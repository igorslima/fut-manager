package br.ufc.npi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.service.JogadorService;

@Controller
@RequestMapping(path="/jogadores/")
public class JogadorController {
	
	@Autowired
	private JogadorService jogadorService;

	@RequestMapping(path="/")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("jogadores");
		mv.addObject("jogadores", jogadorService.getTodosJogadores());
		return mv;
	}
	
	@RequestMapping(path="/salvar", method=RequestMethod.POST)
	public String salvarTime(@RequestParam String nome, @RequestParam Integer idade){
		jogadorService.salvarJogador(nome, idade);
		return "redirect:/jogadores/";
	}
	@RequestMapping(path="/excluir/{id}")
	public String excluirJogador(@PathVariable("id") Integer id){
		jogadorService.deleteJogador(id);
		return "redirect:/jogadores/";
	}
	

}
