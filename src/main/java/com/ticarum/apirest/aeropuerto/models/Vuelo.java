package com.ticarum.apirest.aeropuerto.models;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vuelos")
public class Vuelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "aerolinea_id", referencedColumnName = "id")
	private Aerolinea aerolinea;

	@OneToOne
	@JoinColumn(name = "avion_id", referencedColumnName = "id")
	private Avion avion;

	private LocalTime horaInsercion;
	
	private String origen;
	
	private String destino;
	
	private LocalTime horaSalida;

	private boolean pendienteDespegar;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aerolinea getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public LocalTime getHoraInsercion() {
		return horaInsercion;
	}

	public void setHoraInsercion(LocalTime horaInsercion) {
		this.horaInsercion = horaInsercion;
	}
	
	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public LocalTime getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
	}
	
	public boolean isPendienteDespegar() {
		return pendienteDespegar;
	}

	public void setPendienteDespegar(boolean pendienteDespegar) {
		this.pendienteDespegar = pendienteDespegar;
	}

}
