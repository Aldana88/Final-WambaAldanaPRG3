package com.example.app.controller;

import com.example.app.dto.DevolucionRequest;
import com.example.app.dto.MaterialDTO;
import com.example.app.dto.PrestamoRequest;
import com.example.app.service.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping("/prestamos/prestar")
    public String prestar(@RequestBody PrestamoRequest request) {
        return prestamoService.prestar(request);
    }

    @PostMapping("/prestamos/devolver")
    public String devolver(@RequestBody DevolucionRequest request) {
        return prestamoService.devolver(request);
    }

    @GetMapping("/materiales/disponibles")
    public List<MaterialDTO> getDisponibles() {
        return prestamoService.obtenerDisponibles();
    }

    @PostMapping("/socios/depurar-duplicados")
    public List<String> depurarDuplicados(@RequestBody List<String> dnis) {
        return prestamoService.depurarDuplicados(dnis);
    }
}