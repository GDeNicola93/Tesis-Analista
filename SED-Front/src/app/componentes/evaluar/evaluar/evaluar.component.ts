import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ResultadoDto } from 'src/app/HttpMensajes/resultado-dto';
import { ComportamientoPlantilla } from 'src/app/modelo/comportamiento-plantilla';
import { DetallePlantilla } from 'src/app/modelo/detalle-plantilla';
import { PlantillaEvaluacion } from 'src/app/modelo/plantilla-evaluacion';
import { EvaluacionService } from 'src/app/servicios/evaluacion.service';

@Component({
  selector: 'app-evaluar',
  templateUrl: './evaluar.component.html',
  styleUrls: ['./evaluar.component.css']
})
export class EvaluarComponent implements OnInit {
  idDetalleEvaluacion : number;
  plantillaEvaluacion : PlantillaEvaluacion;
  mensaje = '';
  errorSuperior = false; //Errores anteriores a comenzar a Evaluar
  errorInferior = false; //Errores que ocurren despues de Evaluar
  cargando = true;
  guardado = false;
  resultados : ResultadoDto[] = [];

  constructor(private rutaActiva: ActivatedRoute,private evaluacionServicio : EvaluacionService) { }

  ngOnInit(): void {
    this.idDetalleEvaluacion = this.rutaActiva.snapshot.params.id_detalle;
    this.getPlantillaEvaluacion();
  }

  getPlantillaEvaluacion() : void{
    this.errorSuperior = false;
    this.evaluacionServicio.getEvaluarEmpleado(this.idDetalleEvaluacion).subscribe(data =>{
      this.plantillaEvaluacion = data;
      this.cargando = false;

      //ordeno los comportamientos
      for(let dp of this.plantillaEvaluacion.detallePlantilla){
        dp.comportamiento.sort((a,b)=> a.valoracionNumerica < b.valoracionNumerica ? 1:-1);
      }
    },
      (err : any)=>{
        this.errorSuperior = true;
        this.mensaje = err.error.message;
        this.cargando = false;
      }
    );
  }

  tomarOpcionSeleccionada(detallePlantillaSeleccionado : DetallePlantilla,comportamientoPlantillaSeleccionado : ComportamientoPlantilla) : void{
    let nuevo : ResultadoDto = new ResultadoDto(this.idDetalleEvaluacion,detallePlantillaSeleccionado,comportamientoPlantillaSeleccionado);
    var i = this.resultados.findIndex(x => x.detallePlantilla === detallePlantillaSeleccionado);
    if(i == -1){
      this.resultados.push(nuevo);
    }else{
      this.resultados.splice(i,1);
      this.resultados.push(nuevo);
    }
  }

  guardarResultado() : void{
    this.guardado = false;
    this.errorInferior = false;
    this.evaluacionServicio.guardarResultados(this.resultados).subscribe(data => {
      this.mensaje = data.mensaje;
      this.guardado = true;
      this.errorInferior = false;
    },
      (err: any) => {
        this.mensaje = err.error.message;
        this.guardado = false;
        this.errorInferior = true;
      }
    );
  }

}
