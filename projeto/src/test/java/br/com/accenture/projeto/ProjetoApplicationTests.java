package br.com.accenture.projeto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.accenture.projeto.enums.DiasDaSemana;
import br.com.accenture.projeto.enums.TiposDeComida;
import br.com.accenture.projeto.enums.TiposDeMusica;
import br.com.accenture.projeto.exceptions.CnpjJaExistenteException;
import br.com.accenture.projeto.exceptions.DiaDaSemanaInvalidoException;
import br.com.accenture.projeto.model.Bar;
import br.com.accenture.projeto.model.Endereco;
import br.com.accenture.projeto.service.BarService;
import br.com.accenture.projeto.service.EnderecoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjetoApplicationTests {

	@Autowired
	BarService serviceBar;

	@Autowired
	EnderecoService serviceEndereco;

	@Test
	public void contextLoads() {
		salvarTest();
		editarTest();
		deletarTest();
	}

	public void salvarTest() {

		Endereco endereco = new Endereco("Rua Teste", 10, "Centro", "3315", "16");

		serviceEndereco.salvar(endereco);

		endereco = serviceEndereco.buscarTodos().get(serviceEndereco.buscarTodos().size()-1);

		Bar bar = new Bar("BarTest", "89.049.628/0001-95", "(87) 999999999", DiasDaSemana.SEGUNDA, DiasDaSemana.DOMINGO, "08:00", "00:00", TiposDeMusica.BREGA, TiposDeComida.CALDINHO, true, true, false, false, endereco);

		boolean result = true;

		try {
			serviceBar.salvar(bar);
		} catch (CnpjJaExistenteException e) {
			System.out.println(e.getMessage());
			result = false;
		} catch (DiaDaSemanaInvalidoException e) {
			System.out.println(e.getMessage());
			result = false;
		}

		assertEquals(true, result);
	}

	public void editarTest() {

		Endereco endereco = serviceEndereco.buscarTodos().get(serviceEndereco.buscarTodos().size()-1);

		endereco.setLogradouro("Rua Test");

		serviceEndereco.editar(endereco);

		Bar bar = serviceBar.buscarTodos().get(serviceBar.buscarTodos().size()-1);

		bar.setEndereco(endereco);
		bar.setNome("Test");

		try {
			serviceBar.editar(bar);
		} catch (CnpjJaExistenteException e) {
			System.out.println(e.getMessage());
		} catch (DiaDaSemanaInvalidoException e) {
			System.out.println(e.getMessage());
		}

		assertEquals("Test", serviceBar.buscarPorId(bar.getId()).getNome());
		assertEquals("Rua Test", serviceBar.buscarPorId(bar.getId()).getEndereco().getLogradouro());
	}

	public void deletarTest() {

		Bar bar = serviceBar.buscarTodos().get(serviceBar.buscarTodos().size()-1);

		Long idBar = bar.getId();

		serviceBar.excluir(idBar);

		Endereco endereco = serviceEndereco.buscarTodos().get(serviceEndereco.buscarTodos().size()-1);

		Long idEndereco = endereco.getId();

		serviceEndereco.excluir(idEndereco);

		assertEquals(null, serviceBar.buscarPorId(idBar));

		assertEquals(null, serviceEndereco.buscarPorId(idEndereco));

	}

}
