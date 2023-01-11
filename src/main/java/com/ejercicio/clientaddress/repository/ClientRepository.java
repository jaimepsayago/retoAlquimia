package com.ejercicio.clientaddress.repository;

import com.ejercicio.clientaddress.dto.OrderRequest;
import com.ejercicio.clientaddress.dto.OrderResponse;
import com.ejercicio.clientaddress.entity.Client;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {
	


	//GET CLIENTS NAME WITH ADDRESSES
    @Query("SELECT new com.ejercicio.clientaddress.dto.OrderResponse(c.id,c.name, p.direction)  FROM Client c JOIN c.addresses p")
    public List<OrderResponse> getJoinInfo();
    //GET CLIENT NAME BY ID WITH ADDRESS
    @Query("SELECT new com.ejercicio.clientaddress.dto.OrderResponse(c.id,c.name, p.direction)  FROM Client c JOIN c.addresses p WHERE c.id=:id")
    List<OrderResponse> findClientByAddresses(@Param("id") Integer id);

    Client findByName(String name);

    Client findByNumberId(String numberId);
    
    
}
