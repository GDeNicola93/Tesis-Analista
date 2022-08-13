package com.sedback.SEDBack.Excepciones;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ConfigException {
 
  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(InvalidDataException exc) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    List<String> errors = exc.getResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
        .collect(Collectors.toList());
    return buildResponseEntity(httpStatus, new RuntimeException("La información enviada no es valida"), errors);
  }
  
  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(PermissionException exc){
    HttpStatus httpStatus = HttpStatus.FORBIDDEN;
    return buildResponseEntity(httpStatus, new RuntimeException(exc.getMessage()),null);  
  }
  
  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(FueraDeCursoException exc){
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    return buildResponseEntity(httpStatus, new RuntimeException(exc.getMessage()),null);  
  }
  
  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(FueEvaluadoException exc){
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    return buildResponseEntity(httpStatus, new RuntimeException(exc.getMessage()),null);  
  }
  
  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(NotFoundException exc){
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    return buildResponseEntity(httpStatus, new RuntimeException(exc.getMessage()),null);  
  }
  
  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(NoSuchElementException exc){
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    return buildResponseEntity(httpStatus, new RuntimeException("La información solicitada no existe."),null);  
  }
  
  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(BadRequestException exc){
      HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
      return buildResponseEntity(httpStatus, new RuntimeException(exc.getMessage()),null);
  }
  
  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(NoEditableException exc){
      HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
      return buildResponseEntity(httpStatus, new RuntimeException(exc.getMessage()),null);
  }
  
  private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exc) {
    return buildResponseEntity(httpStatus, exc, null);
  }

  private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exc, List<String> errors) {
    ErrorResponse error = new ErrorResponse();
    error.setMessage(exc.getMessage());
    error.setStatus(httpStatus.value());
    error.setTimestamp(new Date());
    error.setErrors(errors);
    return new ResponseEntity<>(error, httpStatus);

  }
}
