import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
import { PuestoTrabajo } from 'src/app/modelo/puesto-trabajo';
import { Rol } from 'src/app/modelo/rol';
import { Sucursal } from 'src/app/modelo/sucursal';
import { PuestoTrabajoService } from 'src/app/servicios/puesto-trabajo.service';
import { RolService } from 'src/app/servicios/rol.service';
import { SucursalService } from 'src/app/servicios/sucursal.service';
import { UsuarioService } from 'src/app/servicios/usuario.service';

@Component({
  selector: 'app-nuevo-empleado',
  templateUrl: './nuevo-empleado.component.html',
  styleUrls: ['./nuevo-empleado.component.css']
})
export class NuevoEmpleadoComponent implements OnInit {

  empleado: any = {};
  usuario: any = {};
  comboSucursales : Sucursal[] = [];
  comboPuestosTrabajo : PuestoTrabajo[] = [];
  comboRoles : Rol[] = [];
  puestosAsignados : PuestoTrabajo[] = [];
  rolesAsignados : Rol[] = [];
  emptyPuestosAsignados : boolean = true;
  emptyRolesAsignados : boolean = true;
  mensaje = '';
  guardado = false;
  error = false;

  constructor(private sucursalServicio : SucursalService,private puestoTrabajoServicio : PuestoTrabajoService,private rolServicio : RolService,private usuarioServicio:UsuarioService) { }

  ngOnInit(): void {
    this.obtenerSucursales();
    this.obtenerRoles();
  }

  guardar() : void{
    this.guardado = false;
    this.error = false;
    
    this.empleado.puestosTrabajo = this.puestosAsignados;
    this.usuario.empleado = this.empleado;
    this.usuario.roles = this.rolesAsignados;

    this.usuarioServicio.guardar(this.usuario).subscribe(data => {
      this.mensaje = data.mensaje;
      this.guardado = true;
      this.error = false;
    },
      (err: any) => {
        this.mensaje = err.error.mensaje;
        this.guardado = false;
        this.error = true;
      }
    );
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
    this.puestoTrabajoServicio.obtenerPuestosXSucursal(this.empleado.sucursal.id).subscribe(data => {
      this.comboPuestosTrabajo = data;
    });
  }

  agregarPuestoAEmpleado() : void{
    var sePuedeAgregar = true;
    if(this.empleado.puestoTrabajo != null){
      if (this.puestosAsignados.length > 0){
        for (let puesto of this.puestosAsignados){
          if(puesto.id === this.empleado.puestoTrabajo.id){
            sePuedeAgregar = false;
          }
        }
        if(sePuedeAgregar === true){
          this.puestosAsignados.push(this.empleado.puestoTrabajo);
          this.emptyPuestosAsignados = false;
        }else{
          alert("El puesto seleccionado ya se encuentra asignado al usuario.");
        }
      }else{
        this.puestosAsignados.push(this.empleado.puestoTrabajo);
        this.emptyPuestosAsignados = false;
      }
    }
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

  agregarPerfilAUsuario() : void{
    if(this.usuario.roles != null){
      var i = this.rolesAsignados.indexOf(this.usuario.roles);
      if(i == -1){
        this.rolesAsignados.push(this.usuario.roles);
        this.emptyRolesAsignados = false;
      }else{
        alert("El perfil seleccionado ya se encuentra asignado al usuario.");
      }
    }
  }

  desSeleccionarPerfilUsuario(rol:Rol) : void{
    var i = this.rolesAsignados.indexOf(rol);
    if(i !== -1){
      this.rolesAsignados.splice(i,1);
    }
    if(this.rolesAsignados.length == 0){
      this.emptyRolesAsignados = true;
    }
  }

}
