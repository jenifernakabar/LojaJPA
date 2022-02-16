package br.com.alura.devnaka.vo;

import java.time.LocalDate;

public class RelatorioDeVendaVo {
	
	private String nomeProduto;
	private Long qunatidadeVendida;
	private LocalDate dataUltimaVenda;
	
	
	public RelatorioDeVendaVo(String nomeProduto, Long qunatidadeVendida, LocalDate dataUltimaVenda) {
		this.nomeProduto = nomeProduto;
		this.qunatidadeVendida = qunatidadeVendida;
		this.dataUltimaVenda = dataUltimaVenda;
	}

	
	@Override
	public String toString() {
		return "RelatorioDeVendaVo [nomeProduto=" + nomeProduto + ", qunatidadeVendida=" + qunatidadeVendida
				+ ", dataUltimaVenda=" + dataUltimaVenda + "]";
	}


	public String getNomeProduto() {
		return nomeProduto;
	}


	public Long getQunatidadeVendida() {
		return qunatidadeVendida;
	}


	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}
	
	
	

}
