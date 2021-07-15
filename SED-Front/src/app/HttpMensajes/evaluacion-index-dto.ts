import { Estado } from "../modelo/estado";

export class EvaluacionIndexDto{
    id : number;
    fechaInicioEvaluacion : any;
    fechaFinEvaluacion : any;
    evaluador :  string;
    estado : Estado;
}