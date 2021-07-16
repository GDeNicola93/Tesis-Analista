import { Estado } from "../modelo/estado";

export class EvaluacionVerDto{
    id : number;
    fechaInicioEvaluacion : any;
    fechaFinEvaluacion : any;
    fechaHoraCreacion : any;
    estado : Estado;
    idEvaluador : number;
    nombreEvaluador : string;
    puntajeMinAprobacion : number;
    cantidadEmpleadosAEvaluar : number;
    cantidadEmpleadosEvaluados : number;
    restantesAEvaluar : number;
    especificacionPuesto : string;
    sucursal : string;
    empleadosAEvaluar : string[];
}