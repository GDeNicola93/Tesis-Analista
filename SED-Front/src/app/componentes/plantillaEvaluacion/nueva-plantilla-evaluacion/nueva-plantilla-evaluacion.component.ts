import { CompileShallowModuleMetadata, componentFactoryName } from '@angular/compiler';
import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Competencia } from 'src/app/modelo/competencia';
import { DetallePlantilla } from 'src/app/modelo/detalle-plantilla';
import { EspecificacionDePuesto } from 'src/app/modelo/especificacion-puesto';
import { CompetenciaService } from 'src/app/servicios/competencia.service';
import { EspecificacionPuestoService } from 'src/app/servicios/especificacion-puesto.service';
import { PlantillaEvaluacionService } from 'src/app/servicios/plantilla-evaluacion.service';
import Swal from 'sweetalert2';
import { DetallePlantillaEvaluacionComponent } from '../detalle-plantilla-evaluacion/detalle-plantilla-evaluacion.component';
import { VerDetallePlantillaEvaluacionComponent } from '../ver-detalle-plantilla-evaluacion/ver-detalle-plantilla-evaluacion.component';

@Component({
  selector: 'app-nueva-plantilla-evaluacion',
  templateUrl: './nueva-plantilla-evaluacion.component.html',
  styleUrls: ['./nueva-plantilla-evaluacion.component.css']
})
export class NuevaPlantillaEvaluacionComponent implements OnInit {
  comboEspecificacionesPuestosTrabajo : EspecificacionDePuesto[] = [];
  pasoActivo : number = 1;
  comboCompetencias : Competencia[] = [];
  plantillaEvaluacion : FormGroup;
  modoEdicion : boolean = false;


  mensaje = '';
  guardado = false;
  error = false;

  modalRef : BsModalRef;

  constructor(private especificacionDePuestosTrabajoService : EspecificacionPuestoService,private modalService: BsModalService,private plantillaEvaluacionService:PlantillaEvaluacionService,private competenciaService : CompetenciaService,private fb : FormBuilder,private route:ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    this.validaciones();
    this.obtenerPuestosTrabajo();
    this.obtenerCompetencias();

    this.route.params.subscribe(p=>{
      if(p.idPlantilla){
        this.modoEdicion = true;
        this.inicializarParaEdicion(p.idPlantilla);
      }
    })
  }

  validaciones() : void{
    this.plantillaEvaluacion = this.fb.group({
      id : [null],
      especificacionDePuesto : [null,Validators.required],
      descripcion : [''],
      detallePlantilla : this.fb.array([],Validators.required)
    });
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

  onChangePuestoTrabajo() : void{
    this.detallePlantilla.clear();
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
    this.modalRef.content.objetivos = this.plantillaEvaluacion.get('especificacionDePuesto').value.objetivosActivos;
  }

  get detallePlantilla(){
    return this.plantillaEvaluacion.get('detallePlantilla') as FormArray;
  }

  eliminarDetallePlantilla(dp : DetallePlantilla){
    Swal.fire({
      title: '¿Seguro?',
      text: "¿Estas seguro que quieres eliminar la competencia seleccionada de la plantilla de evaluación?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminarla!',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.isConfirmed) {
        var i = this.detallePlantilla.value.indexOf(dp);
        this.detallePlantilla.removeAt(i);
      }
    })
  }

  verDetallePlantilla(dp : DetallePlantilla){
    this.abrirModalVerDetallePlantilla(dp);
  }

  editarDetallePlantilla(dp : DetallePlantilla){
    this.abrirModalEditarDetallePlantilla(dp);
    this.modalRef.content.detallePlantilla.subscribe((result: any) => {

      //Elimino el detalle de plantilla que llega por parametro
      var i = this.detallePlantilla.value.indexOf(dp);
      this.detallePlantilla.removeAt(i);

      this.detallePlantilla.push(result);
      this.modalRef.hide();
    });
  }

  public abrirModalVerDetallePlantilla(dp : DetallePlantilla) {
    this.modalRef = this.modalService.show(VerDetallePlantillaEvaluacionComponent,{ class: 'modal-xl' });
    this.modalRef.content.padre = this;
    this.modalRef.content.dp = dp;
  }

  public abrirModalEditarDetallePlantilla(dp : DetallePlantilla) {
    this.modalRef = this.modalService.show(DetallePlantillaEvaluacionComponent,{ class: 'modal-xl' });
    this.modalRef.content.padre = this;
    this.modalRef.content.comboCompetencias = this.comboCompetencias;
    this.modalRef.content.objetivos = this.plantillaEvaluacion.get('especificacionDePuesto').value.objetivosActivos;

    //Exclusivamente para edicion
    this.modalRef.content.detallePlantillaEdicion = dp;
    this.modalRef.content.inicializarParaEdicion();
  }

  guardarPlantillaEvaluacion() : void{
    this.plantillaEvaluacionService.guardar(this.plantillaEvaluacion.value).subscribe(data => {
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: data.mensaje,
        showConfirmButton: true
      }).then((result) =>{
        this.router.navigate(['plantilla-evaluacion']);
      });
    },
      (err: any) => {
        console.log(err);
        this.mensaje = err.error.mensaje;
        this.guardado = false;
        this.error = true;
      }
    );
  }

  inicializarParaEdicion(idPlantilla : number){
    this.plantillaEvaluacionService.editarGet(idPlantilla).subscribe(data => {
      this.plantillaEvaluacion.patchValue({
        id : data.id,
        especificacionDePuesto : this.setEspecificacionDePuesto(data.especificacionDePuesto),
        descripcion : data.descripcion
      }); 
      
      data.detallePlantilla.map(
        (detalle : any)=>{
          const detalleForm = this.fb.group({
            id : detalle.id,
            competencia : detalle.competencia,
            esPreguntaObjetivo : detalle.esPreguntaObjetivo,
            obj : detalle.obj,
            comportamiento : this.fb.array([]),
            gradoMinimoRequerido : detalle.gradoMinimoRequerido
          });

          detalle.comportamiento.map(
              (comportamiento : any) =>{
                const compForm = this.fb.group({
                  id : comportamiento.id,
                  descComportamiento : comportamiento.descComportamiento,
                  grado : comportamiento.grado,
                  valoracionNumerica : comportamiento.valoracionNumerica
                });
                let arrayComportamientos = detalleForm.get('comportamiento') as FormArray;
                arrayComportamientos.push(compForm);
              }
          ),

          this.detallePlantilla.push(detalleForm);
        }
      );
    },
      (err : any) => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: err.error.message
        }).then((result) =>{
          this.router.navigate(['plantilla-evaluacion']);
        });
      }
    );
  }

  setEspecificacionDePuesto(ep : EspecificacionDePuesto){
    let retorno : EspecificacionDePuesto = null;
    for(let item of this.comboEspecificacionesPuestosTrabajo){
      if(item.id == ep.id){
        retorno = item;
      }
    }
    return retorno;
  }

  getCantidadDeDetalles() : Number{
    return (this.plantillaEvaluacion.get('detallePlantilla') as FormArray).length;
  }
}
