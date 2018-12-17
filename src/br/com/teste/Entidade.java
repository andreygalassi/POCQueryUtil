package br.com.teste;

import java.util.ArrayList;
import java.util.List;

public class Entidade {

	private Long id;
	private String descricao;
	private List<String> lista = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<String> getLista() {
		return lista;
	}
	public void setLista(List<String> lista) {
		this.lista = lista;
	}
	
	public String teste1() {
		return "teste1"; 
	}
	
	public void teste2(String teste2) {
		this.descricao = teste2;
	}

	public String teste3() {
		return "teste3"; 
	}
	
	public void teste3(String teste3) {
		this.descricao = teste3;
	}
	
}
