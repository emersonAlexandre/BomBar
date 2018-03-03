package br.com.accenture.projeto.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.accenture.projeto.enums.TiposDeComida;
import br.com.accenture.projeto.enums.TiposDeMusica;
import br.com.accenture.projeto.model.Bar;
import br.com.accenture.projeto.service.BarService;
import br.com.accenture.projeto.util.Localizacao;

@Controller
@RequestMapping("/buscaBares")
public class BuscaController {

	@Autowired
	BarService serviceBar;

	@Autowired
	Localizacao local;

	@RequestMapping
	public ModelAndView listar(ArrayList<Bar> bares, Bar bar) {
		
		if (bares.isEmpty()) {
			bares = new ArrayList<>();
		}

		for (Bar b : bares) {
			b.getEndereco().setCidade(local.buscarCidadePorCodigo(b.getEndereco().getCidade()).getNome());
			b.getEndereco().setEstado(local.buscarEstadoPorCodigo(b.getEndereco().getEstado()).getSigla());
		}
		ModelAndView mv = new ModelAndView("buscaBares");
		mv.addObject("bares", bares);
		mv.addObject("tiposComida", TiposDeComida.values());
		mv.addObject("tiposMusica", TiposDeMusica.values());
		return mv;
	}

	@RequestMapping(value="/buscar",method=RequestMethod.GET)
	public ModelAndView buscarBares(@Valid Bar bar, BindingResult result, Model model, RedirectAttributes attributes) {
		if (bar.getTipoDeComida() == null && bar.getTipoDeMusica() == null) {
			return listar(new ArrayList<>(), bar);
		}
		return listar((ArrayList<Bar>) serviceBar.buscarBar(bar), bar);
	}
}
