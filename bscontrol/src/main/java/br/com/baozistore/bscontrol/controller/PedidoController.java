package br.com.baozistore.bscontrol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.baozistore.bscontrol.models.Pedido;
import br.com.baozistore.bscontrol.repositories.PedidoRepository;

@RestController	
@RequestMapping({"/pedidos"})
public class PedidoController {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	PedidoController(PedidoRepository pedidoRepository){
		this.pedidoRepository = pedidoRepository;
	}
	
	@GetMapping
	public List<Pedido> findAll() {
	    return pedidoRepository.findAll();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findbyId(@PathVariable long id){
		return pedidoRepository.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Pedido create(@RequestBody Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return pedidoRepository.findById(id).map(record -> {
			pedidoRepository.deleteById(id);
			return ResponseEntity.ok().build();
			}).orElse(ResponseEntity.notFound().build());
	}
	
}

