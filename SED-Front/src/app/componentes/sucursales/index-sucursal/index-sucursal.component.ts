import { Component, OnInit } from '@angular/core';
import { Sucursal } from 'src/app/modelo/sucursal';
import { SucursalService } from 'src/app/servicios/sucursal.service';

@Component({
  selector: 'app-index-sucursal',
  templateUrl: './index-sucursal.component.html',
  styleUrls: ['./index-sucursal.component.css']
})
export class IndexSucursalComponent implements OnInit {

  sucursales : Sucursal[] = [];
  cargando = true;

  constructor(private sucursalServicio : SucursalService) { }

  ngOnInit(): void {
    this.cargarSucursales();
  }

  cargarSucursales() : void{
    this.sucursalServicio.obtenerSucursales().subscribe(data => {
      this.sucursales = data;
      this.cargando = false;
    });
  }



}
