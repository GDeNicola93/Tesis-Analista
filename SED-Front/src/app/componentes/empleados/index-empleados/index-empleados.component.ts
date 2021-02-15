import { Component, OnInit } from '@angular/core';
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

  empleados : Empleado[] = [];
  comboSucursales : Sucursal[] = [];
  comboAreas : Area[] = [];
  form: any = {}; 

  constructor(private empleadoServicio : EmpleadoService,private sucursalServicio : SucursalService) { }

  ngOnInit(): void {
    this.obtenerEmpleados();
    this.obtenerSucursales();
  }

  obtenerEmpleados() : void {
    this.empleadoServicio.obtenerEmpleados().subscribe(data => {
      this.empleados = data;
    });
  }

  obtenerSucursales() : void {
    this.sucursalServicio.obtenerSucursales().subscribe(data => {
      this.comboSucursales = data;
    });
  }

  actualizarComboAreas() : void {
    this.comboAreas = this.form.sucursal.areas;
  }

  buscarPorNombreYApellido() : void{
    this.empleadoServicio.buscarPorNombreYApellido(this.form.buscadorNombreApellido).subscribe(data => {
      this.empleados = data;
      this.form.buscadorNombreApellido = null;
    });
  }

  buscarPorLegajo() : void{
    this.empleadoServicio.buscarPorLegajo(this.form.buscadorLegajo).subscribe(data => {
      this.empleados = data;
      this.form.buscadorLegajo = null;
    });
  }

  esEmptyEmpleados() : boolean {
    if(this.empleados.length === 0){
      return true;
    }else{
      return false;
    }
  }

}
