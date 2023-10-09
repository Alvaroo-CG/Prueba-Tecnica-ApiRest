package com.ticarum.apirest.aeropuerto.services;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticarum.apirest.aeropuerto.models.Vuelo;
import com.ticarum.apirest.aeropuerto.repositories.IVueloRepository;

@Service
public class VueloServiceImpl implements IVueloService{
	private final IVueloRepository vueloRepository;

    @Autowired
    public VueloServiceImpl(IVueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    @Override
    public List<Vuelo> obtenerVuelosPendientesDespegar() {
        return vueloRepository.obtenerVuelosPendientesDespegar();
    }
    
    @Override
	public List<Vuelo> obtenerVuelosDespegados() {
    	return vueloRepository.obtenerVuelosDespegados();
	}

    @Override
    public Vuelo agregarNuevoVuelo(Vuelo nuevoVuelo) {
        nuevoVuelo.setPendienteDespegar(true);
        nuevoVuelo.setHoraInsercion(LocalTime.now());

        return vueloRepository.save(nuevoVuelo);
    }

    @Override
    public Vuelo obtenerVueloPorId(Long id) {
        return vueloRepository.findById(id).orElse(null);
    }

    @Override
    public Vuelo editarVueloPorId(Long id, Vuelo vueloActualizado) {
        Vuelo vueloExistente = vueloRepository.findById(id).orElse(null);
        if (vueloExistente != null) {

            vueloExistente.setAerolinea(vueloActualizado.getAerolinea());
            vueloExistente.setAvion(vueloActualizado.getAvion());
            vueloExistente.setHoraInsercion(vueloActualizado.getHoraInsercion());
            vueloExistente.setHoraSalida(vueloActualizado.getHoraSalida());
            vueloExistente.setOrigen(vueloActualizado.getOrigen());
            vueloExistente.setDestino(vueloActualizado.getDestino());
            vueloExistente.setPendienteDespegar(vueloActualizado.isPendienteDespegar());

            vueloRepository.save(vueloExistente);
        }
        return vueloExistente;
    }

    @Override
    public void eliminarVueloPorId(Long id) {
        vueloRepository.deleteById(id);
    }

    @Override
    public LocalTime obtenerHoraSalidaPorId(Long idVuelo) {
        Optional<Vuelo> vueloOptional = vueloRepository.findById(idVuelo);
        if (vueloOptional.isPresent()) {
            Vuelo vuelo = vueloOptional.get();
            return vuelo.getHoraSalida();
        }
        return null;
    }
    
    @Override
    public void marcarVueloComoDespegado(Long idVuelo) {
        Optional<Vuelo> vueloOptional = vueloRepository.findById(idVuelo);

        if (vueloOptional.isPresent()) {
            Vuelo vuelo = vueloOptional.get();
            vuelo.setPendienteDespegar(false); // Marca el vuelo como despegado
            vuelo.setHoraSalida(LocalTime.now()); //Indica la hora exacta en la finalmente salió el vuelo
            
            vueloRepository.save(vuelo);
  
        }
    }	
    
}
