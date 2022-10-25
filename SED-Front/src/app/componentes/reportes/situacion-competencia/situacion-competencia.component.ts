import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Label } from 'ng2-charts';
import { RespuestaSituacionCompetenciaDTO } from 'src/app/HttpMensajes/SituacionCompetenciaEnPeriodo';
import { Competencia } from 'src/app/modelo/competencia';
import { CompetenciaService } from 'src/app/servicios/competencia.service';
import { ReportesService } from 'src/app/servicios/reportes.service';

@Component({
  selector: 'app-situacion-competencia',
  templateUrl: './situacion-competencia.component.html',
  styleUrls: ['./situacion-competencia.component.css']
})
export class SituacionCompetenciaComponent implements OnInit {
  info : FormGroup;
  comboCompetencias : Competencia[];
  result : RespuestaSituacionCompetenciaDTO;
  error : boolean = false;
  mensajeError : string;

  configPeriodo : any = { dateInputFormat: 'MMMM YYYY',minMode:'month',containerClass:'theme-blue'};

  pieChartOptions: ChartOptions = {
    responsive: true,
    maintainAspectRatio: false,//Para poder achicar el grafico
    legend: {
      position: 'top',
    },
    plugins: {
      datalabels: {
        formatter: (value : any, ctx : any) => {
          const label = ctx.chart.data.labels[ctx.dataIndex];
          return label;
        },
      },
    }
  };
  // public pieChartLabels: Label[] = ['Evaluados '+(this.evaluacion.porcentajeEvaluados),'No Evaluados (70%)'];
  public pieChartLabels: Label[]
  public pieChartData: number[];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartColors = [
    {
      backgroundColor: ['rgba(0,255,0,0.3)','rgba(0, 0, 255, 0.6)','rgba(255,0,0,0.3)'],
    },
  ];

  constructor(private fb : FormBuilder,private competenciaService : CompetenciaService,private reportesServicio : ReportesService) { }

  ngOnInit(): void {
    this.validaciones();
    this.cargarComboCompetencias();
  }

  validaciones() : void{
    this.info = this.fb.group({
      competencia : [null,Validators.required],
      periodo : [null,Validators.required]
    });
  }

  cargarComboCompetencias() : void{
    this.competenciaService.obtenerCompetenciasParaSelect().subscribe(data =>{
      this.comboCompetencias = data;
    });
  }

  generarInforme() : void{
    this.reportesServicio.situacionCompetenciaEnPeriodo(this.info.value).subscribe(data =>{
      this.error = false;
      this.result = data;
      
      this.pieChartLabels = ['Superaron o alcanzaron el mínimo ('+data.porcentajeSuperaronOalcanzaronMin+'%)','No alcanzaron el mínimo requerido ('+data.porcentajeNoAlcanzaronMinimoRequerido+'%)','No evaluados ('+data.porcentajeNoEvaluados+'%)'];
      this.pieChartData = [data.cantSuperaronOalcanzaronMin,data.cantidadNoAlcanzaronMinimoRequerido,data.cantNoEvaluados];
    },err =>{
      this.error = true;
      this.result = null;
      this.mensajeError = err.error.message;
    });
  }

}
