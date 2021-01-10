import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/servicios/token.service';
import { UsuarioService } from 'src/app/servicios/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form : any = {};
  error : boolean = false;
  mensaje : string = "";

  constructor(private usuarioServicio : UsuarioService,private tokenServicio : TokenService,private router : Router) { }

  ngOnInit(): void {
  }

  login() : void {
    this.error = false;
    this.usuarioServicio.login(this.form).subscribe(
      data => {
        this.tokenServicio.setToken(data.token);
        this.router.navigate(['/home']);
      },
      err => {
        this.error = true;
        this.mensaje = "Usuario y/o contraseña invalidos!";
      }
    );
  }

}
