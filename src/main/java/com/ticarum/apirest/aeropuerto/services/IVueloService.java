package com.ticarum.apirest.aeropuerto.services;

import java.time.LocalTime;
import java.util.List;

import com.ticarum.apirest.aeropuerto.models.Vuelo;

public interface IVueloService {
	List<Vuelo> obtenerVuelosPendientesDespegar();
	List<Vuelo> obtenerVuelosDespegados();
    Vuelo agregarNuevoVuelo(Vuelo nuevoVuelo);
    
    Vuelo obtenerVueloPorId(Long id);
    Vuelo editarVueloPorId(Long id, Vuelo vueloActualizado);
    void eliminarVueloPorId(Long id);
    
    LocalTime obtenerHoraSalidaPorId(Long id);
    void marcarVueloComoDespegado(Long idVuelo);
}
