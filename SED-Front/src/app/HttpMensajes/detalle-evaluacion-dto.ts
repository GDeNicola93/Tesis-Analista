import { Resultado } from "../modelo/resultado";

export class DetalleEvaluacionDto{
    id : number;
    evaluado : string;
    evaluador : string;
    fechaHoraRealizacion : any;
    resultados : Resultado[];
    calificacion : number;
    aprobado : boolean;
}