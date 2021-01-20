import { Component, OnInit } from '@angular/core';
import { PuestoTrabajo } from 'src/app/modelo/puesto-trabajo';
import { Rol } from 'src/app/modelo/rol';
import { Sucursal } from 'src/app/modelo/sucursal';
import { PuestoTrabajoService } from 'src/app/servicios/puesto-trabajo.service';
import { RolService } from 'src/app/servicios/rol.service';
import { SucursalService } from 'src/app/servicios/sucursal.service';

@Component({
  selector: 'app-nuevo-empleado',
  templateUrl: './nuevo-empleado.component.html',
  styleUrls: ['./nuevo-empleado.component.css']
})
export class NuevoEmpleadoComponent implements OnInit {

  form: any = {};
  comboSucursales : Sucursal[] = [];
  comboPuestosTrabajo : PuestoTrabajo[] = [];
  comboRoles : Rol[] = [];
  puestosAsignados : PuestoTrabajo[] = [];
  emptyPuestosAsignados : boolean = true;

  constructor(private sucursalServicio : SucursalService,private puestoTrabajoServicio : PuestoTrabajoService,private rolServicio : RolService) { }

  ngOnInit(): void {
    this.obtenerSucursales();
    this.obtenerRoles();
  }

  guardar() : void{
    console.log(this.form.fechaDeNacimiento);
  }

  obtenerSucursales() : void {
    this.sucursalServicio.obtenerSucursales().subscribe(data => {
      this.comboSucursales = data;
    });
  }

  obtenerRoles() : void {
    this.rolServicio.obtenerRoles().subscribe(data => {
      this.comboRoles = data;
    });
  }

  actualizarComboPuestosTrabajo() : void{
    this.puestoTrabajoServicio.obtenerPuestosXSucursal(this.form.sucursal.id).subscribe(data => {
      this.comboPuestosTrabajo = data;
    });
  }

  agregarPuestoAEmpleado() : void{
    this.puestosAsignados.push(this.form.puestoTrabajo);
    this.emptyPuestosAsignados = false;
  }

  desSeleccionarPuesto(puestoTrabajo : PuestoTrabajo) : void {
    var i = this.puestosAsignados.indexOf(puestoTrabajo);
    if(i !== -1 ){
      this.puestosAsignados.splice(i,1);
    }
    if(this.puestosAsignados.length == 0){
      this.emptyPuestosAsignados = true;
    }
  }

}
