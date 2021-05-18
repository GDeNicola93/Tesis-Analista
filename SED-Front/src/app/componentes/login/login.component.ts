import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  cargando = false;
  LoginForm : FormGroup;

  constructor(private usuarioServicio : UsuarioService,private tokenServicio : TokenService,private router : Router,private fb : FormBuilder) { }

  ngOnInit(): void {
  }

  login() : void {
    this.error = false;
    this.cargando = true;
    this.usuarioServicio.login(this.form).subscribe(
      data => {
        this.tokenServicio.setToken(data.token);
        this.cargando = false;
        this.router.navigate(['/home']);
      },
      err => {
        this.cargando = false;
        this.error = true;
        this.mensaje = "Usuario y/o contraseña invalidos!";
      }
    );
  }
}
