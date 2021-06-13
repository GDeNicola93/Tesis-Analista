import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PuestoTrabajo } from 'src/app/modelo/puesto-trabajo';
import { PuestoTrabajoService } from 'src/app/servicios/puesto-trabajo.service';

@Component({
  selector: 'app-index-puestos-trabajo',
  templateUrl: './index-puestos-trabajo.component.html',
  styleUrls: ['./index-puestos-trabajo.component.css']
})
export class IndexPuestosTrabajoComponent implements OnInit {

  puestosTrabajo : PuestoTrabajo[] = [];
  puestoSeleccionadoParaVerOjetivos!: PuestoTrabajo;
  cargando = true;

  constructor(private puestosTeabajoServicio : PuestoTrabajoService,private modalService: NgbModal) { }

  ngOnInit(): void {
    this.cargarPuestosDeTrabajo();
  }

  cargarPuestosDeTrabajo() : void{
    this.puestosTeabajoServicio.getPuestosTrabajosSelect().subscribe(data => {
      this.puestosTrabajo = data;
      this.cargando = false;
    })
  }

  verObjetivosPuesto(content : any, puestoSeleccionado : PuestoTrabajo) : void{
    this.puestoSeleccionadoParaVerOjetivos = puestoSeleccionado;
    this.modalService.open(content,{ size: 'lg' });
  }

}
