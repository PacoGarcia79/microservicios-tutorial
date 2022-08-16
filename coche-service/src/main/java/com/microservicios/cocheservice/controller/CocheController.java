package com.microservicios.cocheservice.controller;

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

import com.microservicios.cocheservice.model.Coche;
import com.microservicios.cocheservice.service.CocheService;

@RestController
@RequestMapping("/coches")
public class CocheController {
	
	@Autowired
	private CocheService cocheService;
	
	@GetMapping
	public ResponseEntity<List<Coche>> getCars() {
		
		List<Coche> coches = cocheService.getAll();
		
		if (coches.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return new ResponseEntity<>(coches, HttpStatus.OK);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Coche>> getCarsByUser(@PathVariable("usuarioId") Integer usuarioId) {
		
		List<Coche> coches = cocheService.byUser(usuarioId);
		
		if (coches.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return new ResponseEntity<>(coches, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Coche> getCar(@PathVariable("id") Integer id) {

		Coche coche = cocheService.getCarById(id);

		if (coche == null) {
			return ResponseEntity.notFound().build();
		}

		return new ResponseEntity<>(coche, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Coche> saveCar(@RequestBody Coche coche) {
		return new ResponseEntity<>(cocheService.save(coche), HttpStatus.OK);
	}

}
