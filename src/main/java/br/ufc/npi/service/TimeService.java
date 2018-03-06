package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.bean.Time;
import br.ufc.npi.repositorio.JogadorRepositorio;
import br.ufc.npi.repositorio.TimeRepositorio;

@Service
public class TimeService {
	
	@Autowired
	private TimeRepositorio timeRepositorio;
	
	@Autowired
	private JogadorRepositorio jogadorRepositorio;
	
	public Time salvarTime(String nome){
		Time time = new Time();
		time.setNome(nome);
		timeRepositorio.save(time);
		
		return time;
	}
	public void deleteTime(Integer id){
		Time time = timeRepositorio.getOne(id);
		List<Jogador> jogadores = time.getJogadores(); 
		for(Jogador jogador: jogadores){
			jogador.setTime(null);
			jogadorRepositorio.save(jogador);
		}
		timeRepositorio.delete(id);
	}
	
	public List<Time> getTodosTimes(){
		return timeRepositorio.findAll();
	}
	

	public Time getTime(Integer id){
		return timeRepositorio.getOne(id);
	}
	public boolean addJogadorAoTime(Integer idTime, Integer idJogador){
		Time time = timeRepositorio.findOne(idTime);
		  if(time.getJogadores().size()==7){
		    return false;
		  }else {
		    Jogador jogador = jogadorRepositorio.findOne(idJogador);
		    time.getJogadores().add(jogador);
		    jogador.setTime(time);
		    timeRepositorio.save(time);
		    jogadorRepositorio.save(jogador);
		    return true;
		  }

	}
	public void delJogadorDoTime(Integer idTime, Integer idJogador){
		Time time = timeRepositorio.findOne(idTime);
		Jogador jogador = jogadorRepositorio.findOne(idJogador);
		
		time.getJogadores().remove(jogador);
		jogador.setTime(null);
		
		timeRepositorio.save(time);
		jogadorRepositorio.save(jogador);
	}}
