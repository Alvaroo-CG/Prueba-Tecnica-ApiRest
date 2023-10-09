package com.ticarum.apirest.aeropuerto.repositories;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ticarum.apirest.aeropuerto.models.Vuelo;

public interface IVueloRepository extends JpaRepository<Vuelo, Long>{

	@Query("SELECT v FROM Vuelo v WHERE v.pendienteDespegar = true")
    List<Vuelo> obtenerVuelosPendientesDespegar();
	
	@Query("SELECT v FROM Vuelo v WHERE v.pendienteDespegar = false")
    List<Vuelo> obtenerVuelosDespegados();
	
	@Query("SELECT v.horaSalida FROM Vuelo v WHERE v.id = :id")
	LocalTime obtenerHoraSalidaPorId(@Param("id") Long id);
}
