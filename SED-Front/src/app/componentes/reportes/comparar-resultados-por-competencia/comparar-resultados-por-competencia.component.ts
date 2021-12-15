import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Label } from 'ng2-charts';
import { DetalleEvaluacionComparacionCompetenciasDto } from 'src/app/HttpMensajes/DetalleEvaluacionComparacionCompetenciasDto';
import { Competencia } from 'src/app/modelo/competencia';
import { EvaluacionService } from 'src/app/servicios/evaluacion.service';
import { ReportesService } from 'src/app/servicios/reportes.service';

@Component({
  selector: 'app-comparar-resultados-por-competencia',
  templateUrl: './comparar-resultados-por-competencia.component.html',
  styleUrls: ['./comparar-resultados-por-competencia.component.css']
})
export class CompararResultadosPorCompetenciaComponent implements OnInit {
  mensaje = '';
  error = false;
  cargando = true;
  competencias : Competencia[];
  competenciaSeleccionada : Competencia;
  idEvaluacion : number;
  detallesEvaluacion : DetalleEvaluacionComparacionCompetenciasDto[] = [];
  resultadosObtenidos : number[] = [];

  public barChartOptions: ChartOptions = {
    responsive: true,
    // We use these empty structures as placeholders for dynamic theming.
    scales: { xAxes: [{}], yAxes: [{ticks:{beginAtZero: true}}] },
    plugins: {
      datalabels: {
        anchor: 'end',
        align: 'end',
      }
    }
  };
  public barChartLabels: Label[] = [];
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;

  public barChartData: ChartDataSets[] = [
    { data: this.resultadosObtenidos, label: 'Resultado obtenido' }
  ];

  constructor(private rutaActiva: ActivatedRoute,private evaluacionServicio : EvaluacionService,private reportesServicio : ReportesService) { }

  ngOnInit(): void {
    this.idEvaluacion = this.rutaActiva.snapshot.params.id_evaluacion;
    this.getCompetenciasEvaluadasEnEvaluaicion();
  }

  getCompetenciasEvaluadasEnEvaluaicion(){
    this.error = false;
    this.evaluacionServicio.getCompetenciasEvaluadasEnEvaluaicion(this.idEvaluacion).subscribe(data =>{
      this.competencias = data;
      this.cargando = false;
    },
      (err : any)=>{
        this.error = true;
        this.mensaje = err.error.message;
        this.cargando = false;
      }
    );
  }

  getResultadosCompetencia(){
    this.resetGrafico();
    this.reportesServicio.getDetalleEvaluacionByIdEvaluacionParaComparacionReporte(this.idEvaluacion,this.competenciaSeleccionada).subscribe(data =>{
      this.detallesEvaluacion = data;
      this.cargarNombresEvaluados();
    });
  }

  cargarNombresEvaluados() { 
    for(let de of this.detallesEvaluacion){
      this.barChartLabels.push(de.nombreEvaluado);
    }
    this.cargarResultadosObtenidos();
  }

  cargarResultadosObtenidos() {
    for(let de of this.detallesEvaluacion){
      this.resultadosObtenidos.push(de.resultadoCompetencia);
    }
  }

  resetGrafico(){
    this.resultadosObtenidos.length = 0;
    this.barChartLabels.length = 0;
  }





}
