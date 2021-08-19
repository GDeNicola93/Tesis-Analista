import { Component, OnInit } from '@angular/core';
import { MisEvaluacionesDto } from 'src/app/HttpMensajes/mis-evaluaciones-dto';
import { EvaluacionService } from 'src/app/servicios/evaluacion.service';

@Component({
  selector: 'app-mis-evaluaciones',
  templateUrl: './mis-evaluaciones.component.html',
  styleUrls: ['./mis-evaluaciones.component.css']
})
export class MisEvaluacionesComponent implements OnInit {
  misEvaluaciones : MisEvaluacionesDto[];
  page : number = 0;
  size : number = 10;
  sort : string = 'id'
  order : string = 'desc';
  esUltima : boolean = false;
  esPrimera : boolean = false;
  totalPages : Array<number>;

  constructor(private evaluacionServicio : EvaluacionService) { }

  ngOnInit(): void {
    this.cargarMisEvaluaciones();
  }

  cargarMisEvaluaciones() : void{
    this.evaluacionServicio.getMisEvaluaciones(this.page,this.size,this.sort,this.order).subscribe(data => {
      this.misEvaluaciones = data.content;
      this.esPrimera = data.first;
      this.esUltima = data.last;
      this.totalPages = new Array(data['totalPages']);
    });
  }

  anterior() : void{
    if(!this.esPrimera){
      this.page--;
      this.cargarMisEvaluaciones();
    }
  }

  siguiente() : void{
    if(!this.esUltima){
      this.page++;
      this.cargarMisEvaluaciones();
    }
  }

  setPagina(pag : number) : void{
    this.page = pag;
    this.cargarMisEvaluaciones();
  }

  setSort(sort : string){
    this.sort = sort;
    this.cargarMisEvaluaciones();
  }

  setOrder() : void{
    if(this.order === 'asc'){
      this.order = 'desc';
    }else{
      this.order = 'asc';
    }
    this.cargarMisEvaluaciones();
  }

}
