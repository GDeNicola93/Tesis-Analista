import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EvaluarIndexDto } from 'src/app/HttpMensajes/evaluar-index-dto';
import { EvaluacionService } from 'src/app/servicios/evaluacion.service';

@Component({
  selector: 'app-index-evaluar',
  templateUrl: './index-evaluar.component.html',
  styleUrls: ['./index-evaluar.component.css']
})
export class IndexEvaluarComponent implements OnInit {
  detallesEvaluaciones : EvaluarIndexDto[];
  idEvaluacion : number;
  mensaje = '';
  error = false;

  constructor(private rutaActiva: ActivatedRoute,private evaluacionServicio : EvaluacionService) { }

  ngOnInit(): void {
    this.idEvaluacion = this.rutaActiva.snapshot.params.id
    this.getDetallesEvaluacion();
  }

  getDetallesEvaluacion() : void{
    this.error = false;
    this.evaluacionServicio.getEmpleadosAEvaluarEvaluacion(this.idEvaluacion).subscribe(data =>{
      this.detallesEvaluaciones = data;
    },
      (err : any)=>{
        this.error = true;
        this.mensaje = err.error.message;
      }
    );
  }

}
