package com.luannascimento.cursomc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luannascimento.cursomc.domains.Categoria;
import com.luannascimento.cursomc.domains.Cliente;
import com.luannascimento.cursomc.domains.Endereco;
import com.luannascimento.cursomc.domains.ItemPedido;
import com.luannascimento.cursomc.domains.Pagamento;
import com.luannascimento.cursomc.domains.Pedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento Obrigatório!")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	public PedidoDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instante;

	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
	private Pagamento pagamento;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "endereco_de_entrega_id")
	private Endereco enderecoDeEntrega;

	@OneToMany(mappedBy = "id.pedido")
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
