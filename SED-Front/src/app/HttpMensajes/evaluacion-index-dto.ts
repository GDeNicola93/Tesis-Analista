import { Estado } from "../modelo/estado";

export class EvaluacionIndexDto{
    id : number;
    fechaInicioEvaluacion : any;
    fechaFinEvaluacion : any;
    periodoInicio : any;
    periodoFin : any;
    puestoTrabajo : string;
    nombreCompletoEvaluador :  string;
    estado : Estado;
    esCancelable : boolean;
    esEditable : boolean;
}