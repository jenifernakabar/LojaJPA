package br.com.alura.devnaka.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.devnaka.modelo.Pedido;
import br.com.alura.devnaka.vo.RelatorioDeVendaVo;

public class PedidoDao {
	
	private EntityManager em;

	public PedidoDao(EntityManager em) {
		super();
		this.em = em;
	}
		
	public void cadastrar(Pedido pedido) {
		em.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	public List<RelatorioDeVendaVo> relatorioDeVendas() {
		String jpql = "SELECT new br.com.alura.devnaka.vo.RelatorioDeVendaVo("
					+ "produto.nome, "
					+ "SUM(item.quantidade), "
					+ "MAX(pedido.data)) "
					+ "FROM Pedido pedido "
					+ "JOIN pedido.itens item "
					+ "JOIN  item.produto produto "
					+ "GROUP BY produto.nome "
					+ "ORDER BY item.quantidade DESC";
		return em.createQuery(jpql, RelatorioDeVendaVo.class).getResultList();
	}
	public Pedido buscarPedidoComCliente(Long id) {
		return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}