import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EspecificacionDePuesto } from 'src/app/modelo/especificacion-puesto';
import { Sucursal } from 'src/app/modelo/sucursal';
import { SucursalService } from 'src/app/servicios/sucursal.service';

@Component({
  selector: 'app-nueva-evaluacion',
  templateUrl: './nueva-evaluacion.component.html',
  styleUrls: ['./nueva-evaluacion.component.css']
})
export class NuevaEvaluacionComponent implements OnInit {
  evaluacionForm : FormGroup;
  comboSucursales : Sucursal[];
  comboEspecificacionesPuesto : EspecificacionDePuesto[];

  constructor(private fb : FormBuilder,private sucursalService : SucursalService) { }

  ngOnInit(): void {
    this.validaciones();
    this.cargarComboSucursales();
  }

  validaciones() : void{
    this.evaluacionForm = this.fb.group({
      fechaInicioEvaluacion : ['',Validators.required],
      fechaFinEvaluacion : ['',Validators.required],
      sucursal : ['',Validators.required],
      especificacionPuesto : ['',Validators.required],
    });
  }

  guardar() : void{

  }

  cargarComboSucursales() : void{
    this.sucursalService.getSucursalesSelect().subscribe(data =>{
      this.comboSucursales = data;
    });
  }

  cargarComboEspecificacionesPuestos() : void{
    this.evaluacionForm.get("especificacionPuesto").reset();
    let idSucursal : number = this.evaluacionForm.get('sucursal').value.id;
    this.sucursalService.obtenerEspecificacionesDePuestoSucursal(idSucursal).subscribe(data =>{
      this.comboEspecificacionesPuesto = data;
    })
  }

}
