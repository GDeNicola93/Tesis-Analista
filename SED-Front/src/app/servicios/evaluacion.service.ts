import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { EvaluacionVerDto } from '../HttpMensajes/evaluacion-ver-dto';
import { HttpMensaje } from '../HttpMensajes/http-mensaje';
import { ResultadoDto } from '../HttpMensajes/resultado-dto';
import { UrlBaseService } from './url-base.service';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class EvaluacionService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  guardar(evaluacion:FormGroup) : Observable<HttpMensaje>{
    return this.httpClient.post<HttpMensaje>(this.urlBaseService.obtenerURLBase() + 'evaluacion',evaluacion,cabecera);
  }

  getEvaluaciones(page:number,size:number,sort:string,order:string,estado:string,filtro:string,filtroFecha:string) : Observable<any>{
    if(filtroFecha != "undefined" && filtroFecha != ""){
      return this.httpClient.get<any>(this.urlBaseService.obtenerURLBase() + `evaluacion?estado=${estado}&filtro=${filtro}&filtroFecha=${filtroFecha}&sort=${sort},${order}&size=${size}&page=${page}`,cabecera);
    }
    return this.httpClient.get<any>(this.urlBaseService.obtenerURLBase() + `evaluacion?estado=${estado}&filtro=${filtro}&sort=${sort},${order}&size=${size}&page=${page}`,cabecera);
  }

  getEvaluacionById(id_evaluacion : number) : Observable<EvaluacionVerDto>{
    return this.httpClient.get<EvaluacionVerDto>(this.urlBaseService.obtenerURLBase() + `evaluacion?id=${id_evaluacion}`,cabecera);
  }

  getEvaluacionesEvaluadorLogeado(page:number,size:number,sort:string,order:string) : Observable<any>{
    return this.httpClient.get<any>(this.urlBaseService.obtenerURLBase() + `evaluacion/evaluador/logeado?sort=${sort},${order}&size=${size}&page=${page}`,cabecera);
  }

  cancelarEvaluacion(id_evaluacion : number) : Observable<HttpMensaje>{
    return this.httpClient.get<HttpMensaje>(this.urlBaseService.obtenerURLBase() + `evaluacion/cancelar/${id_evaluacion}`,cabecera);
  }

  getEmpleadosAEvaluarEvaluacion(id_evaluacion : number) : Observable<any>{
    return this.httpClient.get<any>(this.urlBaseService.obtenerURLBase() + `evaluacion/empleados_a_evaluar/${id_evaluacion}`,cabecera);
  }

  getDetalleEvaluacionById(id_detalle : number) : Observable<any>{
    return this.httpClient.get<any>(this.urlBaseService.obtenerURLBase() + `evaluacion/detalle_evaluacion/${id_detalle}`,cabecera);
  }

  getDetallesEvaluacionByIdEvaluacion(id_evaluacion : number) : Observable<any>{
    return this.httpClient.get<any>(this.urlBaseService.obtenerURLBase() + `evaluacion/detalles_evaluacion/${id_evaluacion}`,cabecera);
  }

  getMisEvaluaciones(page:number,size:number,sort:string,order:string) : Observable<any>{
    return this.httpClient.get<any>(this.urlBaseService.obtenerURLBase() + `evaluacion/mis_evaluaciones?sort=${sort},${order}&size=${size}&page=${page}`,cabecera);
  }

  getEvaluarEmpleado(id_detalle_evaluacion : number) : Observable<any>{
    return this.httpClient.get<any>(this.urlBaseService.obtenerURLBase() + `evaluacion/evaluar/${id_detalle_evaluacion}`,cabecera);
  }

  guardarResultados(resultadosDto : ResultadoDto[]) : Observable<any>{
    return this.httpClient.post<any>(this.urlBaseService.obtenerURLBase() + 'evaluacion/evaluar/resultados',resultadosDto,cabecera);
  }
}
