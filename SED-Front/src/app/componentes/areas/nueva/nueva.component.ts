import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AreaService } from 'src/app/servicios/area.service';

@Component({
  selector: 'app-nueva',
  templateUrl: './nueva.component.html',
  styleUrls: ['./nueva.component.css']
})
export class NuevaComponent implements OnInit {
  mensaje = '';
  errors : string[];
  guardado = false;
  error = false;
  AreaForm : FormGroup;

  constructor(private areaServicio:AreaService,private fb : FormBuilder) { }

  ngOnInit(): void {
    this.validaciones();
  }

  validaciones() : void{
    this.AreaForm = this.fb.group({
      nombre : ['',Validators.required],
      descripcion :[],
    });
  }

  guardar():void {
    this.guardado = false;
    this.error = false;
    this.areaServicio.guardar(this.AreaForm.value).subscribe(data => {
      this.mensaje = data.mensaje;
      this.guardado = true;
      this.error = false;
      this.AreaForm.reset();
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
