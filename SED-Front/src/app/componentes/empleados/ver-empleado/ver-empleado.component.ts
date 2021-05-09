import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EmpleadoVerDto } from 'src/app/HttpMensajes/empleado-ver-dto';
import { Empleado } from 'src/app/modelo/empleado';
import { EmpleadoService } from 'src/app/servicios/empleado.service';

@Component({
  selector: 'app-ver-empleado',
  templateUrl: './ver-empleado.component.html',
  styleUrls: ['./ver-empleado.component.css']
})
export class VerEmpleadoComponent implements OnInit {

  empleadoAVisualizar : EmpleadoVerDto;

  constructor(private empleadoServicio : EmpleadoService,private rutaActiva: ActivatedRoute) { }

  ngOnInit(): void {
    this.getEmpleadoById();
  }

  getEmpleadoById(){
    this.empleadoServicio.getById(this.rutaActiva.snapshot.params.id).subscribe(data => {
      this.empleadoAVisualizar = data;
    });
  }


}
