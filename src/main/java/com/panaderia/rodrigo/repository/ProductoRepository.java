package com.panaderia.rodrigo.repository;

import com.panaderia.rodrigo.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {
 
    List<Producto> findByNombre(String nombre);

    List<Producto> findByCategoria(String categoria);


}
