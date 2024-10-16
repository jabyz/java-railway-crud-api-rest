package com.javi.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javi.apirest.apirest.Repositories.ProductoRepository;
import com.javi.apirest.apirest.Entities.Producto;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    @PostMapping
    public Producto createProduct(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto " + id));
    }

    @PutMapping("/{id}")
    public Producto updateProduct(@PathVariable Long id, @RequestBody Producto producto) {
        Producto miProducto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto " + id));

        miProducto.setNombre(producto.getNombre());
        miProducto.setPrecio(producto.getPrecio());

        return productoRepository.save(miProducto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Producto miProducto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto " + id));
        productoRepository.delete(miProducto);
        return "Producto eliminado " + id;
    }

}
