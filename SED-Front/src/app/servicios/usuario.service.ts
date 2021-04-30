import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Jwt } from '../HttpMensajes/jwt';
import { LoginUsuario } from '../HttpMensajes/login-usuario';
import { Usuario } from '../modelo/usuario';
import { UrlBaseService } from './url-base.service';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  public login(loginUsuario : LoginUsuario) : Observable<Jwt>{
    return this.httpClient.post<Jwt>(this.urlBaseService.obtenerURLBase() + 'usuario/login',loginUsuario);
  }

  public guardar(usuario : Usuario) : Observable<any>{
    return this.httpClient.post<any>(this.urlBaseService.obtenerURLBase() + 'usuario/nuevo',usuario,cabecera);
  }

  public obtenerDatosUsuarioLogeado() : Observable<any>{
    return this.httpClient.get<Usuario>(this.urlBaseService.obtenerURLBase() + 'usuario/obtener_datos',cabecera);
  }

  public setearImagenPerfil(file_name : string) : Observable<any>{
    return this.httpClient.put(this.urlBaseService.obtenerURLBase() + '/setear_imagen_perfil/' +file_name,cabecera);
  }
}
