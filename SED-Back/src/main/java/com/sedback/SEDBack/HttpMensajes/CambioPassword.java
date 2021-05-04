package com.sedback.SEDBack.HttpMensajes;

import lombok.Data;

@Data
public class CambioPassword {
    private String passwordActual;
    private String passwordNew1;
    private String passwordNew2;
}
