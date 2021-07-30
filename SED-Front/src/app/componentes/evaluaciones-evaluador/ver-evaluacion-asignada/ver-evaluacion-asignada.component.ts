import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EvaluacionVerDto } from 'src/app/HttpMensajes/evaluacion-ver-dto';
import { EvaluacionService } from 'src/app/servicios/evaluacion.service';

@Component({
  selector: 'app-ver-evaluacion-asignada',
  templateUrl: './ver-evaluacion-asignada.component.html',
  styleUrls: ['./ver-evaluacion-asignada.component.css']
})
export class VerEvaluacionAsignadaComponent implements OnInit {
  evaluacion : EvaluacionVerDto;
  mensaje = '';
  error = false;


  constructor(private rutaActiva: ActivatedRoute,private evaluacionServicio : EvaluacionService) { }

  ngOnInit(): void {
    this.getEvaluacionById();
  }

  getEvaluacionById() : void{
    this.error = false;
    this.evaluacionServicio.getEvaluacionById(this.rutaActiva.snapshot.params.id).subscribe(data =>{
      this.evaluacion = data;
    },
      (err : any)=>{
        this.error = true;
        this.mensaje = err.error.message;
      }
    );
  }

}
