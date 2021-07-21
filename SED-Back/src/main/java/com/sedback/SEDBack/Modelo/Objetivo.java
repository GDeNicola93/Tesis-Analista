package com.sedback.SEDBack.Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;
import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Objetivo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String descripcion;
	
	@NotNull
	private boolean enCurso;
        
        public void sacarDeCurso(){
            this.setEnCurso(false);
        }
}
