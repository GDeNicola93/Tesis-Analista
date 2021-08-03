import { Resultado } from "../modelo/resultado";

export class DetalleEvaluacionDto{
    id : number;
    evaluado : string;
    fechaRealizacion : any;
    resultados : Resultado[];
    calificacion : number;
    aprobado : boolean;
}