import { Estado } from "../modelo/estado";

export class EvaluacionEvaluadorIndexDto{
    id : number;
    fechaInicioEvaluacion : any;
    fechaFinEvaluacion : any;
    periodoInicio : any;
    periodoFin : any;
    puestoTrabajo : string;
    estado : Estado;
    estaParaEvaluar : boolean;
}