import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { HttpMensaje } from '../HttpMensajes/http-mensaje';
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
}
