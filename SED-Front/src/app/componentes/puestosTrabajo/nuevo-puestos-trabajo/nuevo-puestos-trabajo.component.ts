import { Component, OnInit } from '@angular/core';
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

  constructor(private sucursalServicio : SucursalService,private puestoTrabajoServicio : PuestoTrabajoService) { }

  ngOnInit(): void {
    this.obtenerSucursales();
  }

  guardar() : void {
    this.guardado = false;
    this.error = false;
    
    let nuevoPuesto = new PuestoTrabajo(this.form.nombrePuesto,this.form.sucursal,this.form.area,this.objetivosAgregados);
    
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
    this.comboAreas = this.form.sucursal.areas;
  }

  agregarObjetivo() : void{
    let nuevoObjetivo : Objetivo = new Objetivo(this.form.objetivo);
    this.objetivosAgregados.push(nuevoObjetivo);
    this.form.objetivo = null;
    this.emptyObjetivos = false;
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
