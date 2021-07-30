import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { tap } from "rxjs/internal/operators";
import { TokenService } from "./token.service";

@Injectable()
export class UnAuthorizedInterceptor implements HttpInterceptor {
  
    constructor(private router: Router,private tokenServicio : TokenService) { }
  
  
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(tap(() => { },
        (err: any) => {
            if (err instanceof HttpErrorResponse) {
            if ((err.status === 401)) {
                this.tokenServicio.logOut();
            } else {
                return;
            }
        }
    }));
  }
}