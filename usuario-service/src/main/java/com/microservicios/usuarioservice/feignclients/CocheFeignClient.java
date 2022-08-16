package com.microservicios.usuarioservice.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservicios.usuarioservice.model.Coche;

//@FeignClient(name = "coche-service", url="http://localhost:8002")
//@RequestMapping("/coches")
//public interface CocheFeignClient {
//
//  @PostMapping
//	public Coche save(@RequestBody Coche coche);
//}

@FeignClient(name = "coche-service", url = "http://localhost:8002/coches")
public interface CocheFeignClient {

	@PostMapping()
	public Coche save(@RequestBody Coche coche);

	@GetMapping("/usuario/{usuarioId}")
	public List<Coche> getCars(@PathVariable("usuarioId") int usuarioId);
}
