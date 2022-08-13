import { CompileShallowModuleMetadata, componentFactoryName } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Competencia } from 'src/app/modelo/competencia';
import { ComportamientoPlantilla } from 'src/app/modelo/comportamiento-plantilla';
import { DetallePlantilla } from 'src/app/modelo/detalle-plantilla';
import { EspecificacionDePuesto } from 'src/app/modelo/especificacion-puesto';
import { CompetenciaService } from 'src/app/servicios/competencia.service';
import { EspecificacionPuestoService } from 'src/app/servicios/especificacion-puesto.service';
import { PlantillaEvaluacionService } from 'src/app/servicios/plantilla-evaluacion.service';
import { PuestoTrabajoService } from 'src/app/servicios/puesto-trabajo.service';
import { DetallePlantillaEvaluacionComponent } from '../detalle-plantilla-evaluacion/detalle-plantilla-evaluacion.component';

@Component({
  selector: 'app-nueva-plantilla-evaluacion',
  templateUrl: './nueva-plantilla-evaluacion.component.html',
  styleUrls: ['./nueva-plantilla-evaluacion.component.css']
})
export class NuevaPlantillaEvaluacionComponent implements OnInit {
  form: any = {}; 
  comboEspecificacionesPuestosTrabajo : EspecificacionDePuesto[] = [];
  pasoActivo : number = 1;
  detallePlantilla : DetallePlantilla[] = [];
  comboCompetencias : Competencia[] = [];

  mensaje = '';
  guardado = false;
  error = false;

  modalRef : BsModalRef;

  constructor(private especificacionDePuestosTrabajoService : EspecificacionPuestoService,private modalService: BsModalService,private plantillaEvaluacionService:PlantillaEvaluacionService,private competenciaService : CompetenciaService) { }

  ngOnInit(): void {
    this.obtenerPuestosTrabajo();
    this.obtenerCompetencias();
  }

  obtenerPuestosTrabajo() : void{
    this.especificacionDePuestosTrabajoService.getEspecificacionesDePuestosParaSelect().subscribe(data => {
      this.comboEspecificacionesPuestosTrabajo = data;
    });
  }

  obtenerCompetencias() : void{
    this.competenciaService.obtenerCompetenciasParaSelect().subscribe(data => {
      this.comboCompetencias = data;
    });
  }

  siguientePaso(): void{
    this.pasoActivo = 2;
  }

  anteriorPaso() : void{
    this.pasoActivo = 1;
  }

  agregarCompetencia() : void{
    this.abrirModalDetallePlantilla();
    this.modalRef.content.detallePlantilla.subscribe((result: any) => {
      this.detallePlantilla.push(result);
      this.modalRef.hide();
    });
  }

  public abrirModalDetallePlantilla() {
    this.modalRef = this.modalService.show(DetallePlantillaEvaluacionComponent,{ class: 'modal-xl' });
    this.modalRef.content.padre = this;
    this.modalRef.content.comboCompetencias = this.comboCompetencias;
    this.modalRef.content.objetivos = this.form.especificacionDePuesto.objetivosActivos;
  }

  guardarPlantillaEvaluacion() : void{
    this.form.detallePlantilla = this.detallePlantilla;
    console.log(this.form);
    // this.plantillaEvaluacionService.guardar(this.form).subscribe(data => {
    //   this.mensaje = data.mensaje;
    //   this.guardado = true;
    //   this.error = false;
    // },
    //   (err: any) => {
    //     this.mensaje = err.error.mensaje;
    //     this.guardado = false;
    //     this.error = true;
    //   }
    // );
  }
}
