import { Component, OnInit } from '@angular/core';
import { Sucursal } from 'src/app/modelo/sucursal';
import { SucursalService } from 'src/app/servicios/sucursal.service';

@Component({
  selector: 'app-index-sucursal',
  templateUrl: './index-sucursal.component.html',
  styleUrls: ['./index-sucursal.component.css']
})
export class IndexSucursalComponent implements OnInit {

  sucursales : Sucursal[] = [];
  cargando = true;

  page : number = 0;
  size : number = 1;
  sort : string = 'id'
  order : string = 'asc';
  esUltima : boolean = false;
  esPrimera : boolean = false;
  totalPages : Array<number>;

  constructor(private sucursalServicio : SucursalService) { }

  ngOnInit(): void {
    this.cargarSucursales();
  }

  cargarSucursales() : void{
    this.sucursalServicio.getSucursales(this.page,this.size,this.sort,this.order).subscribe(data => {
      this.sucursales = data.content;
      this.esPrimera = data.first;
      this.esUltima = data.last;
      this.totalPages = new Array(data['totalPages']);
      this.cargando = false;

    });
  }

  setOrder() : void{
    if(this.order === 'asc'){
      this.order = 'desc';
    }else{
      this.order = 'asc';
    }
    this.cargarSucursales();
  }

  setSort(sort : string){
    this.sort = sort;
    this.cargarSucursales();
  }

  anterior() : void{
    if(!this.esPrimera){
      this.page--;
      this.cargarSucursales();
    }
  }

  siguiente() : void{
    if(!this.esUltima){
      this.page++;
      this.cargarSucursales();
    }
  }

  setPagina(pag : number) : void{
    this.page = pag;
    this.cargarSucursales();
  }



}
