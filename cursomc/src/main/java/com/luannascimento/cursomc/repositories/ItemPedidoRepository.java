package com.luannascimento.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luannascimento.cursomc.domains.ItemPedido;
import com.luannascimento.cursomc.domains.ItemPedidoPK;


@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {



		

}
