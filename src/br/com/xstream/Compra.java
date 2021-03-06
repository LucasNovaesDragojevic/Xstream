package br.com.xstream;

import java.util.ArrayList;
import java.util.List;

public class Compra {

	private Integer id;
	private List<Produto> produtos = new ArrayList<>();
	
	public Compra(Integer id, List<Produto> produtos) {
		this.id = id;
		this.produtos = produtos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
}
