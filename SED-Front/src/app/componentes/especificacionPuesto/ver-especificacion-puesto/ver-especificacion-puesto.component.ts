import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EspecificacionDePuestoVerDto } from 'src/app/HttpMensajes/especificacion-puesto-ver-dto';
import { EspecificacionPuestoService } from 'src/app/servicios/especificacion-puesto.service';

@Component({
  selector: 'app-ver-especificacion-puesto',
  templateUrl: './ver-especificacion-puesto.component.html',
  styleUrls: ['./ver-especificacion-puesto.component.css']
})
export class VerEspecificacionPuestoComponent implements OnInit {
  edp : EspecificacionDePuestoVerDto;
  
  constructor(private rutaActiva: ActivatedRoute,private especificacionPuestoService : EspecificacionPuestoService) { }

  ngOnInit(): void {
    this.getEspecificacionesDePuestosById();
  }

  getEspecificacionesDePuestosById(){
    this.especificacionPuestoService.getEspecificacionesDePuestosById(this.rutaActiva.snapshot.params.id).subscribe(data => {
      this.edp = data;
    });
  }
}
