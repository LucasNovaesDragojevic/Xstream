package br.com.xstream;

public class Produto {
	
	private String nome;
	private Double preco;
	private String descricao;
	private Integer codigo;
	
	public Produto(String nome, Double preco, String descricao, Integer codigo) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.codigo = codigo;
	}
}
