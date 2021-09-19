import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Usuario } from 'src/app/modelo/usuario';
import { TokenService } from 'src/app/servicios/token.service';
import { UsuarioService } from 'src/app/servicios/usuario.service';

@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrls: ['./perfil-usuario.component.css']
})
export class PerfilUsuarioComponent implements OnInit   {
  cargando = true;
  usuario !: Usuario;


  constructor(private tokenServicio : TokenService,private usuarioServicio : UsuarioService) {}

  ngOnInit(): void {
    this.obtenerUsuarioLogeado();
  }


  obtenerUsuarioLogeado() : void{
    this.usuarioServicio.obtenerDatosUsuarioLogeado().subscribe(data => {
      this.usuario = data;
      this.cargando = false;
    });
  }
}
