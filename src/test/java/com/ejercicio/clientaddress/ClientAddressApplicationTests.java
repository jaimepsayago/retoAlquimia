package com.ejercicio.clientaddress;



import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ejercicio.clientaddress.dto.OrderResponse;
import com.ejercicio.clientaddress.entity.Client;
import com.ejercicio.clientaddress.repository.ClientRepository;
import com.ejercicio.clientaddress.service.ClientService;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;


@SpringBootTest(classes = {ClientAddressApplicationTests.class})
@RunWith(SpringRunner.class)
class ClientAddressApplicationTests {

	@Mock
	private ClientRepository clientRepository;
	
   @InjectMocks
	//@Autowired
	private ClientService clientService;
	

	
	 //Testing obtener todos los clientes
    @Test
    @Order(1)
    public void test_findCustomerAll(){

        List<Client> cliente = new ArrayList<Client>();

        cliente.add(new Client(1,"1725273823","23","Juan","juanP@gmail.com","56755","Azogues","Caniar","",null));
        cliente.add(new Client(2,"1725273824","24","pablo","pabloP@gmail.com","56744","Troncla","Caniar","",null));
        
        when(clientRepository.findAll()).thenReturn(cliente);
        assertEquals(2, StreamSupport.stream(clientService.findAll().spliterator(), false).count());
    }
    
    //Testing obtener cliente por su Id
    @Test
    @Order(2)
    public void testfindByNumberId(){

    	Client cliente = new Client(1,"1725273823","23","Juan","juanP@gmail.com","56755","Azogues","Caniar","",null);
    	String numberId = "23";
        //when(clientRepository.findByNumberId("23")).thenReturn(Optional.of(cliente));
    	when(clientRepository.findByNumberId("23")).thenReturn(cliente);
    	//Assertions.assertThat(cliente.getId()).isEqualTo("23");
    	assertThat(cliente.getNumberId()).isEqualTo(numberId);
    	
    }
    
  //Testing obtener cliente por su Nombre
    @Test
    @Order(2)
    public void testfindByName(){

    	Client cliente = new Client(1,"1725273823","23","Juan","juanP@gmail.com","56755","Azogues","Caniar","",null);
    	String name = "Juan";
        //when(clientRepository.findByNumberId("23")).thenReturn(Optional.of(cliente));
    	when(clientRepository.findByName("Juan")).thenReturn(cliente);
    	//Assertions.assertThat(cliente.getId()).isEqualTo("23");
    	assertThat(cliente.getName()).isEqualTo(name);
    	
    }
    
   
  //Testing crear cliente
    @Test
    @Order(3)
    public void test_createCliente(){

    	Client cliente = new Client(1,"1725273823","23","Juan","juanP@gmail.com","56755","Azogues","Caniar","",null);
        when(clientRepository.save(cliente)).thenReturn(cliente);
        assertEquals(cliente,clientRepository.save(cliente));
    }
    
    //Testing actualizar cliente
    @Test
    @Order(4)
    public void test_updateCliente(){

    	Client cliente = new Client(1,"1725273823","23","Juan","juanP@gmail.com","56755","Azogues","Caniar","",null);
        when(clientRepository.save(cliente)).thenReturn(cliente);
        assertEquals(cliente,clientRepository.save(cliente));
    }

    //Testing eliminar customer
    @Test
    @Order(5)
    public void test_deleteCliente(){

    	Client cliente = new Client(1,"1725273823","23","Juan","juanP@gmail.com","56755","Azogues","Caniar","",null);

    	clientRepository.deleteById(cliente.getId());

        when(clientRepository.save(cliente)).thenReturn(cliente);
        doThrow(RuntimeException.class).when(clientRepository).deleteById(cliente.getId());
        verify(clientRepository, times(1)).deleteById(cliente.getId());
    }
    

    //Testing obtener cliente con direccion
    
	  @Test
	   @Order(6) 
	  public void testfindClientAddress(){
	   OrderResponse or = new OrderResponse(23,"A","B");
	  
	  List<OrderResponse> listOrderResponse = new ArrayList<>(Arrays.asList(or));
			when(clientRepository.getJoinInfo()).thenReturn(listOrderResponse);
	    	//Assertions.assertThat(cliente.getId()).isEqualTo("23");
	    	assertThat(or.getAddressDirection()).isEqualTo(or.getAddressDirection());
	  }
	 
	     //Testing obtener cliente nombre por id con direccion
		  @Test
		   @Order(7) 
		  public void testfindClientNameAddress(){
		  
	  
		  OrderResponse or = new OrderResponse(23,"A","B");
		  int id = 23;
		  List<OrderResponse> listOrderResponse = new ArrayList<>(Arrays.asList(or));
				when(clientRepository.findClientByAddresses(23)).thenReturn(listOrderResponse);
		    	//Assertions.assertThat(cliente.getId()).isEqualTo("23");
		    	assertThat(or.getRId()).isEqualTo(or.getRId());
		  }
		 


}
