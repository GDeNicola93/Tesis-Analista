import { componentFactoryName } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Competencia } from 'src/app/modelo/competencia';
import { ComportamientoPlantilla } from 'src/app/modelo/comportamiento-plantilla';
import { DetallePlantilla } from 'src/app/modelo/detalle-plantilla';
import { EspecificacionDePuesto } from 'src/app/modelo/especificacion-puesto';
import { CompetenciaService } from 'src/app/servicios/competencia.service';
import { EspecificacionPuestoService } from 'src/app/servicios/especificacion-puesto.service';
import { PlantillaEvaluacionService } from 'src/app/servicios/plantilla-evaluacion.service';
import { PuestoTrabajoService } from 'src/app/servicios/puesto-trabajo.service';

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
  esPreguntaObjetivo : boolean = false;
  comboCompetencias : Competencia[] = [];

  nuevoComportamiento : ComportamientoPlantilla[] = [];
  grados : string[] = ["A","B","C","D","E","F","G","H"];
  cantidadComportamientosAgregados : number = -1;

  mensaje = '';
  guardado = false;
  error = false;

  constructor(private especificacionDePuestosTrabajoService : EspecificacionPuestoService,private modalService: NgbModal,private plantillaEvaluacionService:PlantillaEvaluacionService,private competenciaService : CompetenciaService) { }

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

  agregarCompetencia(content : any) : void{
    this.nuevoComportamiento = [];
    this.cantidadComportamientosAgregados = -1;
    this.obtenerCompetencias();
    this.modalService.open(content,{ size: 'xl',scrollable : true });
  }

  agregarComportamiento() : void{
    this.cantidadComportamientosAgregados += 1;
    this.nuevoComportamiento.push(new ComportamientoPlantilla("",this.grados[this.cantidadComportamientosAgregados],0));
  }

  quitarComportamiento(compo:ComportamientoPlantilla) : void{
    var i = this.nuevoComportamiento.indexOf(compo);
    if(i !== -1){
      this.nuevoComportamiento.splice(i,1);
      this.cantidadComportamientosAgregados -= 1;
      this.actualizarGradosComportamientos();
    }
  }

  //Con este metodo a medida que se borran comportamientos se actualizan automaticamente
  //los grados de cada comportamiento.
  actualizarGradosComportamientos() : void{
    let i = 0;
    for(let comp of this.nuevoComportamiento){
      comp.grado = this.grados[i];
      i += 1;
    }
  }

  guardarCompetencia() : void{
    this.detallePlantilla.push(new DetallePlantilla(this.form.competencia,this.esPreguntaObjetivo,this.form.objetivo,this.form.gradoMinimoRequerido,this.nuevoComportamiento));
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
