import { ResultadoVersusReporteDto } from "./ResultadoVersusReporteDto";

export class DetalleEvaluacionVersusReporteDto{
    id : number;
    nombreApellidoEvaluado : string;
    nombreApellidoEvaluador : string;
    fechaHoraRealizacion : any;
    periodoInicio : any;
    periodoFin : any;
    fueEvaluado : boolean;
    resultados : ResultadoVersusReporteDto[];
}