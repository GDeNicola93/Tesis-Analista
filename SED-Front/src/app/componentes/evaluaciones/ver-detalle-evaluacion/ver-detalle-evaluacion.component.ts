import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DetalleEvaluacionDto } from 'src/app/HttpMensajes/detalle-evaluacion-dto';
import { EvaluacionService } from 'src/app/servicios/evaluacion.service';

@Component({
  selector: 'app-ver-detalle-evaluacion',
  templateUrl: './ver-detalle-evaluacion.component.html',
  styleUrls: ['./ver-detalle-evaluacion.component.css']
})
export class VerDetalleEvaluacionComponent implements OnInit {
  idEvaluacion : number;
  idDetalleEvaluacion : number;
  detalleEvaluacion : DetalleEvaluacionDto;
  mensaje = '';
  error = false;

  constructor(private rutaActiva: ActivatedRoute,private evaluacionServicio : EvaluacionService) { }

  ngOnInit(): void {
    this.idDetalleEvaluacion = this.rutaActiva.snapshot.params.id_detalle;
    this.idEvaluacion = this.rutaActiva.snapshot.params.id_evaluacion;
    this.getDetalleEvaluacion();
  }

  getDetalleEvaluacion() : void{
    this.error = false;
    this.evaluacionServicio.getDetalleEvaluacionById(this.idDetalleEvaluacion).subscribe(data =>{
      this.detalleEvaluacion = data;
    },
      (err : any)=>{
        this.error = true;
        this.mensaje = err.error.message;
      }
    );
  }

}
