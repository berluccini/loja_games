package com.generation.loja_games.Repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.loja_games.Model.Produto;

public interface ProdutoRepository   extends JpaRepository<Produto,Long>{

	List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);
	List<Produto> findByPrecoLessThanEqual(BigDecimal preco);
	List<Produto> findByPrecoGreaterThan(BigDecimal preco);
}
