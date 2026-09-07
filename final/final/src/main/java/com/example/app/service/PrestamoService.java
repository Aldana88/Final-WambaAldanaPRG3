package com.example.app.service;

import com.example.app.dto.DevolucionRequest;
import com.example.app.dto.MaterialDTO;
import com.example.app.dto.PrestamoRequest;
import com.example.app.model.*;
import com.example.app.strategy.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class PrestamoService {

    private HashMap<String, Material> materialesMap = new HashMap<>();
    private HashMap<Integer, Socio> sociosMap = new HashMap<>();

    public PrestamoService() {
        Libro l1 = new Libro("L001", "Cien Años de Soledad", true, "Gabriel García Márquez");
        Revista r1 = new Revista("R001", "National Geographic", true, 202);
        
        materialesMap.put(l1.getCodigo(), l1);
        materialesMap.put(r1.getCodigo(), r1);

        SocioRegular s1 = new SocioRegular(1, "Juan Perez");
        SocioPremium s2 = new SocioPremium(2, "Maria Gomez");

        sociosMap.put(s1.getId(), s1);
        sociosMap.put(s2.getId(), s2);
    }

    public String prestar(PrestamoRequest request) {
        Material material = materialesMap.get(request.getCodigoMaterial());
        if (material == null) {
            return "El material no existe.";
        }

        if (!material.isDisponible()) {
            return "El material ya está prestado.";
        }

        material.setDisponible(false);
        return "Préstamo realizado con éxito.";
    }

    public String devolver(DevolucionRequest request) {
        Material material = materialesMap.get(request.getCodigoMaterial());
        Socio socio = sociosMap.get(request.getIdSocio());

        if (material == null || socio == null) {
            return "Material o Socio no encontrado.";
        }

        material.setDisponible(true);

        if (request.getDiasAtraso() <= 0) {
            return "Devolución realizada a tiempo. Sin multa.";
        }

        MultaStrategy estrategia;
        if ("CAMPANIA".equalsIgnoreCase(request.getTipoEstrategia())) {
            estrategia = new MultaCampania();
        } else if ("FIN_DE_SEMANA".equalsIgnoreCase(request.getTipoEstrategia())) {
            estrategia = new MultaFinDeSemana();
        } else {
            estrategia = new MultaNormal();
        }

        double montoBase = estrategia.calcular(request.getDiasAtraso());
        double montoFinal = socio.aplicarBeneficio(montoBase);

        return "Devolución realizada. Multa a pagar: $" + montoFinal;
    }

    public List<MaterialDTO> obtenerDisponibles() {
        List<MaterialDTO> disponibles = new ArrayList<>();

        for (Material m : materialesMap.values()) {
            if (m.isDisponible()) {
                String tipo = (m instanceof Libro) ? "LIBRO" : "REVISTA";
                disponibles.add(new MaterialDTO(m.getCodigo(), m.getTitulo(), m.isDisponible(), tipo));
            }
        }

        return disponibles;
    }

    public List<String> depurarDuplicados(List<String> dnisConDuplicados) {
        HashSet<String> dnisUnicos = new HashSet<>(dnisConDuplicados);
        return new ArrayList<>(dnisUnicos);
    }
}