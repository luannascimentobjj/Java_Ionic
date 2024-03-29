package com.luannascimento.cursomc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luannascimento.cursomc.domains.Cliente;
import com.luannascimento.cursomc.domains.Endereco;
import com.luannascimento.cursomc.domains.ItemPedido;
import com.luannascimento.cursomc.domains.Pagamento;
import com.luannascimento.cursomc.domains.Pedido;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instante;
	
	private Pagamento pagamento;

	private Cliente cliente;

	private Endereco enderecoDeEntrega;
	
	private Set<ItemPedido> itens = new HashSet<>();

	

	public PedidoDTO(Pedido pedido) {
		super();
		this.id = pedido.getId();
		this.instante = pedido.getInstante();
		this.cliente = pedido.getCliente();
		this.enderecoDeEntrega = pedido.getEnderecoDeEntrega();
	}
	
	public double getValorTotal() {
		
		double soma = 0.0;
		for (ItemPedido ip : itens) {
			soma = soma + ip.getSubTotal();
		}
		
		return soma;
	}
	
	
}
