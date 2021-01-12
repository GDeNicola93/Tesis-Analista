import { Component, OnInit } from '@angular/core';
import { PuestoTrabajo } from 'src/app/modelo/puesto-trabajo';
import { PuestoTrabajoService } from 'src/app/servicios/puesto-trabajo.service';

@Component({
  selector: 'app-index-puestos-trabajo',
  templateUrl: './index-puestos-trabajo.component.html',
  styleUrls: ['./index-puestos-trabajo.component.css']
})
export class IndexPuestosTrabajoComponent implements OnInit {

  puestosTrabajo : PuestoTrabajo[] = [];

  constructor(private puestosTeabajoServicio : PuestoTrabajoService) { }

  ngOnInit(): void {
    this.cargarPuestosDeTrabajo();
  }

  cargarPuestosDeTrabajo() : void{
    this.puestosTeabajoServicio.obtenerPuestoTrabajo().subscribe(data => {
      this.puestosTrabajo = data;
    })
  }

}
