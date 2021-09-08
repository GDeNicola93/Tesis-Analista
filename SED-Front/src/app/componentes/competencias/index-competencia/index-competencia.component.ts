import { Component, OnInit } from '@angular/core';
import { Competencia } from 'src/app/modelo/competencia';
import { CompetenciaService } from 'src/app/servicios/competencia.service';

@Component({
  selector: 'app-index-competencia',
  templateUrl: './index-competencia.component.html',
  styleUrls: ['./index-competencia.component.css']
})
export class IndexCompetenciaComponent implements OnInit {
  competencias : Competencia[];
  page : number = 0;
  size : number = 10;
  sort : string = 'id'
  order : string = 'asc';
  esUltima : boolean = false;
  esPrimera : boolean = false;
  totalPages : Array<number>;
  cargando = true;

  constructor(private competenciaServicio : CompetenciaService) { }

  ngOnInit(): void {
    this.cargarCompetencias();
  }

  cargarCompetencias() : void{
    this.competenciaServicio.obtenerCompetencias(this.page,this.size,this.sort,this.order).subscribe(data => {
      this.competencias = data.content;
      this.esPrimera = data.first;
      this.esUltima = data.last;
      this.totalPages = new Array(data['totalPages']);
      this.cargando = false;
    });
  }

  anterior() : void{
    if(!this.esPrimera){
      this.page--;
      this.cargarCompetencias();
    }
  }

  siguiente() : void{
    if(!this.esUltima){
      this.page++;
      this.cargarCompetencias();
    }
  }

  setPagina(pag : number) : void{
    this.page = pag;
    this.cargarCompetencias();
  }

  setSort(sort : string){
    this.sort = sort;
    this.cargarCompetencias();
  }

  setOrder() : void{
    if(this.order === 'asc'){
      this.order = 'desc';
    }else{
      this.order = 'asc';
    }
    this.cargarCompetencias();
  }

}
