import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Area } from '../modelo/area';
import { UrlBaseService } from './url-base.service';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class AreaService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }


  public guardar(area:Area) : Observable<any>{
    return this.httpClient.post<any>(this.urlBaseService.obtenerURLBase() + 'area', area, cabecera);
  }

  public obtenerAreas() : Observable<any>{
    return this.httpClient.get<Area[]>(this.urlBaseService.obtenerURLBase() + 'area',cabecera);
  }

}
