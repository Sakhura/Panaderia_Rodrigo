package com.panaderia.rodrigo.loader;

import com.panaderia.rodrigo.model.Producto;
import com.panaderia.rodrigo.repository.PedidoRepository;
import com.panaderia.rodrigo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) {
        productoRepository.save(new Producto(null, "MediasLunas", "Facturas" , 35.00, 50));
        productoRepository.save(new Producto(null, "Marraqueta", "Pan" , 120.00, 150));
        productoRepository.save(new Producto(null, "Torta de Chocolate", "Torta" , 2800.00, 50));

    }
}
