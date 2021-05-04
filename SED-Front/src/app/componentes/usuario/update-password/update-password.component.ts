import { Component, OnInit } from '@angular/core';
import { CambioPassword } from 'src/app/HttpMensajes/cambio-password';
import { UsuarioService } from 'src/app/servicios/usuario.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent implements OnInit {
  cambioPassword : any = {};
  mensaje = '';
  guardado = false;
  error = false;

  constructor(private usuarioServicio:UsuarioService) { }

  ngOnInit(): void {
  }

  guardar() : void {
    this.mensaje = '';
    this.guardado = false;
    this.error = false;
    this.usuarioServicio.actualizarPassword(this.cambioPassword).subscribe(data => {
      this.mensaje = data.mensaje;
      this.guardado = true;
      this.error = false;
      this.cambioPassword = {};
    },
      (err: any) => {
        this.mensaje = err.error.mensaje;
        this.guardado = false;
        this.error = true;
      }
    );
  }

}
