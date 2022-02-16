package br.com.alura.devnaka.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.devnaka.dao.CategoriaDao;
import br.com.alura.devnaka.dao.ProdutoDao;
import br.com.alura.devnaka.modelo.Categoria;
import br.com.alura.devnaka.modelo.Produto;
import br.com.alura.devnaka.util.JPAUtil;

public class CadastroDeProdutos {

	public static void main(String[] args) {

		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao pdao = new ProdutoDao(em);
		
		Produto p = pdao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = pdao.buscarTodos();
		todos.forEach(p2 -> System.out.println(p.getNome()));
	}

	private static void cadastrarProduto() {
		Categoria celular = new Categoria("celulares");
		Produto p1 = new Produto("celular", "muito lindo", new BigDecimal(800), celular );
		
		EntityManager em = JPAUtil.getEntityManager();
		
		CategoriaDao cdao = new CategoriaDao(em);
		ProdutoDao pdao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		
		cdao.cadastrar(celular);
		pdao.cadastrar(p1);
		
		em.getTransaction().commit();
		em.close();
	}

}
