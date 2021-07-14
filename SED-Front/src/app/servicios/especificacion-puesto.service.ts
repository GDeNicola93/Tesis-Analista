import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EspecificacionDePuestoVerDto } from '../HttpMensajes/especificacion-puesto-ver-dto';
import { HttpMensaje } from '../HttpMensajes/http-mensaje';
import { EspecificacionDePuesto } from '../modelo/especificacion-puesto';
import { PlantillaEvaluacion } from '../modelo/plantilla-evaluacion';
import { UrlBaseService } from './url-base.service';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class EspecificacionPuestoService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  public guardar(edp:EspecificacionDePuesto) : Observable<HttpMensaje>{
    return this.httpClient.post<HttpMensaje>(this.urlBaseService.obtenerURLBase() + 'especificacion-puesto', edp, cabecera);
  }

  getEspecificacionesDePuestos(page:number,size:number,sort:string,order:string) : Observable<any>{
    return this.httpClient.get<any>(this.urlBaseService.obtenerURLBase() + `especificacion-puesto/index?sort=${sort},${order}&size=${size}&page=${page}`,cabecera);
  }

  getEspecificacionesDePuestosParaSelect() : Observable<any>{
    return this.httpClient.get<any>(this.urlBaseService.obtenerURLBase() + 'especificacion-puesto/for-select',cabecera);
  }

  getEspecificacionesDePuestosById(id : number) : Observable<EspecificacionDePuestoVerDto>{
    return this.httpClient.get<EspecificacionDePuestoVerDto>(this.urlBaseService.obtenerURLBase() + `especificacion-puesto?id=${id}`,cabecera);
  }

  getPlantillasEvaluacionDeEspecificacionPuesto(idEspecificacionPuesto : number) : Observable<PlantillaEvaluacion[]>{
    return this.httpClient.get<PlantillaEvaluacion[]>(this.urlBaseService.obtenerURLBase() + `especificacion-puesto/plantillas/${idEspecificacionPuesto}`,cabecera);
  }
}
