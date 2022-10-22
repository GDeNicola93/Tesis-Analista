import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpMensaje } from '../HttpMensajes/http-mensaje';
import { PlantillaEvaluacion } from '../modelo/plantilla-evaluacion';
import { UrlBaseService } from './url-base.service';


const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class PlantillaEvaluacionService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  public guardar(plantilla:PlantillaEvaluacion) : Observable<any>{
    return this.httpClient.post<any>(this.urlBaseService.obtenerURLBase() + 'plantilla-evaluacion', plantilla, cabecera);
  }

  public obtenerPlantillas() : Observable<any>{
    return this.httpClient.get<PlantillaEvaluacion[]>(this.urlBaseService.obtenerURLBase() + 'plantilla-evaluacion?sort=fechaCreaccion,desc',cabecera);
  }

  public editarGet(idPlantilla : number) : Observable<PlantillaEvaluacion>{
    return this.httpClient.get<PlantillaEvaluacion>(this.urlBaseService.obtenerURLBase() + `plantilla-evaluacion/${idPlantilla}/editar`,cabecera);
  }

  public sacarDeCurso(idPlantilla : number,estaEnCurso : boolean) : Observable<HttpMensaje>{
    return this.httpClient.put<HttpMensaje>(this.urlBaseService.obtenerURLBase() + `plantilla-evaluacion/${idPlantilla}/sacar_de_curso?estaEnCurso=${estaEnCurso}`,cabecera);
  }
}
