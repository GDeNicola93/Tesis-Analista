import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Jwt } from '../modelo/jwt';
import { LoginUsuario } from '../modelo/login-usuario';
import { Usuario } from '../modelo/usuario';
import { UrlBaseService } from './url-base.service';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  public login(loginUsuario : LoginUsuario) : Observable<Jwt>{
    return this.httpClient.post<Jwt>(this.urlBaseService.obtenerURLBase() + 'autenticacion/login',loginUsuario);
  }

  public guardar(usuario : Usuario) : Observable<any>{
    return this.httpClient.post<any>(this.urlBaseService.obtenerURLBase() + 'autenticacion/nuevo',usuario,cabecera);
  }


}
