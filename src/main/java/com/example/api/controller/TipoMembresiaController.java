package com.example.api.controller;

import com.example.api.entity.Miembro;
import com.example.api.entity.TipoMembresia;
import com.example.api.service.TipoMembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-membresia")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoMembresiaController {

    @Autowired
    private TipoMembresiaService tipoMembresiaService;

    @GetMapping
    public List<TipoMembresia> getAllTiposMembresia() {
        return tipoMembresiaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoMembresia> getTipoMembresiaById(@PathVariable Long id) {
        TipoMembresia tipoMembresia = tipoMembresiaService.findById(id);
        return ResponseEntity.ok(tipoMembresia);
    }

    @PostMapping
    public TipoMembresia createTipoMembresia(@RequestBody TipoMembresia tipoMembresia) {
        return tipoMembresiaService.save(tipoMembresia);
    }

    @PutMapping("/{id}")
    public TipoMembresia updateTipoMembresia(@PathVariable Long id, @RequestBody TipoMembresia tipoMembresiaDetails) {
        TipoMembresia tipoMembresia = tipoMembresiaService.findById(id);

        tipoMembresia.setNombre(tipoMembresiaDetails.getNombre());
        tipoMembresia.setPrecio(tipoMembresiaDetails.getPrecio());
        tipoMembresia.setMes(tipoMembresiaDetails.getMes()); // Añade esta línea para actualizar el campo 'mes'

        return tipoMembresiaService.save(tipoMembresia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoMembresia(@PathVariable Long id) {
        tipoMembresiaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
