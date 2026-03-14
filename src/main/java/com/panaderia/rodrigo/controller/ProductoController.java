package com.panaderia.rodrigo.controller;

import com.panaderia.rodrigo.model.Producto;
import com.panaderia.rodrigo.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listar(
            @RequestParam(required = false) String buscar,
            @RequestParam(required = false) String categoria,
            Model model) {

        if (buscar != null && !buscar.isBlank()) {
            model.addAttribute("productos",
                    productoService.buscarPorNombre(buscar));
        } else if (categoria != null && !categoria.isBlank()) {
            model.addAttribute("productos",
                    productoService.findByCategoria(categoria));
        } else {
            model.addAttribute("productos", productoService.findAll());
        }

        model.addAttribute("categorias",            productoService.obtenerCategorias());
        model.addAttribute("buscar",                buscar);
        model.addAttribute("categoriaSeleccionada", categoria);
        return "productos/lista";
    }

    @GetMapping("/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public String formularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("titulo",   "Nuevo Producto");
        return "productos/formulario";
    }

    @GetMapping("/editar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Producto p = productoService.findById(id);
        model.addAttribute("producto", p);
        model.addAttribute("titulo",   "Editar: " + p.getNombre());
        return "productos/formulario";
    }

    @PostMapping("/guardar")
    @PreAuthorize("hasRole('ADMIN')")
    public String guardar(@Valid @ModelAttribute Producto producto,
                          BindingResult result,
                          Model model,
                          RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("titulo",
                    producto.getId() == null ? "Nuevo Producto" : "Editar Producto");
            return "productos/formulario";
        }
        productoService.save(producto);
        flash.addFlashAttribute("mensaje", "Producto guardado correctamente ✅");
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        productoService.delete(id);
        flash.addFlashAttribute("mensaje", "Producto eliminado correctamente 🗑️");
        return "redirect:/productos";
    }
}