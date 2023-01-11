package com.ejercicio.clientaddress.dto;

import com.ejercicio.clientaddress.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {
    private Client client;
}
