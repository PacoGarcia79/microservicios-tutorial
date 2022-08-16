package com.microservicios.motoservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.motoservice.model.Moto;
import com.microservicios.motoservice.repository.MotoRepository;

@Service
public class MotoService {
	
	@Autowired
	private MotoRepository motoRepository;
	
	public List<Moto> getAll(){
		return motoRepository.findAll();
	}
	
	public Moto getCarById(Integer id) {
		return motoRepository.findById(id).orElse(null);
	}
	
	public Moto save(Moto moto) {
		return motoRepository.save(moto);
	}
	
	public List<Moto> byUser(Integer usuarioId){
		return motoRepository.findByUsuarioId(usuarioId);
	}

}
