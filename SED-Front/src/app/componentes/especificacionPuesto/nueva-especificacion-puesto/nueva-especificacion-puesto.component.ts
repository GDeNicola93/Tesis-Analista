import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EspecificacionDePuesto } from 'src/app/modelo/especificacion-puesto';
import { Objetivo } from 'src/app/modelo/objetivo';
import { PuestoTrabajo } from 'src/app/modelo/puesto-trabajo';
import { Sucursal } from 'src/app/modelo/sucursal';
import { EspecificacionPuestoService } from 'src/app/servicios/especificacion-puesto.service';
import { PuestoTrabajoService } from 'src/app/servicios/puesto-trabajo.service';
import { SucursalService } from 'src/app/servicios/sucursal.service';

@Component({
  selector: 'app-nueva-especificacion-puesto',
  templateUrl: './nueva-especificacion-puesto.component.html',
  styleUrls: ['./nueva-especificacion-puesto.component.css']
})
export class NuevaEspecificacionPuestoComponent implements OnInit {

  comboPuestosTrabajo : PuestoTrabajo[];
  comboSucursales : Sucursal[];
  especificacionPuestoForm : FormGroup;
  objetivosAgregados : Objetivo[] = [];
  emptyObjetivos : boolean = true;
  mensaje = '';
  guardado = false;
  error = false;

  constructor(private puestoTrabajoService : PuestoTrabajoService,private fb : FormBuilder,private sucursalService : SucursalService,private especificacionPuestoService : EspecificacionPuestoService) { }

  ngOnInit(): void {
    this.validaciones();
    this.cargarComboPuestosTrabajo();
  }

  validaciones() : void{
    this.especificacionPuestoForm = this.fb.group({
      puestoTrabajo : ['',Validators.required],
      sucursal : ['',Validators.required],
      descripcion :[],
      objetivo :[],
    });
  }

  guardar() : void{
    this.guardado = false;
    this.error = false;
    let edp = this.crearObjetoEspecificacionDePuesto();
    this.especificacionPuestoService.guardar(edp).subscribe(data => {
      this.mensaje = data.mensaje;
      this.guardado = true;
      this.error = false;
      this.especificacionPuestoForm.reset();
    },
      (err: any) => {
        this.mensaje = err.error.mensaje;
        this.guardado = false;
        this.error = true;
      }
    );
  }

  crearObjetoEspecificacionDePuesto() : EspecificacionDePuesto{
    let edp = new EspecificacionDePuesto(
      this.especificacionPuestoForm.get('puestoTrabajo').value,
      this.especificacionPuestoForm.get('sucursal').value,
      this.objetivosAgregados,
      this.especificacionPuestoForm.get('descripcion').value
    );
    return edp;
  }

  cargarComboPuestosTrabajo(){
    this.puestoTrabajoService.getPuestosTrabajosSelect().subscribe(data =>{
      this.comboPuestosTrabajo = data;
    });
  }

  cargarComboSucursales(){
    this.especificacionPuestoForm.get("sucursal").reset();
    let idArea : number = this.especificacionPuestoForm.get('puestoTrabajo').value.area.id;
    this.sucursalService.getSucursalesSegunAreaSelect(idArea).subscribe(data =>{
      this.comboSucursales = data;
    });
  }

  agregarObjetivo() : void{
    if(this.especificacionPuestoForm.get('objetivo').value != null){
      let nuevoObjetivo : Objetivo = new Objetivo(this.especificacionPuestoForm.get('objetivo').value);
      this.objetivosAgregados.push(nuevoObjetivo);
      this.especificacionPuestoForm.get('objetivo').setValue(null);
      this.emptyObjetivos = false;
    }
  }

  eliminarObjetivo(obj : Objetivo) : void{
    var i = this.objetivosAgregados.indexOf(obj);
    if(i !== -1 ){
      this.objetivosAgregados.splice(i,1);
    }
    if(this.objetivosAgregados.length == 0){
      this.emptyObjetivos = true;
    }
  }

}
