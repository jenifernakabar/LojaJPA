package br.com.alura.devnaka.teste;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.devnaka.dao.CategoriaDao;
import br.com.alura.devnaka.dao.ClienteDao;
import br.com.alura.devnaka.dao.PedidoDao;
import br.com.alura.devnaka.dao.ProdutoDao;
import br.com.alura.devnaka.modelo.Categoria;
import br.com.alura.devnaka.modelo.Cliente;
import br.com.alura.devnaka.modelo.Pedido;
import br.com.alura.devnaka.modelo.Produto;
import br.com.alura.devnaka.modelo.ItensPedido;
import br.com.alura.devnaka.util.JPAUtil;
import br.com.alura.devnaka.vo.RelatorioDeVendaVo;

public class CadastroDePedido {

	public static void main(String[] args) {
//		popularBancoDeDados();
				
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao pdao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto = pdao.buscarPorId(1l); 
		Produto produto2 = pdao.buscarPorId(2l);
		Produto produto3 = pdao.buscarPorId(3l);
		Cliente cliente = clienteDao.buscarPorId(1l);
		
		em.getTransaction().begin();
		
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItensPedido(10, pedido, produto));
		pedido.adicionarItem(new ItensPedido(40, pedido, produto2));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItensPedido(2, pedido, produto3));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);
		
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("VALOR TOTAL: " + totalVendido);
		
		List<RelatorioDeVendaVo> relatorio = pedidoDao.relatorioDeVendas();
		relatorio.forEach(System.out::println);
		}

	private static void popularBancoDeDados() {
		Categoria celular = new Categoria("celulares");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		Produto p1 = new Produto("Xiami", "muito lindo", new BigDecimal(800), celular );
		Produto p2 = new Produto("PS5", "graficos incriveis", new BigDecimal(5000), videogames );
		Produto p3 = new Produto("Macbook", "excelente qualidade", new BigDecimal(10000), informatica );
		
		Cliente cliente = new Cliente("Ana", "12345");
		
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDao cdao = new CategoriaDao(em);
		ProdutoDao pdao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		cdao.cadastrar(celular);
		cdao.cadastrar(informatica);
		cdao.cadastrar(videogames);
		pdao.cadastrar(p1);
		pdao.cadastrar(p2);
		pdao.cadastrar(p3);
		clienteDao.cadastrar(cliente);
		em.getTransaction().commit();
		em.close();
}
}
