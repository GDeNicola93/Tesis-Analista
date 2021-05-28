package com.sedback.SEDBack.Dtos;

import lombok.Data;

@Data
public class CambioPassword {
    private String passwordActual;
    private String passwordNew1;
    private String passwordNew2;
}
