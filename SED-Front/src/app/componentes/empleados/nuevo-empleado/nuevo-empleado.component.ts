import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { catchError } from 'rxjs/internal/operators';
import { HttpMensaje } from 'src/app/HttpMensajes/http-mensaje';
import { Empleado } from 'src/app/modelo/empleado';
import { PuestoTrabajo } from 'src/app/modelo/puesto-trabajo';
import { Rol } from 'src/app/modelo/rol';
import { Sucursal } from 'src/app/modelo/sucursal';
import { Usuario } from 'src/app/modelo/usuario';
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
  comboPuestosTrabajo : PuestoTrabajo[] = [];
  comboRoles : Rol[] = [];
  puestosAsignados : PuestoTrabajo[] = [];
  rolesAsignados : Rol[] = [];
  emptyPuestosAsignados : boolean = true;
  emptyRolesAsignados : boolean = true;
  mensaje = '';
  guardado = false;
  error = false;
  empleadoForm : FormGroup;
  usuarioForm : FormGroup;

  constructor(private sucursalServicio : SucursalService,private puestoTrabajoServicio : PuestoTrabajoService,private rolServicio : RolService,private usuarioServicio:UsuarioService,private fb : FormBuilder) { }

  ngOnInit(): void {
    this.obtenerSucursales();
    this.obtenerRoles();
    this.validaciones();
  }

  validaciones() : void{
    this.empleadoForm = this.fb.group({
      legajo : ['',Validators.required],
      nombre : ['',[Validators.required,Validators.minLength(3)]],
      apellido : ['',[Validators.required,Validators.minLength(3)]],
      fechaDeNacimiento : ['',Validators.required],
      dni : ['',[Validators.required,Validators.min(10000000),Validators.max(99999999)]],
      email : ['',[Validators.required,Validators.email]],
      sucursal : [],
      puestosTrabajo : [],
    });

    this.usuarioForm = this.fb.group({
      nombreUsuario : ['',Validators.required],
      roles : [],
      password : ['',Validators.required],
      repeatPassword : ['',Validators.required],
    },{
      validator: this.ValidarPasswords('password', 'repeatPassword')
    });
  }

  guardar() : void{
      this.guardado = false;
      this.error = false;

      let emp = this.crearObjetoEmpleado();
      let user = this.crearObjetoUsuario(emp);

      this.usuarioServicio.guardar(user).subscribe(data => {
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

  crearObjetoEmpleado() : Empleado{
    let empl = new Empleado(
      this.empleadoForm.get('legajo').value,
      this.empleadoForm.get('nombre').value,
      this.empleadoForm.get('apellido').value,
      this.empleadoForm.get('dni').value,
      this.empleadoForm.get('email').value,
      this.empleadoForm.get('fechaDeNacimiento').value,
      null,
      this.puestosAsignados
    );
    return empl;
  }

  crearObjetoUsuario(emp:Empleado) : Usuario{
    let user = new Usuario(
      this.usuarioForm.get('nombreUsuario').value,
      this.usuarioForm.get('password').value,
      this.rolesAsignados,
      emp
    );
    return user;
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
    this.puestoTrabajoServicio.obtenerPuestosXSucursal(this.empleadoForm.get('sucursal').value.id).subscribe(data => {
      this.comboPuestosTrabajo = data;
    });
  }

  agregarPuestoAEmpleado() : void{
    var sePuedeAgregar = true;
    if(this.empleadoForm.get('puestosTrabajo').value != null){
      if (this.puestosAsignados.length > 0){
        for (let puesto of this.puestosAsignados){
          if(puesto.id === (this.empleadoForm.get('puestosTrabajo').value).id){
            sePuedeAgregar = false;
          }
        }
        if(sePuedeAgregar === true){
          this.puestosAsignados.push(this.empleadoForm.get('puestosTrabajo').value);
          this.emptyPuestosAsignados = false;
        }else{
          alert("El puesto seleccionado ya se encuentra asignado al usuario.");
        }
      }else{
        this.puestosAsignados.push(this.empleadoForm.get('puestosTrabajo').value);
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
    if(this.usuarioForm.get('roles').value != null){
      var i = this.rolesAsignados.indexOf(this.usuarioForm.get('roles').value);
      if(i == -1){
        this.rolesAsignados.push(this.usuarioForm.get('roles').value);
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
