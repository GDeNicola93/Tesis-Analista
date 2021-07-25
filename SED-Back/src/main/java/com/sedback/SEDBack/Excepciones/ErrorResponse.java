package com.sedback.SEDBack.Excepciones;

import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private int status;
  
    private String message;

    private Date timestamp;

    List<String> errors;

    ErrorResponse(String message) {
      this.message = message;
    }
}
