package com.generation.loja_games.Model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name= "tb_produto")//= create table
public class Produto {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=100)
	@NotBlank(message ="O Atributo nome é obrigatorio!")
	@Size(min = 5, max =100, message="O Atributo nome deve conter no minimo 5 e no maximo 100 caracteres.")
	private String nome;
	
	@Column(length=1000)
	@NotBlank(message ="O Atributo console é obrigatorio!")
	@Size(min = 10, max =1000, message="O Atributo console deve conter no minimo 10 e no maximo 1000 caracteres.")
	private String console;
	
	@Column(length=1000)
	@NotBlank(message ="O Atributo descricao é obrigatorio!")
	@Size(min = 10, max =1000, message="O Atributo descricao deve conter no minimo 10 e no maximo 1000 caracteres.")
	private String descricao;
	
	@DecimalMin(value = "0.0",inclusive= false)
	@Digits(integer=3,fraction= 2)
	private BigDecimal preco;
	
	@Column(length=100)
	@NotBlank(message ="O Atributo foto é obrigatorio!")
	@Size(min = 10, max =100, message="O Atributo foto deve conter no minimo 10 e no maximo 1000 caracteres.")
	private String foto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}


	
}
