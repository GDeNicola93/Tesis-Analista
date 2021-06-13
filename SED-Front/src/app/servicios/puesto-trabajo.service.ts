import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PuestoTrabajo } from '../modelo/puesto-trabajo';
import { UrlBaseService } from './url-base.service';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class PuestoTrabajoService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  guardar(puesto : PuestoTrabajo) : Observable<any>{
    return this.httpClient.post<any>(this.urlBaseService.obtenerURLBase() + 'puesto-trabajo', puesto, cabecera);
  }

  getPuestosTrabajosSelect() : Observable<any>{
    return this.httpClient.get<PuestoTrabajo[]>(this.urlBaseService.obtenerURLBase() + 'puesto-trabajo',cabecera);
  }

  obtenerPuestosXSucursal(id_sucursal : number) : Observable<any>{
    return this.httpClient.get<PuestoTrabajo[]>(this.urlBaseService.obtenerURLBase() + 'puesto-trabajo?sucursal_id='+id_sucursal,cabecera);
  } 

}
