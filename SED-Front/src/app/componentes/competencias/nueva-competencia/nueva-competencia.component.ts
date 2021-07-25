import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CompetenciaService } from 'src/app/servicios/competencia.service';

@Component({
  selector: 'app-nueva-competencia',
  templateUrl: './nueva-competencia.component.html',
  styleUrls: ['./nueva-competencia.component.css']
})
export class NuevaCompetenciaComponent implements OnInit {
  mensaje = '';
  errors : string[];
  guardado = false;
  error = false;
  
  competenciaForm : FormGroup;
  constructor(private fb : FormBuilder,private competenciaServicio : CompetenciaService) { }

  ngOnInit(): void {
    this.validaciones();
  }

  validaciones() : void{
    this.competenciaForm = this.fb.group({
      nombre : ['',Validators.required],
      descripcion :[null],
    });
  }

  guardar() : void{
    this.guardado = false;
    this.error = false;
    this.competenciaServicio.guardar(this.competenciaForm.value).subscribe(data => {
      this.mensaje = data;
      this.guardado = true;
      this.error = false;
    },
      (err: any) => {
        this.mensaje = err.error.message;
        this.errors = err.error.errors;
        this.guardado = false;
        this.error = true;
      }
    );
  }

}
