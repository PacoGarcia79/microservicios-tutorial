package com.microservicios.motoservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.motoservice.model.Moto;
import com.microservicios.motoservice.service.MotoService;

@RestController
@RequestMapping("/motos")
public class MotoController {
	
	@Autowired
	private MotoService motoService;
	
	@GetMapping
	public ResponseEntity<List<Moto>> getBikes() {
		
		List<Moto> motos = motoService.getAll();
		
		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return new ResponseEntity<>(motos, HttpStatus.OK);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> getBikesByUser(@PathVariable("usuarioId") Integer usuarioId) {
		
		List<Moto> motos = motoService.byUser(usuarioId);
		
		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return new ResponseEntity<>(motos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Moto> getBike(@PathVariable("id") Integer id) {
		Moto moto = motoService.getCarById(id);

		if (moto == null) {
			return ResponseEntity.notFound().build();
		}

		return new ResponseEntity<>(moto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Moto> saveCar(@RequestBody Moto moto) {
		return new ResponseEntity<>(motoService.save(moto), HttpStatus.OK);
	}

}
