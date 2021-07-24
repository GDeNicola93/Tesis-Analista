import { Estado } from "../modelo/estado";

export class EvaluacionEvaluadorIndexDto{
    id : number;
    fechaInicioEvaluacion : any;
    fechaFinEvaluacion : any;
    fechaHoraCreacion : any;
    estado : Estado;
    estaParaEvaluar : boolean;
}