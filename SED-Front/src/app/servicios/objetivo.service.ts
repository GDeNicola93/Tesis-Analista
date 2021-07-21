import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpMensaje } from '../HttpMensajes/http-mensaje';
import { UrlBaseService } from './url-base.service';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class ObjetivoService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  eliminar(id_objetivo : number) : Observable<HttpMensaje>{
    return this.httpClient.get<HttpMensaje>(this.urlBaseService.obtenerURLBase() + `objetivo/eliminar/${id_objetivo}`,cabecera);
  }
}
