package com.generation.loja_games.Controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.loja_games.Model.Produto;
import com.generation.loja_games.Repository.CategoriaRepository;
import com.generation.loja_games.Repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	@GetMapping("/preco/{preco}")
	public ResponseEntity<List<Produto>> getByTitulo(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findByPrecoLessThanEqual(preco ));
		
	}
	
	@GetMapping("/precos/{preco}")
	public ResponseEntity<List<Produto>> getByPreco(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThan(preco ));
		
		}
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {

		if (categoriaRepository.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
	}
	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
		if (produtoRepository.existsById(produto.getId())) {

			if (categoriaRepository.existsById(produto.getCategoria().getId())) 
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
			
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		if (produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}
