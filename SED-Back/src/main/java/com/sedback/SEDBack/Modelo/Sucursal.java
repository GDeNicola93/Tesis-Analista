package com.sedback.SEDBack.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.sedback.SEDBack.Views.Views;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.List;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Sucursal implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonView(Views.Resumida.class)
	private Integer id;
	
	@NotNull
	@Column(unique = true)
        @JsonView(Views.Resumida.class)
	private String nombre;
        
        @SuppressWarnings("unused")
	private String descripcion;
	
	@ManyToMany
	private Set<Area> areas = new HashSet<>();
        
        @OneToMany(mappedBy = "sucursal")
        @JsonIgnore
        private List<EspecificacionDePuesto> especificacionesPuestos;
        
        public boolean tieneArea(Area area){
            return this.getAreas().stream().anyMatch(a -> (a.equals(area)));
        }
}
