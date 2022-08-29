import { Component, Input, OnInit } from '@angular/core';
import { DetallePlantilla } from 'src/app/modelo/detalle-plantilla';

@Component({
  selector: 'app-ver-detalle-plantilla-evaluacion',
  templateUrl: './ver-detalle-plantilla-evaluacion.component.html',
  styleUrls: ['./ver-detalle-plantilla-evaluacion.component.css']
})
export class VerDetallePlantillaEvaluacionComponent implements OnInit {

  padre: any; //Es la referencia al padre que lo llamo.

  dp: DetallePlantilla;

  constructor() { }

  ngOnInit(): void {
  }

  cerrar() {
    this.padre.modalRef.hide();
  }

}
