package com.examen.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.examen.model.Producto;
import com.examen.repository.IProductoRepository;

@Controller
public class ProductoController {

    @Autowired
    private IProductoRepository productoRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Producto> productos = productoRepository.findAll();
        model.addAttribute("productos", productos);
        return "index";
    }

    @GetMapping("/producto/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        return "registro";
    }

    @PostMapping("/producto/guardar")
    public String guardarProducto(Producto producto) {
        productoRepository.save(producto);
        return "redirect:/";
    }

    @GetMapping("/producto/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") int id, Model model) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            return "registro";
        } else {
            return "redirect:/"; 
        }
    }

@GetMapping("/producto/eliminar/{id}")
public String eliminarProducto(@PathVariable("id") int id) {
    productoRepository.deleteById(id);
    return "redirect:/";
}

}
