import { Component, OnInit } from '@angular/core';
import { Area } from 'src/app/modelo/area';
import { AreaService } from 'src/app/servicios/area.service';

@Component({
  selector: 'app-areas',
  templateUrl: './areas.component.html',
  styleUrls: ['./areas.component.css']
})
export class AreasComponent implements OnInit {

  areas : Area[] = [];
  cargando = true;

  constructor(private areaServicio : AreaService) { }

  ngOnInit(): void {
    this.cargarAreas();
  }

  cargarAreas() : void{
    this.areaServicio.obtenerAreas().subscribe(data => {
      this.areas = data;
      this.cargando = false;
    });
  }

}
