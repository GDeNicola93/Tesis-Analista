import { Estado } from "../modelo/estado";

export class EvaluacionIndexDto{
    id : number;
    fechaInicioEvaluacion : any;
    fechaFinEvaluacion : any;
    nombreCompletoEvaluador :  string;
    estado : Estado;
    esCancelable : boolean;
}