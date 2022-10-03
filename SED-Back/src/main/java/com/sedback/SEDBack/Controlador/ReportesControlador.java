package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.Dtos.DetalleEvaluacionComparacionCompetenciasDto;
import com.sedback.SEDBack.Dtos.DetalleEvaluacionVersusReporteDto;
import com.sedback.SEDBack.Dtos.RespuestaSituacionCompetenciaDTO;
import com.sedback.SEDBack.Dtos.SituacionCompetenciaEnPeriodoDTO;
import com.sedback.SEDBack.Logica.DetalleEvaluacionServicio;
import com.sedback.SEDBack.Logica.EvaluacionServicio;
import com.sedback.SEDBack.Modelo.Competencia;
import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") //Para poder acceder desde cualquier lado a mi backend
@RestController
@RequestMapping("/reportes")
public class ReportesControlador {
    @Autowired
    private DetalleEvaluacionServicio detalleEvaluacionServicio;
    
    @Autowired
    private EvaluacionServicio evaluacionServicio;
    
    @GetMapping("/versus_grado_minimo/detalle_evaluacion/{id_detalle_evaluacion}")
    public DetalleEvaluacionVersusReporteDto getDetalleEvaluacionByIdParaVersusReporte(@PathVariable(value="id_detalle_evaluacion") Long id_detalle_evaluacion){
        return detalleEvaluacionServicio.getDetalleEvaluacionByIdParaVersusReporte(id_detalle_evaluacion);
    }
    
    @PostMapping("/comparacion_de_evaluaciones_por_competencia/{id_evaluacion}")
    public List<DetalleEvaluacionComparacionCompetenciasDto> getDetalleEvaluacionByIdEvaluacionParaComparacionReporte(@PathVariable(value="id_evaluacion") Long id_evaluacion,@RequestBody Competencia comp){
        return detalleEvaluacionServicio.getDetalleEvaluacionByIdEvaluacionParaComparacionReporte(id_evaluacion, comp);
    }
    
    @PostMapping("/situacion_competencia_en_periodo")
    @PreAuthorize("hasAuthority('Administrador')")
    public RespuestaSituacionCompetenciaDTO situacionCompetenciaEnPeriodo(@RequestBody SituacionCompetenciaEnPeriodoDTO scp){
        return evaluacionServicio.situacionCompetenciaEnPeriodo(scp);
    }
}
