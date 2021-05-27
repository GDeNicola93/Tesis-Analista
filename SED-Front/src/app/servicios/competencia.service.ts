import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Competencia } from '../modelo/competencia';
import { UrlBaseService } from './url-base.service';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class CompetenciaService {

  constructor(private httpClient: HttpClient,private urlBaseService : UrlBaseService) { }

  public guardar(competencia:Competencia) : Observable<any>{
    return this.httpClient.post<any>(this.urlBaseService.obtenerURLBase() + 'competencia', competencia, cabecera);
  }

  public obtenerCompetencias(page:number,size:number,sort:string,order:string) : Observable<any>{
    return this.httpClient.get<Competencia[]>(this.urlBaseService.obtenerURLBase() + `competencia?sort=${sort},${order}&size=${size}&page=${page}`);
  }
}
