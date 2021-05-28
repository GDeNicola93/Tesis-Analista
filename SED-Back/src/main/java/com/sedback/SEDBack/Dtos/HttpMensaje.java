/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ususario
 */
@Data
public class HttpMensaje {
    private String mensaje;

    public HttpMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
