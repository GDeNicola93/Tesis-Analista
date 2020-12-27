import { Component, OnInit } from '@angular/core';
import { AreaService } from 'src/app/servicios/area.service';

@Component({
  selector: 'app-nueva',
  templateUrl: './nueva.component.html',
  styleUrls: ['./nueva.component.css']
})
export class NuevaComponent implements OnInit {
  form: any = {};
  mensaje = '';
  guardado = false;
  error = false;

  constructor(private areaServicio:AreaService) { }

  ngOnInit(): void {
  }

  guardar():void {
    this.guardado = false;
    this.error = false;
    this.areaServicio.guardar(this.form).subscribe(data => {
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
