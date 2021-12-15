import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DetalleEvaluacionVersusReporteDto } from '../HttpMensajes/DetalleEvaluacionVersusReporteDto';
import { Competencia } from '../modelo/competencia';
import { UrlBaseService } from './url-base.service';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class ReportesService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  getDetalleEvaluacionByIdParaVersusReporte(id_detalle_evaluacion : number) : Observable<any>{
    return this.httpClient.get<any>(this.urlBaseService.obtenerURLBase() + `reportes/versus_grado_minimo/detalle_evaluacion/${id_detalle_evaluacion}`,cabecera);
  }

  getDetalleEvaluacionByIdEvaluacionParaComparacionReporte(id_evaluacion : number,comp : Competencia) : Observable<any>{
    return this.httpClient.post<any>(this.urlBaseService.obtenerURLBase() + `reportes/comparacion_de_evaluaciones_por_competencia/${id_evaluacion}`,comp,cabecera);
  }
}
