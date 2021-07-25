package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Excepciones.InvalidDataException;
import com.sedback.SEDBack.Logica.CompetenciaServicio;
import com.sedback.SEDBack.Modelo.Competencia;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") //Para poder acceder desde cualquier lado a mi backend
@RestController
@RequestMapping("/competencia")
public class CompetenciaControlador {
    @Autowired
    private CompetenciaServicio servicio;
    
    @PostMapping
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<HttpMensaje> guardar(@Valid @RequestBody Competencia competencia,BindingResult result){
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new HttpMensaje(servicio.guardar(competencia)));
    }
    
    @GetMapping("/index")
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<Page<Competencia>> obtenerCompetencias(Pageable page){
        return servicio.obtenerCompetencias(page);
    }
    
    @GetMapping("/for-select")
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<List<Competencia>> obtenerCompetenciasParaSelect(){
        return servicio.obtenerCompetenciasParaSelect();
    }
}
