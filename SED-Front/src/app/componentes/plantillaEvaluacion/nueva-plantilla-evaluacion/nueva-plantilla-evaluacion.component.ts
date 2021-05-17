import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Competencia } from 'src/app/modelo/competencia';
import { ComportamientoPlantilla } from 'src/app/modelo/comportamiento-plantilla';
import { DetallePlantilla } from 'src/app/modelo/detalle-plantilla';
import { PuestoTrabajo } from 'src/app/modelo/puesto-trabajo';
import { PlantillaEvaluacionService } from 'src/app/servicios/plantilla-evaluacion.service';
import { PuestoTrabajoService } from 'src/app/servicios/puesto-trabajo.service';

@Component({
  selector: 'app-nueva-plantilla-evaluacion',
  templateUrl: './nueva-plantilla-evaluacion.component.html',
  styleUrls: ['./nueva-plantilla-evaluacion.component.css']
})
export class NuevaPlantillaEvaluacionComponent implements OnInit {
  form: any = {}; 
  comboPuestosTrabajo : PuestoTrabajo[] = [];
  pasoActivo : number = 1;
  detallePlantilla : DetallePlantilla[] = [];
  esPreguntaObjetivo : boolean = false;

  nuevaCompetencia : any = {};
  nuevoComportamiento : ComportamientoPlantilla[] = [];


  mensaje = '';
  guardado = false;
  error = false;

  constructor(private puestoTrabajoService : PuestoTrabajoService,private modalService: NgbModal,private plantillaEvaluacionService:PlantillaEvaluacionService) { }

  ngOnInit(): void {
    this.obtenerPuestosTrabajo();
  }

  cambiarCheck() {
    if(this.esPreguntaObjetivo == false){
      this.esPreguntaObjetivo = true;
    }else{
      this.esPreguntaObjetivo = false;
      this.form.objetivo = null;
    }
  }

  obtenerPuestosTrabajo() : void{
    this.puestoTrabajoService.obtenerPuestoTrabajo().subscribe(data => {
      this.comboPuestosTrabajo = data;
    });
  }

  siguientePaso(): void{
    this.pasoActivo = 2;
  }

  anteriorPaso() : void{
    this.pasoActivo = 1;
  }

  agregarCompetencia(content : any) : void{
    this.nuevoComportamiento = [];
    this.nuevaCompetencia = {};
    this.modalService.open(content,{ size: 'xl' });
  }

  agregarComportamiento() : void{
    this.nuevoComportamiento.push(new ComportamientoPlantilla("","",0,false));
  }

  guardarCompetencia() : void{
    this.detallePlantilla.push(new DetallePlantilla(this.nuevaCompetencia,this.esPreguntaObjetivo,this.form.objetivo,this.nuevoComportamiento));
    this.esPreguntaObjetivo = false;
    this.form.objetivo = null;
    this.modalService.dismissAll();
  }

  guardarPlantillaEvaluacion() : void{
    this.form.detallePlantilla = this.detallePlantilla;
    this.plantillaEvaluacionService.guardar(this.form).subscribe(data => {
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

}
