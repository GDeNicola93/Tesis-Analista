import { ResultadoVersusReporteDto } from "./ResultadoVersusReporteDto";

export class DetalleEvaluacionVersusReporteDto{
    id : number;
    nombreApellidoEvaluado : string;
    nombreApellidoEvaluador : string;
    fechaRealizacion : any;
    fueEvaluado : boolean;
    resultados : ResultadoVersusReporteDto[];
}