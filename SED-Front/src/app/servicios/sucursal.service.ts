import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EspecificacionDePuesto } from '../modelo/especificacion-puesto';
import { Sucursal } from '../modelo/sucursal';
import { UrlBaseService } from './url-base.service';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class SucursalService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  public getSucursales(page:number,size:number,sort:string,order:string) : Observable<any>{
    return this.httpClient.get<Sucursal[]>(this.urlBaseService.obtenerURLBase() + `sucursal/index?sort=${sort},${order}&size=${size}&page=${page}`,cabecera);
  }

  public getSucursalesSelect() : Observable<any>{
    return this.httpClient.get<Sucursal[]>(this.urlBaseService.obtenerURLBase() + 'sucursal/select',cabecera);
  }

  public getSucursalesSegunAreaSelect(id:number) : Observable<any>{
    return this.httpClient.get<Sucursal[]>(this.urlBaseService.obtenerURLBase() + `sucursal/select/area/${id}`,cabecera);
  }

  public guardar(sucursal:Sucursal) : Observable<any>{
    return this.httpClient.post<any>(this.urlBaseService.obtenerURLBase() + 'sucursal', sucursal, cabecera);
  }

  public obtenerEspecificacionesDePuestoSucursal(id_sucursal : number) : Observable<EspecificacionDePuesto[]>{
    return this.httpClient.get<EspecificacionDePuesto[]>(this.urlBaseService.obtenerURLBase() + `sucursal/especificaciones_puestos/${id_sucursal}`,cabecera);
  }
}
