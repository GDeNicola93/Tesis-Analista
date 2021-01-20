import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Rol } from '../modelo/rol';
import { UrlBaseService } from './url-base.service';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class RolService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  obtenerRoles() : Observable<any>{
    return this.httpClient.get<Rol[]>(this.urlBaseService.obtenerURLBase() + 'rol',cabecera);
  }
}
