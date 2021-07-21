import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EspecificacionDePuestoVerDto } from 'src/app/HttpMensajes/especificacion-puesto-ver-dto';
import { EspecificacionPuestoService } from 'src/app/servicios/especificacion-puesto.service';
import { ObjetivoService } from 'src/app/servicios/objetivo.service';

@Component({
  selector: 'app-ver-especificacion-puesto',
  templateUrl: './ver-especificacion-puesto.component.html',
  styleUrls: ['./ver-especificacion-puesto.component.css']
})
export class VerEspecificacionPuestoComponent implements OnInit {
  edp : EspecificacionDePuestoVerDto;
  idObjetivoAEliminar: number;
  
  constructor(private rutaActiva: ActivatedRoute,
    private especificacionPuestoService : EspecificacionPuestoService,
    private modalService: NgbModal,
    private objetivoService : ObjetivoService
    ) { }

  ngOnInit(): void {
    this.getEspecificacionesDePuestosById();
  }

  getEspecificacionesDePuestosById(){
    this.especificacionPuestoService.getEspecificacionesDePuestosById(this.rutaActiva.snapshot.params.id).subscribe(data => {
      this.edp = data;
    });
  }

  eliminarObjetivoConfirmacion(content : any,idObjetivoAEliminar : number){
    this.idObjetivoAEliminar = idObjetivoAEliminar;
    this.modalService.open(content, { size: 'lg' });
  }

  eliminar() : void{
    this.objetivoService.eliminar(this.idObjetivoAEliminar).subscribe(data =>{
      this.modalService.dismissAll();
      window.location.reload();
    });
  }
}
