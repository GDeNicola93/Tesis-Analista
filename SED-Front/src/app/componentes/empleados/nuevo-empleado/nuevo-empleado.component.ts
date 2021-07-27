import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { catchError } from 'rxjs/internal/operators';
import { HttpMensaje } from 'src/app/HttpMensajes/http-mensaje';
import { Empleado } from 'src/app/modelo/empleado';
import { EspecificacionDePuesto } from 'src/app/modelo/especificacion-puesto';
import { PuestoTrabajo } from 'src/app/modelo/puesto-trabajo';
import { Rol } from 'src/app/modelo/rol';
import { Sucursal } from 'src/app/modelo/sucursal';
import { Usuario } from 'src/app/modelo/usuario';
import { EmpleadoService } from 'src/app/servicios/empleado.service';
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
  comboSucursales : Sucursal[] = [];
  comboEspecificacionesPuesto : EspecificacionDePuesto[] = [];
  comboRoles : Rol[] = [];
  puestosAsignados : EspecificacionDePuesto[] = [];
  rolesAsignados : Rol[] = [];
  emptyPuestosAsignados : boolean = true;
  emptyRolesAsignados : boolean = true;
  mensaje = '';
  errors : string[];
  guardado = false;
  error = false;
  nuevoEmpleadoForm : FormGroup;

  constructor(private sucursalServicio : SucursalService,private rolServicio : RolService,private empleadoServicio : EmpleadoService,private fb : FormBuilder) { }

  ngOnInit(): void {
    this.obtenerSucursales();
    this.obtenerRoles();
    this.validaciones();
  }

  validaciones() : void{
    this.nuevoEmpleadoForm = this.fb.group({
      legajo : ['',Validators.required],
      nombre : ['',[Validators.required,Validators.minLength(3)]],
      apellido : ['',[Validators.required,Validators.minLength(3)]],
      fechaDeNacimiento : ['',Validators.required],
      dni : ['',[Validators.required,Validators.min(10000000),Validators.max(99999999)]],
      email : ['',[Validators.required,Validators.email]],
      sucursal : [],
      puestosTrabajo : [],
      nombreUsuario : ['',Validators.required],
      password : ['',Validators.required],
      repeatPassword : ['',Validators.required],
      roles : [],
    },{
      validator: this.ValidarPasswords('password', 'repeatPassword')
    });
  }

  guardar() : void{
      this.guardado = false;
      this.error = false;

      this.nuevoEmpleadoForm.get('puestosTrabajo').setValue(this.puestosAsignados);
      this.nuevoEmpleadoForm.get('roles').setValue(this.rolesAsignados);
      
      this.empleadoServicio.guardar(this.nuevoEmpleadoForm.value).subscribe(data =>{
        this.mensaje = data.mensaje;
        this.guardado = true;
        this.error = false;
        this.nuevoEmpleadoForm.reset();
      },
        (err:any)=>{
          this.mensaje = err.error.message;
          this.errors = err.error.errors;
          this.guardado = false;
          this.error = true;
        }
      );
  }

  obtenerSucursales() : void {
    this.sucursalServicio.getSucursalesSelect().subscribe(data => {
      this.comboSucursales = data;
    });
  }

  obtenerRoles() : void {
    this.rolServicio.obtenerRoles().subscribe(data => {
      this.comboRoles = data;
    });
  }

  actualizarComboEspecificacionesPuesto() : void{
    this.sucursalServicio.obtenerEspecificacionesDePuestoSucursal(this.nuevoEmpleadoForm.get('sucursal').value.id).subscribe(data =>{
      this.comboEspecificacionesPuesto = data
    });
  }

  agregarPuestoAEmpleado() : void{
    var sePuedeAgregar = true;
    if(this.nuevoEmpleadoForm.get('puestosTrabajo').value != null){
      if (this.puestosAsignados.length > 0){
        for (let puesto of this.puestosAsignados){
          if(puesto.id === (this.nuevoEmpleadoForm.get('puestosTrabajo').value).id){
            sePuedeAgregar = false;
          }
        }
        if(sePuedeAgregar === true){
          this.puestosAsignados.push(this.nuevoEmpleadoForm.get('puestosTrabajo').value);
          this.emptyPuestosAsignados = false;
        }else{
          alert("El puesto seleccionado ya se encuentra asignado al empleado.");
        }
      }else{
        this.puestosAsignados.push(this.nuevoEmpleadoForm.get('puestosTrabajo').value);
        this.emptyPuestosAsignados = false;
      }
    }
  }

  desSeleccionarPuesto(puestoTrabajo : EspecificacionDePuesto) : void {
    var i = this.puestosAsignados.indexOf(puestoTrabajo);
    if(i !== -1 ){
      this.puestosAsignados.splice(i,1);
    }
    if(this.puestosAsignados.length == 0){
      this.emptyPuestosAsignados = true;
    }
  }

  agregarPerfilAUsuario() : void{
    if(this.nuevoEmpleadoForm.get('roles').value != null){
      var i = this.rolesAsignados.indexOf(this.nuevoEmpleadoForm.get('roles').value);
      if(i == -1){
        this.rolesAsignados.push(this.nuevoEmpleadoForm.get('roles').value);
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

  ValidarPasswords(controlName: string, matchingControlName: string){
    return (formGroup: FormGroup) => {
        const control = formGroup.controls[controlName];
        const matchingControl = formGroup.controls[matchingControlName];
        if (matchingControl.errors && !matchingControl.errors.confirmedValidator) {
            return;
        }
        if (control.value !== matchingControl.value) {
            matchingControl.setErrors({ validarPasswords: true });
        } else {
            matchingControl.setErrors(null);
        }
    }
  }

}
