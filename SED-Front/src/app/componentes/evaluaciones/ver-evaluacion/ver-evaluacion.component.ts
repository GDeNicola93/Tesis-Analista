import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EvaluacionVerDto } from 'src/app/HttpMensajes/evaluacion-ver-dto';
import { EvaluacionService } from 'src/app/servicios/evaluacion.service';

@Component({
  selector: 'app-ver-evaluacion',
  templateUrl: './ver-evaluacion.component.html',
  styleUrls: ['./ver-evaluacion.component.css']
})
export class VerEvaluacionComponent implements OnInit {
  cargando = true;
  evaluacion : EvaluacionVerDto;

  constructor(private rutaActiva: ActivatedRoute,private evaluacionServicio : EvaluacionService) { }

  ngOnInit(): void {
    this.getEvaluacionById();
  }

  getEvaluacionById() : void{
    this.evaluacionServicio.getEvaluacionById(this.rutaActiva.snapshot.params.id).subscribe(data =>{
      this.evaluacion = data;
      this.cargando = false;
    });

  }

}
