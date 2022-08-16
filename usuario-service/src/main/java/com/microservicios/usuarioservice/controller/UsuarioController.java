package com.microservicios.usuarioservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.usuarioservice.model.Coche;
import com.microservicios.usuarioservice.model.Moto;
import com.microservicios.usuarioservice.model.Usuario;
import com.microservicios.usuarioservice.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> getUsers() {
		List<Usuario> usuarios = usuarioService.getAll();
		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUser(@PathVariable("id") Integer id) {

		Usuario usuario = usuarioService.getUsuarioById(id);

		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Usuario> saveUser(@RequestBody Usuario usuario) {
		return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.OK);
	}

	@GetMapping("/coches/{usuarioId}")
	public ResponseEntity<List<Coche>> getCarsByUser(@PathVariable("usuarioId") Integer usuarioId) {

		Usuario usuario = usuarioService.getUsuarioById(usuarioId);

		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		List<Coche> userCars = usuarioService.getCars(usuarioId);

		if (userCars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return new ResponseEntity<>(userCars, HttpStatus.OK);
	}

	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> getBikesByUser(@PathVariable("usuarioId") Integer usuarioId) {

		Usuario usuario = usuarioService.getUsuarioById(usuarioId);

		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		List<Moto> userBikes = usuarioService.getBikes(usuarioId);

		if (userBikes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return new ResponseEntity<>(userBikes, HttpStatus.OK);
	}

	// START FEIGN METHODS

	@PostMapping("/coches/{usuarioId}")
	public ResponseEntity<Coche> saveCar(@PathVariable("usuarioId") int usuarioId, @RequestBody Coche coche) {
		return new ResponseEntity<Coche>(usuarioService.saveCar(usuarioId, coche), HttpStatus.OK);
	}

	@PostMapping("/motos/{usuarioId}")
	public ResponseEntity<Moto> saveBike(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto) {
		return new ResponseEntity<Moto>(usuarioService.saveBike(usuarioId, moto), HttpStatus.OK);
	}

	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> getVehicles(@PathVariable("usuarioId") int usuarioId) {
		return new ResponseEntity(usuarioService.getVehicles(usuarioId), HttpStatus.OK);
	}

	//////
	@GetMapping("/coches/feign/{usuarioId}")
	public ResponseEntity<List<Coche>> getCarsFeign(@PathVariable("usuarioId") int usuarioId) {
		return new ResponseEntity(usuarioService.getCarsFeign(usuarioId), HttpStatus.OK);
	}
	//////

	// END FEIGN METHODS
}
