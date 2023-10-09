package com.ticarum.apirest.aeropuerto.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticarum.apirest.aeropuerto.models.Aerolinea;
import com.ticarum.apirest.aeropuerto.models.Vuelo;
import com.ticarum.apirest.aeropuerto.services.AerolineaService;
import com.ticarum.apirest.aeropuerto.services.IVueloService;

@RestController
@RequestMapping("/{aerolineaNombre}/services")
public class HomeController {
	
	private final AerolineaService aerolineaService;
	private final IVueloService vueloService;

    @Autowired
    public HomeController(AerolineaService aerolineaService, IVueloService vueloService) {
        this.aerolineaService = aerolineaService;
        this.vueloService = vueloService;
    }

    @GetMapping("/info")
    public ResponseEntity<Aerolinea> obtenerInformacionAerolinea(
            @PathVariable("aerolineaNombre") String aerolineaNombre) {
        Optional<Aerolinea> aerolineaOptional = aerolineaService.obtenerInformacionPorNombre(aerolineaNombre);

        if (aerolineaOptional.isPresent()) {
            return ResponseEntity.ok(aerolineaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    
    @GetMapping("/vuelo")
    public ResponseEntity<List<Vuelo>> obtenerVuelosPendientesDespegar(
            @PathVariable("aerolineaNombre") String aerolineaNombre) {
        List<Vuelo> vuelosPendientes = vueloService.obtenerVuelosPendientesDespegar();
        return new ResponseEntity<>(vuelosPendientes, HttpStatus.OK);
    }

    @PostMapping("/vuelo")
    public ResponseEntity<Vuelo> agregarNuevoVuelo(
            @PathVariable("aerolineaNombre") String aerolineaNombre,
            @RequestBody Vuelo nuevoVuelo) {
        Vuelo vueloAgregado = vueloService.agregarNuevoVuelo(nuevoVuelo);
        return new ResponseEntity<>(vueloAgregado, HttpStatus.CREATED);
    }
    
    
     
    @GetMapping("/vuelo/{idVuelo}")
    public ResponseEntity<Vuelo> obtenerVueloPorId(
            @PathVariable("aerolineaNombre") String aerolineaNombre,
            @PathVariable("idVuelo") Long idVuelo) {
        Vuelo vuelo = vueloService.obtenerVueloPorId(idVuelo);
        if (vuelo != null) {
            return ResponseEntity.ok(vuelo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/vuelo/{idVuelo}")
    public ResponseEntity<Vuelo> editarVueloPorId(
            @PathVariable("aerolineaNombre") String aerolineaNombre,
            @PathVariable("idVuelo") Long idVuelo,
            @RequestBody Vuelo vueloActualizado) {
        Vuelo vueloEditado = vueloService.editarVueloPorId(idVuelo, vueloActualizado);
        if (vueloEditado != null) {
            return ResponseEntity.ok(vueloEditado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/vuelo/{idVuelo}")
    public ResponseEntity<Void> eliminarVueloPorId(
            @PathVariable("aerolineaNombre") String aerolineaNombre,
            @PathVariable("idVuelo") Long idVuelo) {
        vueloService.eliminarVueloPorId(idVuelo);
        return ResponseEntity.noContent().build();
    }
    
    
    
    @GetMapping("/salida")
    public ResponseEntity<List<Vuelo>> obtenerVuelosDespegado(
            @PathVariable("aerolineaNombre") String aerolineaNombre) {
        List<Vuelo> vuelosDespegados = vueloService.obtenerVuelosDespegados();
        return new ResponseEntity<>(vuelosDespegados, HttpStatus.OK);
    }
    
    
    
    @GetMapping("/salida/{idVuelo}")
    public ResponseEntity<Map<String, Object>> obtenerSalidaVuelo(
            @PathVariable("aerolineaNombre") String aerolineaNombre,
            @PathVariable Long idVuelo) {
        Vuelo vuelo = vueloService.obtenerVueloPorId(idVuelo);

        if (vuelo != null && vuelo.getHoraSalida() != null) {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("idVuelo", vuelo.getId());
            respuesta.put("pendienteSalir", vuelo.isPendienteDespegar());
            respuesta.put("horaSalida", vuelo.getHoraSalida());

            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    
    @PutMapping("/salida/{idVuelo}/despegue")
    public ResponseEntity<Void> marcarVueloComoDespegado(@PathVariable Long idVuelo) {
        vueloService.marcarVueloComoDespegado(idVuelo);
        return ResponseEntity.noContent().build();
    }

}
