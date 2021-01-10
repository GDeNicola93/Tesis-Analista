import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenService } from '../servicios/token.service';

@Injectable({
  providedIn: 'root'
})
export class GeneralGuard implements CanActivate {

  realRol: string = "";
  
  constructor(private tokenServicio : TokenService,private router : Router){}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const expectedRol = route.data.expectedRol;

    if(this.tokenServicio.isAdmin()){
      this.realRol = 'Administrador';
    }
    if(this.tokenServicio.isEvaluador()){
      this.realRol = 'Evaluador';
    }
    if(this.tokenServicio.isEmpleado()){
      this.realRol = 'Empleado';
    }
    if (!this.tokenServicio.isLogged() || expectedRol.indexOf(this.realRol) < 0) {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
  
}
