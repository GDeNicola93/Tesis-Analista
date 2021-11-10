import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenService } from '../servicios/token.service';

@Injectable({
  providedIn: 'root'
})
export class GeneralGuard implements CanActivate {

  realRol: string[] = [];
  
  constructor(private tokenServicio : TokenService,private router : Router){}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const expectedRol = route.data.expectedRol;
    let esRol : Boolean;

    if(this.tokenServicio.isAdmin()){
      this.realRol.push('Administrador');
    }
    if(this.tokenServicio.isEvaluador()){
      this.realRol.push('Evaluador');
    }
    if(this.tokenServicio.isEmpleado()){
      this.realRol.push('Empleado');
    }
    for (let rol of this.realRol) {
      if(expectedRol.indexOf(this.realRol) !== -1){
        esRol = true;
      }
    }
    
    if (!this.tokenServicio.isLogged() || esRol == false) {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
  
}
