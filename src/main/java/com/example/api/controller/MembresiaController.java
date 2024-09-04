package com.example.api.controller;

import com.example.api.entity.Membresia;
import com.example.api.service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membresias")
@CrossOrigin(origins = "http://localhost:4200")
public class MembresiaController {

    @Autowired
    private MembresiaService membresiaService;

    @GetMapping
    public ResponseEntity<List<Membresia>> getAllMembresias() {
        List<Membresia> membresias = membresiaService.findAll();
        return ResponseEntity.ok(membresias);
    }

    @PostMapping
    public Membresia createMembresia(@RequestBody Membresia membresia) {
        return membresiaService.save(membresia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membresia> updateMembresia(@PathVariable Long id, @RequestBody Membresia membresiaDetails) {
        Membresia updatedMembresia = membresiaService.update(id, membresiaDetails);
        return ResponseEntity.ok(updatedMembresia);
    }
}
