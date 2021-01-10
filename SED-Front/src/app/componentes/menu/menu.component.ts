import { Component, OnInit } from '@angular/core';
import { TokenService } from 'src/app/servicios/token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(private tokenServicio : TokenService) { }

  nombreUsuario : string = "";
  isAdministrador : boolean = false;

  ngOnInit(): void {
    this.getNombreUsuario();
    this.getIsAdministrador();
  }

  cerrarSesion() : void {
    this.tokenServicio.logOut();
  }

  getNombreUsuario() : void {
    this.nombreUsuario = this.tokenServicio.getUserName();
  }

  getIsAdministrador() : void {
    this.isAdministrador=this.tokenServicio.isAdmin();
  }

}
