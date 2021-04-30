import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UrlBaseService } from './url-base.service';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class UploadImagenPerfilService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  public subirImagenPerfil(body : FormData) : Observable<any>{
    return this.httpClient.post<any>(this.urlBaseService.obtenerURLBase() + 'imagen_perfil/upload',body);
  }
}
