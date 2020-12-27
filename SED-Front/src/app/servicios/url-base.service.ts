import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class UrlBaseService {

   url_base : string = "http://localhost:8080/";
  
  constructor() { }

  public obtenerURLBase():string{
    return this.url_base;
  }
}
