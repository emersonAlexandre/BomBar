package br.com.accenture.projeto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.accenture.projeto.enums.DiasDaSemana;
import br.com.accenture.projeto.enums.TiposDeComida;
import br.com.accenture.projeto.enums.TiposDeMusica;
import br.com.accenture.projeto.exceptions.CnpjJaExistenteException;
import br.com.accenture.projeto.exceptions.DiaDaSemanaInvalidoException;
import br.com.accenture.projeto.model.Bar;
import br.com.accenture.projeto.model.Cidade;
import br.com.accenture.projeto.service.BarService;
import br.com.accenture.projeto.service.EnderecoService;
import br.com.accenture.projeto.util.Localizacao;

/**
 * Classe que representa o controller de todo o fluxo de requesições relacionadas a entidade Bar
 */
@Controller
@RequestMapping("/bares")
public class BarController {

	/**
	 * Injetando BarService para utilizar os seus métodos de manipulação com os dados de um objeto do tipo Bar
	 */
	@Autowired
	BarService serviceBar;

	/**
	 * Injetando BarService para utilizar os seus métodos de manipulação com os dados de um objeto do tipo Endereco
	 */
	@Autowired
	EnderecoService serviceEndereco;

	/**
	 * Injetando Localizacao para utilizar os seus métodos de manipulação com os arquivos do tipo JSON
	 */
	@Autowired
	Localizacao local;

	/**
	 * Método que é responsável por retornar um ModelAndView juntamente com os tipos de comida, tipos de música, dias da semana, cidades, estados e cadastro.html(página HTML para onde será redirecionado)
	 * @return (Retorna um ModelAndView juntamente com os tipos de comida, tipos de música, dias da semana, cidades, estados e cadastro.html(página HTML para onde será redirecionado))
	 */
	@RequestMapping("/novo")
	public ModelAndView novo(Bar bar) {
		ModelAndView mv = new ModelAndView("cadastroBar");
		mv.addObject("tiposComida", TiposDeComida.values());
		mv.addObject("tiposMusica", TiposDeMusica.values());
		mv.addObject("diasDaSemana", DiasDaSemana.values());
		mv.addObject("cidades", local.buscarCidades());
		mv.addObject("estados", local.buscarEstados());
		return mv;

	}

