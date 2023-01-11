package com.ejercicio.clientaddress.service;

import com.ejercicio.clientaddress.dto.OrderRequest;
import com.ejercicio.clientaddress.dto.OrderResponse;
import com.ejercicio.clientaddress.entity.Address;
import com.ejercicio.clientaddress.entity.Client;
import com.ejercicio.clientaddress.repository.ClientRepository;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    
    
    public List<Client> findAll() {
    	 List <Client> clientsAll = clientRepository.findAll();
         	 return clientsAll;
    }
    
    public String deleteClient(int id){
        clientRepository.deleteById(id);
        return "removed with id: "+id;
    }
    public Client addClient(OrderRequest request){
        return clientRepository.save(request.getClient());
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    public List<OrderResponse> getAddresses(){
        return clientRepository.getJoinInfo();
    }

    public Client updateClient(@NotNull Client client){
        Client existingClient = clientRepository.findById(client.getId()).orElse(null);
        existingClient.setName(client.getName());
        existingClient.setEmail(client.getEmail());
        existingClient.setTypeId(client.getTypeId());
        existingClient.setNumberId(client.getNumberId());
        existingClient.setPhone(client.getPhone());
        return clientRepository.save(existingClient);
    }

    public Client addAddresses( Client client){
        Client existingClient = clientRepository.findById(client.getId()).orElse(null);
        List<Address> existing =existingClient.getAddresses();
        List<Address> newAddress =client.getAddresses();
        List<Address> union = Stream.concat(existing.stream(),newAddress.stream())
                .collect(Collectors.toList());
        existingClient.setAddresses(union);
        return clientRepository.save(existingClient);
    }

    public Client findClientbyName(String name){
        return clientRepository.findByName(name);
    }
}
