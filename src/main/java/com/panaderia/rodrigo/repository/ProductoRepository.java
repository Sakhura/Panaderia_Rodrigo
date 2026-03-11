package com.panaderia.rodrigo.repository;

import com.panaderia.rodrigo.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoRepository {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    public Producto findById(int id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void delete(int id) {
        productoRepository.deleteById(id);
    }

}
