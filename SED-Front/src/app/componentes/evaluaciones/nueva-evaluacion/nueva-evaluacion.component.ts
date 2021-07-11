import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-nueva-evaluacion',
  templateUrl: './nueva-evaluacion.component.html',
  styleUrls: ['./nueva-evaluacion.component.css']
})
export class NuevaEvaluacionComponent implements OnInit {
  evaluacionForm : FormGroup;

  constructor(private fb : FormBuilder) { }

  ngOnInit(): void {
    this.validaciones();
  }

  validaciones() : void{
    this.evaluacionForm = this.fb.group({
      fechaInicioEvaluacion : ['',Validators.required],
      fechaFinEvaluacion : ['',Validators.required],
    });
  }

  guardar() : void{

  }

}
