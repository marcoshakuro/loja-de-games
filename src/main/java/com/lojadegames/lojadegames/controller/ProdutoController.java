package com.lojadegames.lojadegames.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojadegames.lojadegames.model.Produto;
import com.lojadegames.lojadegames.repository.ProdutoRepository;

@RestController
@RequestMapping ("/produtos")
@CrossOrigin (origins = "*")
public class ProdutoController {
	
	
	@Autowired
	public ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById (@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/produtos/{produtos}")
	public ResponseEntity<List<Produto>> getByNome (@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Produto> post (@RequestBody Produto produtos){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtos));
	}
	
	@PutMapping
	public ResponseEntity<Produto> put (@RequestBody Produto produtos){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produtos));
	}
	
	@DeleteMapping("/{id}")
	public void delete(long id) {
		repository.deleteById(id);
	}

}
