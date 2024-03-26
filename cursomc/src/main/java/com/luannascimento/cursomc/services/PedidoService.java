package com.luannascimento.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luannascimento.cursomc.domains.ItemPedido;
import com.luannascimento.cursomc.domains.PagamentoComBoleto;
import com.luannascimento.cursomc.domains.Pedido;
import com.luannascimento.cursomc.domains.enums.EstadoPagamento;
import com.luannascimento.cursomc.exceptions.ObjectNotFoundException;
import com.luannascimento.cursomc.repositories.ItemPedidoRepository;
import com.luannascimento.cursomc.repositories.PagamentoRepository;
import com.luannascimento.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;

	public Optional<Pedido> buscar(Integer id) {

		Optional<Pedido> pedido = pedidoRepository.findById(id);

		if (pedido.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo" + Pedido.class.getName());
		}

		return pedido;

	}
	@Transactional
	public Pedido inserir(Pedido ped) {
		ped.setId(null);
		ped.setInstante(new Date());
		ped.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		if(ped.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) ped.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, ped.getInstante());
		}
		
		ped = pedidoRepository.save(ped);
		pagamentoRepository.save(ped.getPagamento());
		
		for (ItemPedido ip : ped.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.buscar(ip.getProduto().getId()).get());
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(ped);
			
		}
		
		itemPedidoRepository.saveAll(ped.getItens());
		
		return ped;
	}

}
