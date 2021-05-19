import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Area } from 'src/app/modelo/area';
import { Objetivo } from 'src/app/modelo/objetivo';
import { PuestoTrabajo } from 'src/app/modelo/puesto-trabajo';
import { Sucursal } from 'src/app/modelo/sucursal';
import { PuestoTrabajoService } from 'src/app/servicios/puesto-trabajo.service';
import { SucursalService } from 'src/app/servicios/sucursal.service';

@Component({
  selector: 'app-nuevo-puestos-trabajo',
  templateUrl: './nuevo-puestos-trabajo.component.html',
  styleUrls: ['./nuevo-puestos-trabajo.component.css']
})
export class NuevoPuestosTrabajoComponent implements OnInit {

  form: any = {};
  comboSucursales : Sucursal[] = [];
  comboAreas : Area[] = [];
  objetivosAgregados : Objetivo[] = [];
  emptyObjetivos : boolean = true;
  mensaje = '';
  guardado = false;
  error = false;
  puestoTrabajoForm : FormGroup;

  constructor(private sucursalServicio : SucursalService,private puestoTrabajoServicio : PuestoTrabajoService,private fb : FormBuilder) { }

  ngOnInit(): void {
    this.obtenerSucursales();
    this.validaciones();

  }

  validaciones() : void{
    this.puestoTrabajoForm = this.fb.group({
      nombrePuesto : ['',Validators.required],
      sucursal : ['',Validators.required],
      area : ['',Validators.required],
      objetivo : [],
    });
  }

  guardar() : void {
    this.guardado = false;
    this.error = false;
    
    let nuevoPuesto = new PuestoTrabajo(this.puestoTrabajoForm.get('nombrePuesto').value,this.puestoTrabajoForm.get('sucursal').value,this.puestoTrabajoForm.get('area').value,this.objetivosAgregados);
    
    this.puestoTrabajoServicio.guardar(nuevoPuesto).subscribe(data => {
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

  actualizarComboAreas() : void {
    this.comboAreas = (this.puestoTrabajoForm.get('sucursal').value).areas;
  }

  agregarObjetivo() : void{
    if(this.puestoTrabajoForm.get('objetivo').value != null){
      let nuevoObjetivo : Objetivo = new Objetivo(this.puestoTrabajoForm.get('objetivo').value);
      this.objetivosAgregados.push(nuevoObjetivo);
      this.puestoTrabajoForm.get('objetivo').setValue(null);
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
