package com.microservicios.cocheservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.cocheservice.model.Coche;
import com.microservicios.cocheservice.repository.CocheRepository;

@Service
public class CocheService {
	
	@Autowired
	private CocheRepository cocheRepository;
	
	public List<Coche> getAll(){
		return cocheRepository.findAll();
	}
	
	public Coche getCarById(Integer id) {
		return cocheRepository.findById(id).orElse(null);
	}
	
	public Coche save(Coche coche) {
		return cocheRepository.save(coche);
	}
	
	public List<Coche> byUser(Integer usuarioId){
		return cocheRepository.findByUsuarioId(usuarioId);
	}

}
