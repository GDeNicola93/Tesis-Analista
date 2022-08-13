import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Competencia } from 'src/app/modelo/competencia';
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

  @Output() detallePlantilla = new EventEmitter<FormGroup>();

  constructor(private fb : FormBuilder) { }

  ngOnInit(): void {
    this.validaciones();
  }

  validaciones() : void{
    this.detallePlantillaForm = this.fb.group({
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
    // console.log(this.detallePlantillaForm.value);
    this.detallePlantilla.emit(this.detallePlantillaForm.value);
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

}
