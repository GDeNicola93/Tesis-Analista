import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Label } from 'ng2-charts';
import { DetalleEvaluacionVersusReporteDto } from 'src/app/HttpMensajes/DetalleEvaluacionVersusReporteDto';
import { ReportesService } from 'src/app/servicios/reportes.service';

@Component({
  selector: 'app-versus-grado-minimo',
  templateUrl: './versus-grado-minimo.component.html',
  styleUrls: ['./versus-grado-minimo.component.css']
})
export class VersusGradoMinimoComponent implements OnInit {
  mensaje = '';
  error = false;
  cargando = true;
  detalleEvaluacion : DetalleEvaluacionVersusReporteDto;
  idDetalleEvaluacion : number;
  gradosMinimosRequeridos : number[] = [];
  gradosObtenidosEnEvaluacion : number[] = [];

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
    { data: this.gradosObtenidosEnEvaluacion, label: 'Obtenido en evaluación' },
    { data: this.gradosMinimosRequeridos, label: 'Mínimo grado requerido' }
  ];

  constructor(private rutaActiva: ActivatedRoute,private reporteServicio : ReportesService) { }

  ngOnInit(): void {
    this.idDetalleEvaluacion = this.rutaActiva.snapshot.params.id_detalle;
    this.getDetalleEvaluacion();
  }

  getDetalleEvaluacion() : void{
    this.error = false;
    this.reporteServicio.getDetalleEvaluacionByIdParaVersusReporte(this.idDetalleEvaluacion).subscribe(data =>{
      this.detalleEvaluacion = data;
      this.cargando = false;
      this.cargarCompetencias();
    },
      (err : any)=>{
        this.error = true;
        this.mensaje = err.error.message;
        this.cargando = false;
      }
    );
  }

  cargarCompetencias() : void{
    for(let r of this.detalleEvaluacion.resultados){
      this.barChartLabels.push(r.comptetencia);
    }
    this.cargarGradosMinimosRequeridos();
  }

  cargarGradosMinimosRequeridos() : void{
    for(let r of this.detalleEvaluacion.resultados){
      this.gradosMinimosRequeridos.push(r.minimaValoracionNumerica);
    }
    this.cargarObtenidoEnEvaluacion();
  }

  cargarObtenidoEnEvaluacion() : void{
    for(let r of this.detalleEvaluacion.resultados){
      this.gradosObtenidosEnEvaluacion.push(r.valoracionNumericaSeleccionada);
    }
  }

}
