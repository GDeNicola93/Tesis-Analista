import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Jwt } from '../HttpMensajes/jwt';
import { TokenService } from './token.service';
import { UsuarioService } from './usuario.service';
import { catchError, concatMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor(private tokenServicio: TokenService, private usuarioService: UsuarioService) { }


  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token: string = this.tokenServicio.getToken();

    let request = req;

    if (token) {
      request = req.clone({
        setHeaders: {
          authorization: `Bearer ${token}`
        }
      });
    }

    return next.handle(request).pipe(catchError((err: HttpErrorResponse) => {
      if (err.status === 401) {
        const jwtDTO = new Jwt(this.tokenServicio.getToken());
        return this.usuarioService.refreshToken(jwtDTO).pipe(concatMap((data: any) => {
          console.log("Refrescando token...");
          this.tokenServicio.setToken(data.token);
          request = req.clone({
            setHeaders: {
              authorization: `Bearer ${data.token}`
            }
          });
          return next.handle(request);
        }));
      } else {
        this.tokenServicio.logOut();
        return throwError(err);
      }
    }));
  }
}
