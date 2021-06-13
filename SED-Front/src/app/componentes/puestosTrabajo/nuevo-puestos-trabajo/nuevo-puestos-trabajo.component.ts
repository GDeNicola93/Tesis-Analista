import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Area } from 'src/app/modelo/area';
import { Objetivo } from 'src/app/modelo/objetivo';
import { PuestoTrabajo } from 'src/app/modelo/puesto-trabajo';
import { Sucursal } from 'src/app/modelo/sucursal';
import { AreaService } from 'src/app/servicios/area.service';
import { PuestoTrabajoService } from 'src/app/servicios/puesto-trabajo.service';
import { SucursalService } from 'src/app/servicios/sucursal.service';

@Component({
  selector: 'app-nuevo-puestos-trabajo',
  templateUrl: './nuevo-puestos-trabajo.component.html',
  styleUrls: ['./nuevo-puestos-trabajo.component.css']
})
export class NuevoPuestosTrabajoComponent implements OnInit {

  comboAreas : Area[] = [];
  mensaje = '';
  guardado = false;
  error = false;
  puestoTrabajoForm : FormGroup;

  constructor(private puestoTrabajoServicio : PuestoTrabajoService,private fb : FormBuilder,private areaServicio:AreaService) { }

  ngOnInit(): void {
    this.cargarComboAreas();
    this.validaciones();
  }

  validaciones() : void{
    this.puestoTrabajoForm = this.fb.group({
      nombrePuesto : ['',Validators.required],
      area : ['',Validators.required],
    });
  }

  guardar() : void {
    this.guardado = false;
    this.error = false;
    
    this.puestoTrabajoServicio.guardar(this.puestoTrabajoForm.value).subscribe(data => {
      this.mensaje = data.mensaje;
      this.guardado = true;
      this.error = false;
      this.puestoTrabajoForm.reset();
    },
    (err: any) => {
      this.mensaje = err.error.mensaje;
       this.guardado = false;
      this.error = true;
      }
    );
  }

  cargarComboAreas() : void {
    this.areaServicio.obtenerAreas().subscribe(data =>{
      this.comboAreas = data;
    });
  }
}
