import { Component, OnInit } from '@angular/core';
import { Area } from 'src/app/modelo/area';
import { Sucursal } from 'src/app/modelo/sucursal';
import { AreaService } from 'src/app/servicios/area.service';
import { SucursalService } from 'src/app/servicios/sucursal.service';

@Component({
  selector: 'app-nueva-sucursal',
  templateUrl: './nueva-sucursal.component.html',
  styleUrls: ['./nueva-sucursal.component.css']
})
export class NuevaSucursalComponent implements OnInit {

  form: any = {};
  mensaje = '';
  guardado = false;
  error = false;
  selectAreas : Area[] = []; 
  areasSeleccionadas : Area[] = [];
  cargando = true;

  constructor(private sucursalServicio:SucursalService,private areaServicio : AreaService) { }

  ngOnInit(): void {
    this.construirSelectAreas();
  }

  guardar():void {
    this.guardado = false;
    this.error = false;
    
    let sucursal = new Sucursal(this.form.nombre,this.form.descripcion,this.areasSeleccionadas);
    
    this.sucursalServicio.guardar(sucursal).subscribe(data => {
      this.mensaje = data.mensaje;
      this.guardado = true;
      this.error = false;
    },
      (err: any) => {
        this.mensaje = err.error.mensaje;
        this.guardado = false;
        this.error = true;
      }
    );
  }

  construirSelectAreas() : void{
    this.areaServicio.obtenerAreas().subscribe(data => {
      this.selectAreas = data;
      this.cargando = false;
    });
  }

  seleccionarArea(area:Area){
    this.areasSeleccionadas.push(area); //Agrego el area seleccionada al array areasSeleccionadas

    //A continuacion elimino el area selecionada del array selectAreas que contiene todas las areas.
    var i = this.selectAreas.indexOf(area);
    if(i !== -1 ){
      this.selectAreas.splice(i,1);
    }
  }

  desSeleccionarArea(area:Area){
    var i = this.areasSeleccionadas.indexOf(area);
    if(i !== -1 ){
      this.areasSeleccionadas.splice(i,1);
      this.selectAreas.push(area);
    }
  }

}
