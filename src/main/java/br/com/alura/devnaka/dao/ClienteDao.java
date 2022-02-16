package br.com.alura.devnaka.dao;

import javax.persistence.EntityManager;

import br.com.alura.devnaka.modelo.Cliente;

public class ClienteDao {
	
	private EntityManager em;

	public ClienteDao(EntityManager em) {
		super();
		this.em = em;
	}
		
	public void cadastrar(Cliente cliente) {
		em.persist(cliente);
	}
	
	public Cliente buscarPorId(Long id) {
		return em.find(Cliente.class, id);
	
}
}