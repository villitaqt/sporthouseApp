package com.example.api.service;

import com.example.api.config.ResourceNotFoundException;
import com.example.api.entity.TipoMembresia;
import com.example.api.repository.TipoMembresiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoMembresiaService {

    @Autowired
    private TipoMembresiaRepository tipoMembresiaRepository;

    public List<TipoMembresia> findAll() {
        return tipoMembresiaRepository.findAll();
    }

    public TipoMembresia findById(Long id) {
        return tipoMembresiaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TipoMembresia no encontrado"));
    }

    public TipoMembresia save(TipoMembresia tipoMembresia) {
        return tipoMembresiaRepository.save(tipoMembresia);
    }

    public void deleteById(Long id) {
        tipoMembresiaRepository.deleteById(id);
    }

}

