package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.HttpMensajes.HttpMensaje;
import com.sedback.SEDBack.Logica.CompetenciaServicio;
import com.sedback.SEDBack.Modelo.Competencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<HttpMensaje> guardar(@RequestBody Competencia competencia){
        return servicio.guardar(competencia);
    }
    
//    @GetMapping
//    @PreAuthorize("hasAuthority('Administrador')")
//    public ResponseEntity<Page<Competencia>> obtenerCompetencias(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "nombre") String order){
//        return servicio.obtenerCompetencias(PageRequest.of(page,size,Sort.by(order)));
//    }
    @GetMapping
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<Page<Competencia>> obtenerCompetencias(Pageable page){
        return servicio.obtenerCompetencias(page);
    }
}
