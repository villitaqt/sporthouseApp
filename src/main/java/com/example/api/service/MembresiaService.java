package com.example.api.service;

import com.example.api.config.ResourceNotFoundException;
import com.example.api.entity.Membresia;
import com.example.api.entity.Miembro;
import com.example.api.entity.TipoMembresia;
import com.example.api.repository.MembresiaRepository;
import com.example.api.repository.TipoMembresiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembresiaService {

    @Autowired
    private MembresiaRepository membresiaRepository;

    @Autowired
    private TipoMembresiaRepository tipoMembresiaRepository;

    public List<Membresia> findAll() {
        return membresiaRepository.findAll();
    }

    public Membresia save(Membresia membresia) {
        // Validar si el tipo de membresía existe
        TipoMembresia tipo = tipoMembresiaRepository.findById(membresia.getTipoMembresia().getId())
                .orElseThrow(() -> new ResourceNotFoundException("TipoMembresia not found"));
        membresia.setTipoMembresia(tipo);
        return membresiaRepository.save(membresia);
    }

    public Membresia findByIdOrThrow(Long id) {
        return membresiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membresia no encontrado con id " + id));
    }

    public Membresia update(Long id, Membresia membresiaDetails) {
        // Buscar la membresía existente
        Membresia membresiaExistente = findByIdOrThrow(id);

        // Validar si el tipo de membresía existe
        TipoMembresia tipo = tipoMembresiaRepository.findById(membresiaDetails.getTipoMembresia().getId())
                .orElseThrow(() -> new ResourceNotFoundException("TipoMembresia not found"));

        // Actualizar los detalles de la membresía existente
        membresiaExistente.setFechaInicio(membresiaDetails.getFechaInicio());
        membresiaExistente.setFechaFin(membresiaDetails.getFechaFin());
        membresiaExistente.setTipoMembresia(tipo);

        // Guardar la membresía actualizada
        return membresiaRepository.save(membresiaExistente);
    }
}
