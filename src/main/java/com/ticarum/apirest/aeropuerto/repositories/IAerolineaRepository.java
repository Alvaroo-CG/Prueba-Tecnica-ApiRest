package com.ticarum.apirest.aeropuerto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticarum.apirest.aeropuerto.models.Aerolinea;

public interface IAerolineaRepository extends JpaRepository<Aerolinea, Long>{
	Optional<Aerolinea> findByNombre(String nombre);
}
