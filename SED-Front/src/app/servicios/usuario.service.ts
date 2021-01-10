import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Jwt } from '../modelo/jwt';
import { LoginUsuario } from '../modelo/login-usuario';
import { UrlBaseService } from './url-base.service';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  public login(loginUsuario : LoginUsuario) : Observable<Jwt>{
    return this.httpClient.post<Jwt>(this.urlBaseService.obtenerURLBase() + 'autenticacion/login',loginUsuario);
  }


}
