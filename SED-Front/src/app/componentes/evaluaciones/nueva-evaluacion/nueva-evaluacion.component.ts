import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BsLocaleService } from 'ngx-bootstrap/datepicker';
import { EmpleadoResumido } from 'src/app/HttpMensajes/empleado-resumido-dto';
import { Empleado } from 'src/app/modelo/empleado';
import { EspecificacionDePuesto } from 'src/app/modelo/especificacion-puesto';
import { PlantillaEvaluacion } from 'src/app/modelo/plantilla-evaluacion';
import { Sucursal } from 'src/app/modelo/sucursal';
import { EmpleadoService } from 'src/app/servicios/empleado.service';
import { EspecificacionPuestoService } from 'src/app/servicios/especificacion-puesto.service';
import { EvaluacionService } from 'src/app/servicios/evaluacion.service';
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
  comboPlantillaEvaluacion : PlantillaEvaluacion[];
  comboEmpleadosEvaluadores : EmpleadoResumido[];
  mensaje = '';
  errors : string[];
  guardado = false;
  error = false;

  configPeriodoInicio : any = { dateInputFormat: 'MMMM YYYY',minMode:'month',containerClass:'theme-blue',}

  constructor(private fb : FormBuilder,
    private sucursalService : SucursalService,
    private especificacionService : EspecificacionPuestoService,
    private empleadoService : EmpleadoService,
    private evaluacionServicio : EvaluacionService) {
  }

  ngOnInit(): void {
    this.validaciones();
    this.cargarComboSucursales();
    this.cargarComboEmpleadosEvaluadores();
  }

  validaciones() : void{
    this.evaluacionForm = this.fb.group({
      fechaInicioEvaluacion : [null,Validators.required],
      fechaFinEvaluacion : [null,Validators.required],
      periodoInicio : [null,Validators.required],
      periodoFin : [null,Validators.required],
      sucursal : [null,Validators.required],
      especificacionPuesto : [null,Validators.required],
      plantillaEvaluacion : [null,Validators.required],
      empleadoEvaluador : [null,Validators.required],
    });
  }

  guardar() : void{
    this.guardado = false;
    this.error = false;
    this.evaluacionServicio.guardar(this.evaluacionForm.value).subscribe(data => {
      this.mensaje = data.mensaje;
      this.guardado = true;
      this.error = false;
      this.evaluacionForm.reset();
    },
      (err: any) => {
        this.mensaje = err.error.message;
        this.errors = err.error.errors;
        this.guardado = false;
        this.error = true;
      }
    );
  }

  cargarComboSucursales() : void{
    this.sucursalService.getSucursalesSelect().subscribe(data =>{
      this.comboSucursales = data;
    });
  }

  cargarComboEspecificacionesPuestos() : void{
    this.evaluacionForm.get("especificacionPuesto").reset();
    this.evaluacionForm.get("plantillaEvaluacion").reset();
    let idSucursal : number = this.evaluacionForm.get('sucursal').value.id;
    this.sucursalService.obtenerEspecificacionesDePuestoSucursal(idSucursal).subscribe(data =>{
      this.comboEspecificacionesPuesto = data;
    })
  }

  cargarComboPlantillasEvaluacion() : void{
    this.evaluacionForm.get("plantillaEvaluacion").reset();
    let idEspecificacionPuesto : number = this.evaluacionForm.get('especificacionPuesto').value.id;
    this.especificacionService.getPlantillasEvaluacionDeEspecificacionPuesto(idEspecificacionPuesto).subscribe(data =>{
      this.comboPlantillaEvaluacion = data;
    });
  }

  cargarComboEmpleadosEvaluadores() : void{
    this.empleadoService.getEmpleadosEvaluadores().subscribe(data =>{
      this.comboEmpleadosEvaluadores = data;
    });
  }

}
