package com.ticarum.apirest.aeropuerto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticarum.apirest.aeropuerto.models.Aerolinea;
import com.ticarum.apirest.aeropuerto.repositories.IAerolineaRepository;

@Service
public class AerolineaService {
	private final IAerolineaRepository aerolineaRepository;

    @Autowired
    public AerolineaService(IAerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }

    public Optional<Aerolinea> obtenerInformacionPorNombre(String nombre) {
        return aerolineaRepository.findByNombre(nombre);
    }
}
