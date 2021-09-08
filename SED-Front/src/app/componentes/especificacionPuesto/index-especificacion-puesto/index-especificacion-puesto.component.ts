import { Component, OnInit } from '@angular/core';
import { EspecificacionDePuestoIndexDto } from 'src/app/HttpMensajes/especificacion-puesto-index-dto';
import { EspecificacionPuestoService } from 'src/app/servicios/especificacion-puesto.service';

@Component({
  selector: 'app-index-especificacion-puesto',
  templateUrl: './index-especificacion-puesto.component.html',
  styleUrls: ['./index-especificacion-puesto.component.css']
})
export class IndexEspecificacionPuestoComponent implements OnInit {

  page : number = 0;
  size : number = 10;
  sort : string = 'id'
  order : string = 'asc';
  esUltima : boolean = false;
  esPrimera : boolean = false;
  totalPages : Array<number>;
  especificacionesPuestoIndex : EspecificacionDePuestoIndexDto[];
  cargando = true;


  constructor(private especificacionPuestoService : EspecificacionPuestoService) { }

  ngOnInit(): void {
    this.cargarEspecificacionesDePuesto();
  }

  cargarEspecificacionesDePuesto() : void{
    this.especificacionPuestoService.getEspecificacionesDePuestos(this.page,this.size,this.sort,this.order).subscribe(data =>{
      this.especificacionesPuestoIndex = data.content;
      this.esPrimera = data.first;
      this.esUltima = data.last;
      this.totalPages = new Array(data['totalPages']);
      this.cargando = false;
    });
  }

  anterior() : void{
    if(!this.esPrimera){
      this.page--;
      this.cargarEspecificacionesDePuesto();
    }
  }

  siguiente() : void{
    if(!this.esUltima){
      this.page++;
      this.cargarEspecificacionesDePuesto();
    }
  }

  setPagina(pag : number) : void{
    this.page = pag;
    this.cargarEspecificacionesDePuesto();
  }

  setSort(sort : string){
    this.sort = sort;
    this.cargarEspecificacionesDePuesto();
  }

  setOrder() : void{
    if(this.order === 'asc'){
      this.order = 'desc';
    }else{
      this.order = 'asc';
    }
    this.cargarEspecificacionesDePuesto();
  }

}
