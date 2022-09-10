import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ComportamientoPlantilla } from 'src/app/modelo/comportamiento-plantilla';
import { PlantillaEvaluacion } from 'src/app/modelo/plantilla-evaluacion';
import { PlantillaEvaluacionService } from 'src/app/servicios/plantilla-evaluacion.service';

@Component({
  selector: 'app-index-plantilla-evaluacion',
  templateUrl: './index-plantilla-evaluacion.component.html',
  styleUrls: ['./index-plantilla-evaluacion.component.css']
})
export class IndexPlantillaEvaluacionComponent implements OnInit {
  plantillas : PlantillaEvaluacion[] = [];
  plantillaParaVisualizar !: PlantillaEvaluacion;
  cargando = true;

  constructor(private plantillaEvaluacionServicio : PlantillaEvaluacionService,private modalService: NgbModal) { }

  ngOnInit(): void {
    this.cargarPlantillas();
  }


  cargarPlantillas() : void{
    this.plantillaEvaluacionServicio.obtenerPlantillas().subscribe(data => {
      this.plantillas = data.content;
      this.cargando = false;
    });
  }

  verPlantillaDeEvaluacion(content : any,plantilla : PlantillaEvaluacion) : void{
    this.plantillaParaVisualizar = plantilla;

    //ordeno los comportamientos
    for(let dp of this.plantillaParaVisualizar.detallePlantilla){
      dp.comportamiento.sort((a,b)=> a.valoracionNumerica < b.valoracionNumerica ? 1:-1);
    }

    this.modalService.open(content, { size: 'xl',scrollable: true });
  }
}
