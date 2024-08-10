package com.example.api.service;

import com.example.api.entity.Miembro;
import com.example.api.repository.MiembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiembroService {

    @Autowired
    private MiembroRepository miembroRepository;

    public List<Miembro> findAll() {
        return miembroRepository.findAll();
    }

    public Miembro findByIdOrThrow(Long id) {
        return miembroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado con id " + id));
    }

    public Miembro save(Miembro miembro) {
        // Agrega validaciones si es necesario
        return miembroRepository.save(miembro);
    }

    public Miembro updateMiembro(Long id, Miembro miembroDetails) {
        Miembro miembro = findByIdOrThrow(id);
        miembro.setNombre(miembroDetails.getNombre());
        miembro.setDni(miembroDetails.getDni());
        // Actualiza otros campos si es necesario
        return miembroRepository.save(miembro);
    }

    public void deleteById(Long id) {
        if (!miembroRepository.existsById(id)) {
            throw new RuntimeException("Miembro no encontrado con id " + id);
        }
        miembroRepository.deleteById(id);
    }

    public List<Miembro> findByNombre(String nombre) {
        return miembroRepository.findByNombre(nombre);
    }

    public Miembro findByDni(int dni) {
        return miembroRepository.findByDni(dni);
    }
}

