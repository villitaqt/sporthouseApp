package com.example.api.service;

import com.example.api.entity.Membresia;
import com.example.api.entity.Miembro;
import com.example.api.entity.TipoMembresia;
import com.example.api.repository.MiembroRepository;
import com.example.api.repository.TipoMembresiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiembroService {

    @Autowired
    private MiembroRepository miembroRepository;

    @Autowired
    private TipoMembresiaRepository tipoMembresiaRepository;

    public List<Miembro> findAll() {
        return miembroRepository.findAll();
    }

    public Miembro findByIdOrThrow(Long id) {
        return miembroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado con id " + id));
    }

    public Miembro createMiembro(Miembro miembro) {
        // Buscar el tipo de membresía
        Long tipoMembresiaId = miembro.getMembresia().getTipoMembresia().getId();
        TipoMembresia tipoMembresia = tipoMembresiaRepository.findById(tipoMembresiaId)
                .orElseThrow(() -> new RuntimeException("Tipo de Membresía no encontrada con id: " + tipoMembresiaId));

        // Asignar el tipo de membresía a la membresía del miembro
        Membresia membresia = miembro.getMembresia();
        membresia.setTipoMembresia(tipoMembresia);
        miembro.setMembresia(membresia);

        // Guardar el miembro con su membresía
        return miembroRepository.save(miembro);
    }

    public Miembro updateMiembro(Long id, Miembro miembroDetails) {
        Miembro miembro = findByIdOrThrow(id);
        miembro.setNombre(miembroDetails.getNombre());
        miembro.setDni(miembroDetails.getDni());
        miembro.setMembresia(miembroDetails.getMembresia());
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

