import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
}
