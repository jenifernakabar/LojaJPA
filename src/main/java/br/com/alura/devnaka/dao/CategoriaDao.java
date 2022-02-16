package br.com.alura.devnaka.dao;

import javax.persistence.EntityManager;

import br.com.alura.devnaka.modelo.Categoria;
import br.com.alura.devnaka.modelo.Produto;

public class CategoriaDao {
	
	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		em.persist(categoria);
	}
	
	public void atualizar (Categoria categoria) {
		categoria = this.em.merge(categoria);
	}
	
	public void deletar (Categoria categoria) {
		categoria = this.em.merge(categoria);
		this.em.remove(categoria);		
	}
}
