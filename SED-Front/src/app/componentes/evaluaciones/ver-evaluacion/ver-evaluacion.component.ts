import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChartOptions, ChartType } from 'chart.js';
import { Label } from 'ng2-charts';
import { EvaluacionVerDto } from 'src/app/HttpMensajes/evaluacion-ver-dto';
import { EvaluacionService } from 'src/app/servicios/evaluacion.service';
import { TokenService } from 'src/app/servicios/token.service';

@Component({
  selector: 'app-ver-evaluacion',
  templateUrl: './ver-evaluacion.component.html',
  styleUrls: ['./ver-evaluacion.component.css']
})
export class VerEvaluacionComponent implements OnInit {
  esAdministrador : boolean;
  cargando = true;
  evaluacion : EvaluacionVerDto;
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
  public pieChartLabels: Label[] = ['Evaluados','No Evaluados'];
  public pieChartData: number[];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartColors = [
    {
      backgroundColor: ['rgba(0,255,0,0.3)','rgba(255,0,0,0.3)'],
    },
  ];

  constructor(private rutaActiva: ActivatedRoute,private evaluacionServicio : EvaluacionService,private tokenServicio : TokenService) { }

  ngOnInit(): void {
    this.getEvaluacionById();
    this.getIsAdministrador();
  }

  getEvaluacionById() : void{
    this.evaluacionServicio.getEvaluacionById(this.rutaActiva.snapshot.params.id).subscribe(data =>{
      this.evaluacion = data;
      this.cargando = false;
      this.generarDeGrafico();
    });
  }

  generarDeGrafico() : void{
    this.pieChartData = [this.evaluacion.cantidadEmpleadosEvaluados,this.evaluacion.restantesAEvaluar];
  }

  getIsAdministrador() : void {
    this.esAdministrador=this.tokenServicio.isAdmin();
  }

}
