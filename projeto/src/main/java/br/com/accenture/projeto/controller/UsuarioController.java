package br.com.accenture.projeto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.accenture.projeto.enums.Grupo;
import br.com.accenture.projeto.model.Usuario;
import br.com.accenture.projeto.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioService service; 

	@RequestMapping(method = RequestMethod.GET, path = "/entrar")
	public String entrar() {
		return "entrar";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/403")
	public String errorAccessDenied() {
		return "403";
	}

	@RequestMapping("/cadastroUsuario")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("cadastroUsuario");
		mv.addObject("grupo", Grupo.values());
		return mv;
	}

	@RequestMapping(value = {"/cadastroUsuario"}, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes) {

		/**
		 * Verifica se existe algum erro com as informações(campos da página HTML) recebidas da view
		 */
		if (result.hasErrors()) {
			model.addAttribute(usuario);
			return novo(usuario);
		}

		service.salvar(usuario);

		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");

		return new ModelAndView("redirect:/entrar");
	}

}