	/**
	 * Método responsável por receber da view o objeto do tipo Bar e salvá-lo ou atualizá-lo chamando os métodos da classe BarService
	 * @param bar (Objeto que vem da view passado por parâmetro que será cadastrado ou atualizado no banco de dados)
	 * @param result (Informações sobre os campos vindos da view)
	 * @param model (Modelo que represento o objeto na view)
	 * @param attributes (Atributos que são passados para view, onde lá os mesmos são manipulados)
	 * @return (Retorna um ModelAndView juntamente com a mensagem de sucesso ou erro, objeto que está tentando salvar ou editar(caso tenha ocorrido algum erro) e bares.html(página HTML para onde será 
	 * redirecionado, a qual contém todos os registros de bares)
	 */
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Bar bar, BindingResult result, Model model, RedirectAttributes attributes) {

		/**
		 * Verifica se existe algum erro com as informações(campos da página HTML) recebidas da view
		 */
		if (result.hasErrors()) {
			model.addAttribute(bar);
			return novo(bar);
		}

		String msg = "";

		try {
			/**
			 * Verifica se o objeto recebido por parâmetro já contém um id, caso tenha, ele entra nessa condição, onde significa que será uma atualização de informações e não um registro de informações no 
			 * banco de dados, portanto será chamado o método edição de BarService
			 */
			if (bar.getId() != null) {
				Long id = serviceBar.buscarPorId(bar.getId()).getEndereco().getId();
				bar.getEndereco().setId(id);
				serviceEndereco.editar(bar.getEndereco());
				bar.setEndereco(serviceEndereco.buscarPorId(bar.getEndereco().getId()));
				serviceBar.editar(bar);
				msg += "Bar atualizado com sucesso";
			}

			/**
			 * Caso não tenha um id, significa que é um registro de informações, então entrará nesta condição, onde será chamado o método salvar de BarService
			 */
			else {
				serviceEndereco.salvar(bar.getEndereco());
				bar.setEndereco(serviceEndereco.buscarTodos().get(serviceEndereco.buscarTodos().size()-1));
				serviceBar.salvar(bar);
				msg += "Bar salvo com sucesso";
			}

		} catch (CnpjJaExistenteException e) {
			msg += "Erro! "+e.getMessage();
		} catch (DiaDaSemanaInvalidoException e) {
			msg += "Erro! "+e.getMessage();
		}

		attributes.addFlashAttribute("mensagem", msg);

		/**
		 * Verifica se tem erros relacionados a validação feitas no DAO, caso tenha retorna o método novo com a mensagem do erro juntamente com o objeto em questão que está com o erro, para que não seja 
		 * perdido as informações já preenchidas nos campos da view
		 */
		if (msg.contains("Erro")) {
			model.addAttribute(bar);
			model.addAttribute("mensagem", msg);
			return novo(bar);
		}

		else {
			return new ModelAndView("redirect:/bares");
		}
	}

	/**
	 * Método que representa uma requisição do tipo GET, que retorna uma ModelAndView com um redirecionamento para o método novo, passando o objeto que foi buscado no banco de dados de acordo com o id recebido 
	 * por parâmetro, para que quando o mesmo for redirecionado para a página de cadastro, os campos do formulário sejam preenchidos de acordo com suas informações
	 * @param id (Id do objeto que veio da view)
	 * @return (Retorna uma ModelAndView com um redirecionamento para o método novo, passando o objeto que foi buscado no banco de dados de acordo com o id recebido por parâmetro)
	 */
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Bar bar = serviceBar.buscarPorId(id);
		ModelAndView mv = novo(bar);
		mv.addObject(bar);
		return mv;
	}

	/**
	 * Método que retorna uma lista de objetos do tipo Bar para a página bares.html
	 * @return (Retorna uma lista de objetos do tipo Bar para a página bares.html)
	 */
	@RequestMapping
	public ModelAndView listar() {
		List<Bar> bares = serviceBar.buscarTodos();
		for (Bar bar : bares) {
			bar.getEndereco().setCidade(local.buscarCidadePorCodigo(bar.getEndereco().getCidade()).getNome());
			bar.getEndereco().setEstado(local.buscarEstadoPorCodigo(bar.getEndereco().getEstado()).getSigla());
		}
		ModelAndView mv = new ModelAndView("bares");
		mv.addObject("bares", bares);
		return mv;
	}

	/**
	 * Método que representa uma requisição do tipo DELETE onde é responsável por realizar a chamada do método excluir de BarService passando por parâmetro o id que foi recebido por parâmetro, o qual é 
	 * referente a um objeto Bar que deseja deletar, e retorna uma mensagem de sucesso após a remoção do objeto
	 * @param id (Id do objeto que deseja deletar)
	 * @param attributes (Atributos que serão retornados para a página HTML)
	 * @return (Retorna uma String que redireciona para a página bares.html)
	 */
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		Long idEndereco = serviceBar.buscarPorId(id).getEndereco().getId();
		serviceBar.excluir(id);
		serviceEndereco.excluir(idEndereco);
		attributes.addFlashAttribute("mensagem", "Bar excluído com sucesso");
		return "redirect:/bares";
	}

	/**
	 * Método que representa uma requisição do tipo GET que é responsável por retornar uma lista de cidades em formato JSON de acordo com o id de um estado que é passado por parâmetro
	 * @param id (Id de um estado que é passado por parâmetro)
	 * @return (Retorna uma lista de cidades em formato JSON com base no id do estado passado por parâmetro)
	 */
	@RequestMapping(value="/listacidades/{id}", method=RequestMethod.GET)
	public @ResponseBody List<Cidade> buscarCidadesPorId(@PathVariable("id") Long id) {
		return local.buscarCidadesPorCodigoDoEstado(String.valueOf(id));
	}

}
