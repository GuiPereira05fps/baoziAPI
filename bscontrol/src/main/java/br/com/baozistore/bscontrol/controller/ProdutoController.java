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

import br.com.baozistore.bscontrol.models.Produto;
import br.com.baozistore.bscontrol.repositories.ProdutoRepository;

@RestController
@RequestMapping({"/produtos"})
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	ProdutoController(ProdutoRepository produtoRepository){
		this.produtoRepository = produtoRepository;
	}
	
	@GetMapping
	public List<Produto> findAll() {
	    return produtoRepository.findAll();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findbyId(@PathVariable long id){
		return produtoRepository.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Produto create(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Produto produto) {
		return produtoRepository.findById(id).map(record -> {
			record.setNome(produto.getNome());
			record.setPreco(produto.getPreco());
			record.setEstoque(produto.getEstoque());
			Produto updated = produtoRepository.save(record);
			return ResponseEntity.ok().body(updated);}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return produtoRepository.findById(id).map(record -> {
			produtoRepository.deleteById(id);
			return ResponseEntity.ok().build();
			}).orElse(ResponseEntity.notFound().build());
	}
	
}

