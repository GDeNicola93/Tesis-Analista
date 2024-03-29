package com.sedback.SEDBack.Controlador;

import com.fasterxml.jackson.annotation.JsonView;
import com.sedback.SEDBack.Dtos.EmpleadoIndexDto;
import com.sedback.SEDBack.Dtos.EmpleadoVerDto;
import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Dtos.NuevoEmpleadoDto;
import com.sedback.SEDBack.Excepciones.InvalidDataException;
import com.sedback.SEDBack.Logica.EmpleadoServicio;
import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.Usuario;
import com.sedback.SEDBack.Views.Views;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*") //Para poder acceder desde cualquier lado a mi backend
@RestController
@RequestMapping("/empleados")
public class EmpleadoControlador {
    
    @Autowired
    private EmpleadoServicio servicio;
    
    @PostMapping
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<HttpMensaje> guardar(@Valid @RequestBody NuevoEmpleadoDto nuevoEmpleado,BindingResult result){
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new HttpMensaje(servicio.guardar(nuevoEmpleado)));
    }
      
    @GetMapping(params = {"id"})
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<EmpleadoVerDto> getEmpleadoById(Integer id){
        return servicio.getEmpleadoById(id);
    }
    
    @GetMapping(params = {"filtro"})
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<Page<EmpleadoIndexDto>> getEmpleados(String filtro,Pageable page){
        return servicio.getEmpleados(filtro,page);
    }
    
    @GetMapping("/evaluadores")
    @PreAuthorize("hasAuthority('Administrador')")
    @JsonView(Views.Resumida.class)
    public ResponseEntity<List<Empleado>> getEmpleadosEvaluadores(){
        return servicio.getEmpleadosEvaluadores();
    }

}
