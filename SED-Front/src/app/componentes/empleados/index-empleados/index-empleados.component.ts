import { Component, OnInit } from '@angular/core';
import { EmpleadoIndexDto } from 'src/app/HttpMensajes/empleado-index-dto';
import { Area } from 'src/app/modelo/area';
import { Empleado } from 'src/app/modelo/empleado';
import { Sucursal } from 'src/app/modelo/sucursal';
import { EmpleadoService } from 'src/app/servicios/empleado.service';
import { SucursalService } from 'src/app/servicios/sucursal.service';

@Component({
  selector: 'app-index-empleados',
  templateUrl: './index-empleados.component.html',
  styleUrls: ['./index-empleados.component.css']
})
export class IndexEmpleadosComponent implements OnInit {

  empleados : EmpleadoIndexDto[] = [];
  page : number = 0;
  size : number = 10;
  sort : string = 'emp.nombre'
  order : string = 'asc';
  esUltima : boolean = false;
  esPrimera : boolean = false;
  totalPages : Array<number>;
  filtro : string = "";

  constructor(private empleadoServicio : EmpleadoService,private sucursalServicio : SucursalService) { }

  ngOnInit(): void {
    this.cargarEmpleados();
  }

  cargarEmpleados() : void{
    this.empleadoServicio.getEmpleados(this.page,this.size,this.sort,this.order,this.filtro).subscribe(data => {
      this.empleados = data.content;
      this.esPrimera = data.first;
      this.esUltima = data.last;
      this.totalPages = new Array(data['totalPages']);
    });
  }

  buscarEmpleado() : void{
    this.page = 0;
    this.cargarEmpleados();
  }

  esEmptyEmpleados() : boolean {
    if(this.empleados.length === 0){
      return true;
    }else{
      return false;
    }
  }

  anterior() : void{
    if(!this.esPrimera){
      this.page--;
      this.cargarEmpleados();
    }
  }

  siguiente() : void{
    if(!this.esUltima){
      this.page++;
      this.cargarEmpleados();
    }
  }

  setPagina(pag : number) : void{
    this.page = pag;
    this.cargarEmpleados();
  }

  setOrder() : void{
    if(this.order === 'asc'){
      this.order = 'desc';
    }else{
      this.order = 'asc';
    }
    this.cargarEmpleados();
  }

  setSort(sort : string){
    this.sort = sort;
    this.cargarEmpleados();
  }

  

}
