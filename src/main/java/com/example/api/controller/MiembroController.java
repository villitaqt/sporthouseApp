package com.example.api.controller;

import com.example.api.config.ResourceNotFoundException;
import com.example.api.entity.Membresia;
import com.example.api.entity.Miembro;
import com.example.api.service.MembresiaService;
import com.example.api.service.MiembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/miembros")
@CrossOrigin(origins = "http://localhost:4200")
public class MiembroController {

    @Autowired
    private MiembroService miembroService;

    @Autowired
    private MembresiaService membresiaService;

    @GetMapping
    public List<Miembro> getAllMiembros() {
        return miembroService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Miembro> getMiembroById(@PathVariable Long id) {
        Miembro miembro = miembroService.findByIdOrThrow(id);
        return ResponseEntity.ok(miembro);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Miembro>> findByNombre(@RequestParam String nombre) {
        List<Miembro> miembros = miembroService.findByNombre(nombre);
        if (miembros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(miembros);
    }

    @GetMapping("/buscar/{dni}")
    public ResponseEntity<Miembro> findByDni(@PathVariable int dni) {
        Miembro miembro = miembroService.findByDni(dni);
        if (miembro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(miembro);
    }

    @PostMapping
    public ResponseEntity<Miembro> createMiembro(@RequestBody Miembro miembro) {
        Miembro newMiembro = miembroService.createMiembro(miembro);
        return ResponseEntity.ok(newMiembro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Miembro> updateMiembro(@PathVariable Long id, @RequestBody Miembro miembroDetails) {
        try {
            Miembro miembroToUpdate = miembroService.updateMiembro(id, miembroDetails);
            return ResponseEntity.ok(miembroToUpdate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMiembro(@PathVariable Long id) {
        try {
            miembroService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
