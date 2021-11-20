package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.Dtos.DetalleEvaluacionVersusReporteDto;
import com.sedback.SEDBack.Logica.DetalleEvaluacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") //Para poder acceder desde cualquier lado a mi backend
@RestController
@RequestMapping("/reportes")
public class ReportesControlador {
    @Autowired
    private DetalleEvaluacionServicio detalleEvaluacionServicio;
    
    @GetMapping("/versus_grado_minimo/detalle_evaluacion/{id_detalle_evaluacion}")
    public DetalleEvaluacionVersusReporteDto getDetalleEvaluacionByIdParaVersusReporte(@PathVariable(value="id_detalle_evaluacion") Long id_detalle_evaluacion){
        return detalleEvaluacionServicio.getDetalleEvaluacionByIdParaVersusReporte(id_detalle_evaluacion);
    }
}
