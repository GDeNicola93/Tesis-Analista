import { Component, OnInit } from '@angular/core';
import { EvaluacionIndexDto } from 'src/app/HttpMensajes/evaluacion-index-dto';
import { EvaluacionService } from 'src/app/servicios/evaluacion.service';

@Component({
  selector: 'app-index-evaluacion',
  templateUrl: './index-evaluacion.component.html',
  styleUrls: ['./index-evaluacion.component.css']
})
export class IndexEvaluacionComponent implements OnInit {
  evaluaciones : EvaluacionIndexDto[];
  page : number = 0;
  size : number = 10;
  sort : string = 'id'
  order : string = 'asc';
  esUltima : boolean = false;
  esPrimera : boolean = false;
  totalPages : Array<number>;


  constructor(private evaluacionServicio : EvaluacionService) { }

  ngOnInit(): void {
    this.cargarEvaluaciones();
  }

  cargarEvaluaciones() : void{
    this.evaluacionServicio.getEvaluaciones(this.page,this.size,this.sort,this.order).subscribe(data =>{
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
