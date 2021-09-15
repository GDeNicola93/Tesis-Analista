package com.sedback.SEDBack.Controlador;

import com.fasterxml.jackson.annotation.JsonView;
import com.sedback.SEDBack.Dtos.DetalleEvaluacionDto;
import com.sedback.SEDBack.Dtos.EvaluacionEvaluadorIndexDto;
import com.sedback.SEDBack.Dtos.EvaluacionIndexDto;
import com.sedback.SEDBack.Dtos.EvaluacionVerDto;
import com.sedback.SEDBack.Dtos.EvaluarIndexDto;
import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Dtos.MisEvaluacionesDto;
import com.sedback.SEDBack.Dtos.NuevaEvaluacionDto;
import com.sedback.SEDBack.Excepciones.InvalidDataException;
import com.sedback.SEDBack.Logica.DetalleEvaluacionServicio;
import com.sedback.SEDBack.Logica.EvaluacionServicio;
import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import com.sedback.SEDBack.Modelo.Evaluacion;
import com.sedback.SEDBack.Modelo.PlantillaEvaluacion;
import com.sedback.SEDBack.Seguridad.UsuarioPrincipal;
import com.sedback.SEDBack.Views.Views;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") //Para poder acceder desde cualquier lado a mi backend
@RestController
@RequestMapping("/evaluacion")
public class EvaluacionControlador {
    @Autowired
    private EvaluacionServicio servicio;
    
    @Autowired
    private DetalleEvaluacionServicio detalleEvaluacionServicio;
    
    @PostMapping()
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<HttpMensaje> guardar(@Valid @RequestBody NuevaEvaluacionDto evaluacion,BindingResult result){
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new HttpMensaje(servicio.guardar(evaluacion)));
    }
    
    @GetMapping()
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<Page<EvaluacionIndexDto>> getEvaluaciones(Pageable page){
        return servicio.getEvaluaciones(page);
    }
    
    @GetMapping(params = {"id"})
    @PreAuthorize("hasAuthority('Administrador')" + "|| hasAuthority('Evaluador')")
    public ResponseEntity<EvaluacionVerDto> getEvaluacionById(Long id,@RequestHeader("authorization") String bearer){
        String token = bearer.replace("Bearer ", "");
        return ResponseEntity.status(HttpStatus.OK).body(servicio.getEvaluacionById(id,token));
    }
    
    @GetMapping("/cancelar/{id_evaluacion}")
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<HttpMensaje> cancelarEvaluacion(@PathVariable(value="id_evaluacion") Long id_evaluacion){
        return servicio.cancelarEvaluacion(id_evaluacion);
    }
    
    //Endpoints Evaluador
    
    @GetMapping("/evaluador/logeado")
    @PreAuthorize("hasAuthority('Evaluador')")
    public ResponseEntity<Page<EvaluacionEvaluadorIndexDto>> getEvaluacionesEvaluadorLogeado(@RequestHeader("authorization") String bearer,Pageable page){
        String token = bearer.replace("Bearer ", "");
        return servicio.getEvaluacionesEvaluadorLogeado(token, page);
    }
    
    @GetMapping("/empleados_a_evaluar/{id_evaluacion}")
    @PreAuthorize("hasAuthority('Evaluador')")
    public ResponseEntity<List<EvaluarIndexDto>> getEmpleadosAEvaluarEvaluacion(@PathVariable(value="id_evaluacion") Long id_evaluacion,UsernamePasswordAuthenticationToken principal){
        UsuarioPrincipal userLogeado = (UsuarioPrincipal) principal.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(servicio.getEmpleadosAEvaluarEvaluacion(id_evaluacion,userLogeado.getId()));
    }
    
    @GetMapping("/detalle_evaluacion/{id_detalle_evaluacion}")
    @PreAuthorize("hasAuthority('Administrador')" + "|| hasAuthority('Evaluador')" + "|| hasAuthority('Empleado')")
    public ResponseEntity<DetalleEvaluacionDto> getDetalleEvaluacionById(@PathVariable(value="id_detalle_evaluacion") Long id_detalle_evaluacion,UsernamePasswordAuthenticationToken principal){
        UsuarioPrincipal userLogeado = (UsuarioPrincipal) principal.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(servicio.getDetalleEvaluacionById(id_detalle_evaluacion,userLogeado.getId()));
    }
    
    @GetMapping("/evaluar/{id_detalle_evaluacion}")
    @PreAuthorize("hasAuthority('Evaluador')")
    public ResponseEntity<PlantillaEvaluacion> getEvaluarEmpleado(@PathVariable(value="id_detalle_evaluacion") Long id_detalle_evaluacion,UsernamePasswordAuthenticationToken principal){
        UsuarioPrincipal userLogeado = (UsuarioPrincipal) principal.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(servicio.getEvaluarEmpleado(id_detalle_evaluacion,userLogeado.getId()));
    }
    
    //Endpoints Empleado
    
    @GetMapping("/mis_evaluaciones")
    public ResponseEntity<Page<MisEvaluacionesDto>> getMisEvaluaciones(UsernamePasswordAuthenticationToken principal,Pageable page){
        UsuarioPrincipal userLogeado = (UsuarioPrincipal) principal.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(detalleEvaluacionServicio.getMisEvaluaciones(userLogeado.getId(),page));
        
    }
    
}
