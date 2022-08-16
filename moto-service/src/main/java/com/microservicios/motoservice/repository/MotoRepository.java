package com.microservicios.motoservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicios.motoservice.model.Moto;


public interface MotoRepository extends JpaRepository<Moto, Integer>{

	List<Moto> findByUsuarioId(Integer usuarioId);
}
