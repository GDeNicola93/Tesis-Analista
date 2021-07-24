import { Component, OnInit } from '@angular/core';
import { EvaluacionEvaluadorIndexDto } from 'src/app/HttpMensajes/evaluacion-evaluador-index-dto';
import { EvaluacionService } from 'src/app/servicios/evaluacion.service';

@Component({
  selector: 'app-evaluaciones-asignadas',
  templateUrl: './evaluaciones-asignadas.component.html',
  styleUrls: ['./evaluaciones-asignadas.component.css']
})
export class EvaluacionesAsignadasComponent implements OnInit {
  evaluaciones : EvaluacionEvaluadorIndexDto[];
  page : number = 0;
  size : number = 10;
  sort : string = 'fechaInicioEvaluacion'
  order : string = 'desc';
  esUltima : boolean = false;
  esPrimera : boolean = false;
  totalPages : Array<number>;

  constructor(private evaluacionServicio : EvaluacionService) { }

  ngOnInit(): void {
    this.cargarEvaluaciones();
  }

  cargarEvaluaciones() : void{
    this.evaluacionServicio.getEvaluacionesEvaluadorLogeado(this.page,this.size,this.sort,this.order).subscribe(data => {
      this.evaluaciones = data.content;
      this.esPrimera = data.first;
      this.esUltima = data.last;
      this.totalPages = new Array(data['totalPages']);
    });
  }

  anterior() : void{
    if(!this.esPrimera){
      this.page--;
      this.cargarEvaluaciones();
    }
  }

  siguiente() : void{
    if(!this.esUltima){
      this.page++;
      this.cargarEvaluaciones();
    }
  }

  setPagina(pag : number) : void{
    this.page = pag;
    this.cargarEvaluaciones();
  }

  setSort(sort : string){
    this.sort = sort;
    this.cargarEvaluaciones();
  }

  setOrder() : void{
    if(this.order === 'asc'){
      this.order = 'desc';
    }else{
      this.order = 'asc';
    }
    this.cargarEvaluaciones();
  }

}
