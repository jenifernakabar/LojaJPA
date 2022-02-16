package br.com.alura.devnaka.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.devnaka.dao.CategoriaDao;
import br.com.alura.devnaka.dao.ClienteDao;
import br.com.alura.devnaka.dao.PedidoDao;
import br.com.alura.devnaka.dao.ProdutoDao;
import br.com.alura.devnaka.modelo.Categoria;
import br.com.alura.devnaka.modelo.Cliente;
import br.com.alura.devnaka.modelo.ItensPedido;
import br.com.alura.devnaka.modelo.Pedido;
import br.com.alura.devnaka.modelo.Produto;
import br.com.alura.devnaka.util.JPAUtil;

public class PerformanceConsultas {

	public static void main(String[] args) {
//		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDao pedidoDao = new PedidoDao(em);
		Pedido pedido = pedidoDao.buscarPedidoComCliente(1l);
		em.close();
		System.out.println(pedido.getCliente().getNome());
	}

	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");

		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("8000"), videogames);
		Produto macbook = new Produto("Macbook", "Macboo pro retina", new BigDecimal("14000"), informatica);

		Cliente cliente = new Cliente("Rodrigo", "123456");

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItensPedido(10, pedido, celular));
		pedido.adicionarItem(new ItensPedido(40, pedido, videogame));

		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItensPedido(2, pedido, macbook));

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);

		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbook);

		clienteDao.cadastrar(cliente);

		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);

		em.getTransaction().commit();
		em.close();
	}

}
