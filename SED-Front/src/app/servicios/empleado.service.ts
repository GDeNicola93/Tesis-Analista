import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Empleado } from '../modelo/empleado';
import { UrlBaseService } from './url-base.service';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  obtenerEmpleados() : Observable<any>{
    return this.httpClient.get<Empleado[]>(this.urlBaseService.obtenerURLBase() + 'empleados',cabecera);
  }

  buscarEmpleado(dato : string) : Observable<any>{
    return this.httpClient.get<Empleado[]>(this.urlBaseService.obtenerURLBase() + 'empleados?dato='+dato,cabecera);
  }
}
