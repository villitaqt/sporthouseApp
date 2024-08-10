package com.example.api.repository;

import com.example.api.entity.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiembroRepository extends JpaRepository<Miembro, Long> {
    List<Miembro> findByNombre(String nombre);
    Miembro findByDni(int dni);
}
