import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PlantillaEvaluacion } from 'src/app/modelo/plantilla-evaluacion';
import { EvaluacionService } from 'src/app/servicios/evaluacion.service';

@Component({
  selector: 'app-evaluar',
  templateUrl: './evaluar.component.html',
  styleUrls: ['./evaluar.component.css']
})
export class EvaluarComponent implements OnInit {
  idDetalleEvaluacion : number;
  plantillaEvaluacion : PlantillaEvaluacion;
  mensaje = '';
  error = false;

  constructor(private rutaActiva: ActivatedRoute,private evaluacionServicio : EvaluacionService) { }

  ngOnInit(): void {
    this.idDetalleEvaluacion = this.rutaActiva.snapshot.params.id_detalle;
    this.getPlantillaEvaluacion();
  }

  getPlantillaEvaluacion() : void{
    this.error = false;
    this.evaluacionServicio.getEvaluarEmpleado(this.idDetalleEvaluacion).subscribe(data =>{
      this.plantillaEvaluacion = data;
    },
      (err : any)=>{
        this.error = true;
        this.mensaje = err.error.message;
      }
    );
  }

}
