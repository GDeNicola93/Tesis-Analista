import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmpleadoIndexDto } from '../HttpMensajes/empleado-index-dto';
import { EmpleadoVerDto } from '../HttpMensajes/empleado-ver-dto';
import { Empleado } from '../modelo/empleado';
import { UrlBaseService } from './url-base.service';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  getEmpleados(page:number,size:number,sort:string,order:string,filtro:string) : Observable<any>{
    return this.httpClient.get<EmpleadoIndexDto[]>(this.urlBaseService.obtenerURLBase() + `empleados?filtro=${filtro}&sort=${sort},${order}&size=${size}&page=${page}`,cabecera);
  }

  getById(id : number) : Observable<EmpleadoVerDto>{
    return this.httpClient.get<EmpleadoVerDto>(this.urlBaseService.obtenerURLBase() + 'empleados?id='+id,cabecera);
  }

  getEmpleadosEvaluadores() : Observable<Empleado[]>{
    return this.httpClient.get<Empleado[]>(this.urlBaseService.obtenerURLBase() + 'empleados/evaluadores',cabecera);
  }
}
