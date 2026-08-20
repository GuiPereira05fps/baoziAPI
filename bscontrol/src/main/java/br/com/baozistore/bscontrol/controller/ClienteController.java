package br.com.baozistore.bscontrol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.baozistore.bscontrol.models.Cliente;
import br.com.baozistore.bscontrol.repositories.ClienteRepository;

@RestController
@RequestMapping({"/clientes"})
public class ClienteController {
	@Autowired
	private ClienteRepository clienteRepository;
	
	ClienteController(ClienteRepository clienteRepository){
		this.clienteRepository = clienteRepository;
	}
	
	@GetMapping
	
	public List<Cliente> findAll() {
	    return clienteRepository.findAll();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findbyId(@PathVariable long id){
		return clienteRepository.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Cliente create(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Cliente cliente) {
		return clienteRepository.findById(id).map(record -> {
			record.setNome(cliente.getNome());
			record.setClienteDesde(cliente.getClienteDesde());
			Cliente updated = clienteRepository.save(record);
			return ResponseEntity.ok().body(updated);}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return clienteRepository.findById(id).map(record -> {
			clienteRepository.deleteById(id);
			return ResponseEntity.ok().build();
			}).orElse(ResponseEntity.notFound().build());
	}
	
}

