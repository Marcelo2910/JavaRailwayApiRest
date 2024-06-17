package com.sergiecode.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergiecode.apirest.apirest.Repositories.ProductorRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sergiecode.apirest.apirest.Entities.Producto;


@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductorRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProducts(){
        return productoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));
    }

    @PostMapping
    public Producto createProduct(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }



    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto productDetails ){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        producto.setNombre(productDetails.getNombre());
        producto.setPrecio(productDetails.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String productDeleted(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        productoRepository.delete(producto);

        return "El producto con el ID: " + id + " fue eliminado correctamente";
    }
    
}
