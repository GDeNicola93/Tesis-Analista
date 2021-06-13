package com.sedback.SEDBack.Modelo;

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

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Sucursal implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(unique = true)
	private String nombre;
        
        @SuppressWarnings("unused")
	private String descripcion;
	
	@ManyToMany
	private Set<Area> areas = new HashSet<>();
        
        public boolean tieneArea(Area area){
            return this.getAreas().stream().anyMatch(a -> (a.equals(area)));
        }
}
