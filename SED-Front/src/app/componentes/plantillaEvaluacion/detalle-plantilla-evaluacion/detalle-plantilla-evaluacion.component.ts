import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Competencia } from 'src/app/modelo/competencia';
import { DetallePlantilla } from 'src/app/modelo/detalle-plantilla';
import { Objetivo } from 'src/app/modelo/objetivo';

@Component({
  selector: 'app-detalle-plantilla-evaluacion',
  templateUrl: './detalle-plantilla-evaluacion.component.html',
  styleUrls: ['./detalle-plantilla-evaluacion.component.css']
})
export class DetallePlantillaEvaluacionComponent implements OnInit {

  padre: any; //Es la referencia al padre que lo llamo.
  comboCompetencias : Competencia[] = [];
  detallePlantillaForm : FormGroup;
  objetivos : Objetivo[] = [];
  grados : string[] = ["A","B","C","D","E","F","G","H","I","J","K"];
  cantidadComportamientosAgregados : number = -1;

  //Para edicion
  detallePlantillaEdicion : DetallePlantilla;
  //Fin Para edicion

  @Output() detallePlantilla = new EventEmitter<FormGroup>();

  constructor(private fb : FormBuilder) { }

  ngOnInit(): void {
    this.validaciones();
  }

  validaciones() : void{
    this.detallePlantillaForm = this.fb.group({
      id : [null],
      competencia : [null,Validators.required],
      esPreguntaObjetivo : [false],
      obj : [null],
      comportamiento : this.fb.array([]),
      gradoMinimoRequerido : ["",Validators.required]
    });
  }

  agregarComportamiento() : void{
    this.comportamiento.push(this.nuevoComportamiento());
  }

  nuevoComportamiento(){
    this.cantidadComportamientosAgregados += 1;
    return this.fb.group({
      descComportamiento: ['', Validators.required],
      grado: [this.grados[this.cantidadComportamientosAgregados], Validators.required],
      valoracionNumerica : [0,[Validators.required,Validators.min(1),Validators.max(100)]]
    });
  }

  get comportamiento() {
    return this.detallePlantillaForm.get('comportamiento') as FormArray;
  }

  comportamientoToFormGroup(index : number){
    let comportamientos = this.detallePlantillaForm.get('comportamiento') as FormArray;
    return <FormGroup>comportamientos.controls[index];
  }


  quitarComportamiento(index : number) : void{
    let compotamientoArray  = this.detallePlantillaForm.get('comportamiento') as FormArray;
    
    compotamientoArray.removeAt(index);
    this.cantidadComportamientosAgregados -= 1;
    this.actualizarGradosComportamientos();
  }

  cerrar() {
    this.padre.modalRef.hide();
  }

  guardar(){
    this.detallePlantilla.emit(this.detallePlantillaForm);
  }

  changeCheckEsPreguntaObjetivo(){
    if(this.detallePlantillaForm.get('esPreguntaObjetivo').value){
      this.detallePlantillaForm.controls['obj'].setValidators(Validators.required);
      this.detallePlantillaForm.controls['obj'].updateValueAndValidity();
    }else{
      this.detallePlantillaForm.controls['obj'].clearValidators();
      this.detallePlantillaForm.controls['obj'].updateValueAndValidity();
    }
  }

  //Con este metodo a medida que se borran comportamientos se actualizan automaticamente
  //los grados de cada comportamiento.
  actualizarGradosComportamientos() : void{
    let array = this.comportamiento.controls as FormControl[];
    let i = 0;
    for(let comp of array){
      comp.get('grado').setValue(this.grados[i]);
      i += 1;
    }
    this.detallePlantillaForm.get('gradoMinimoRequerido').setValue("");
  }

  inicializarParaEdicion() : void{
    this.detallePlantillaForm.patchValue({
      id : this.detallePlantillaEdicion.id,
      competencia : this.setCompetencia(this.detallePlantillaEdicion.competencia),
      esPreguntaObjetivo : this.detallePlantillaEdicion.esPreguntaObjetivo,
      obj : this.setObjetivo(this.detallePlantillaEdicion.obj),
      gradoMinimoRequerido : this.detallePlantillaEdicion.gradoMinimoRequerido
    }); 

    this.detallePlantillaEdicion.comportamiento.map(
      (comportamiento : any) =>{
        const compForm = this.fb.group({
          id : comportamiento.id,
          descComportamiento : [comportamiento.descComportamiento,Validators.required],
          grado : [comportamiento.grado,Validators.required],
          valoracionNumerica : [comportamiento.valoracionNumerica,[Validators.required,Validators.min(1),Validators.max(100)]]
        });
        this.cantidadComportamientosAgregados += 1;
        this.comportamiento.push(compForm);
      }
    );
  }

  setCompetencia(c : Competencia) : Competencia{
    let retorno : Competencia = null;
    for(let item of this.comboCompetencias){
      if(item.id == c.id){
        retorno = item;
      }
    }
    return retorno;
  }

  setObjetivo(o : Objetivo) : Objetivo{
    let retorno : Objetivo = null;
    for(let item of this.objetivos){
      if(item.id == o.id){
        retorno = item;
      }
    }
    return retorno;
  }

}
