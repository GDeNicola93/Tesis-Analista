import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EvaluarIndexDto } from 'src/app/HttpMensajes/evaluar-index-dto';
import { EvaluacionService } from 'src/app/servicios/evaluacion.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-index-detalles-evaluacion',
  templateUrl: './index-detalles-evaluacion.component.html',
  styleUrls: ['./index-detalles-evaluacion.component.css']
})
export class IndexDetallesEvaluacionComponent implements OnInit {
  detallesEvaluaciones : EvaluarIndexDto[];
  idEvaluacion : number;
  mensaje = '';
  error = false;
  cargando = true;

  constructor(private rutaActiva: ActivatedRoute,private evaluacionServicio : EvaluacionService,private location: Location) { }

  ngOnInit(): void {
    this.idEvaluacion = this.rutaActiva.snapshot.params.id_evaluacion;
    this.getDetallesEvaluacion();
  }

  getDetallesEvaluacion() : void{
    this.error = false;
    this.evaluacionServicio.getDetallesEvaluacionByIdEvaluacion(this.idEvaluacion).subscribe(data =>{
      this.detallesEvaluaciones = data.content;
      this.cargando = false;
    },
      (err : any)=>{
        this.error = true;
        this.mensaje = err.error.message;
        this.cargando = false;
      }
    );
  }

  volver() {
    this.location.back();
  }

}
