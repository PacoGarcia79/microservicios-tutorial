package com.microservicios.cocheservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicios.cocheservice.model.Coche;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Integer>{

	List<Coche> findByUsuarioId(Integer usuarioId);
}
