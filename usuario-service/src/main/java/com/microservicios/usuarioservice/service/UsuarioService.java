package com.microservicios.usuarioservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservicios.usuarioservice.feignclients.CocheFeignClient;
import com.microservicios.usuarioservice.feignclients.MotoFeignClient;
import com.microservicios.usuarioservice.model.Coche;
import com.microservicios.usuarioservice.model.Moto;
import com.microservicios.usuarioservice.model.Usuario;
import com.microservicios.usuarioservice.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CocheFeignClient cocheFeignClient;

	@Autowired
	private MotoFeignClient motoFeignClient;

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	public Usuario getUsuarioById(Integer id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	// START RESTTEMPLATE METHODS
	public List<Coche> getCars(int usuarioId) {
		return restTemplate.getForObject("http://localhost:8002/coches/usuario/" + usuarioId, List.class);
	}

	public List<Moto> getBikes(int usuarioId) {
		return restTemplate.getForObject("http://localhost:8003/motos/usuario/" + usuarioId, List.class);
	}

	// END RESTTEMPLATE METHODS

	// START FEIGN METHODS

	public Coche saveCar(int usuarioId, Coche coche) {
		coche.setUsuarioId(usuarioId);
		return cocheFeignClient.save(coche);
	}

	public Moto saveBike(int usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		return motoFeignClient.save(moto);
	}
	
	///
	public List<Coche> getCarsFeign(int usuarioId){
		return cocheFeignClient.getCars(usuarioId);
	}
	///

	public Map<String, Object> getVehicles(int usuarioId) {
		Map<String, Object> vehiclesMap = new HashMap<>();

		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

		if (usuario == null) {
			vehiclesMap.put("Mensaje", "El usuario no existe");
			return vehiclesMap;
		}
		
		vehiclesMap.put("Usuario", usuario);

		List<Coche> coches = cocheFeignClient.getCars(usuarioId);
		
		//vehiclesMap.put("Coches", coches.isEmpty() ? "El usuario no tiene coches" : coches);

		if (coches == null) {
			vehiclesMap.put("Coches", "El usuario no tiene coches");
		} else {
			vehiclesMap.put("Coches", coches);
		}

		List<Moto> motos = motoFeignClient.getBikes(usuarioId);
				
		if (motos.isEmpty()) {
			vehiclesMap.put("Motos", "El usuario no tiene motos");
		} else {
			vehiclesMap.put("Motos", motos);
		}
		
		//vehiclesMap.put("Motos", motos.isEmpty() ? "El usuario no tiene motos" : motos);

		return vehiclesMap;

	}

	// END FEIGN METHODS

}
