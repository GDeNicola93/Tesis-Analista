import { DetallePlantilla } from "./detalle-plantilla";
import { PuestoTrabajo } from "./puesto-trabajo";

export class PlantillaEvaluacion {
    id?: number;
    descripcion : string;
    puestoTrabajo : PuestoTrabajo;
    competencia : DetallePlantilla[] = [];
    estaEnCurso !: boolean;
    fechaCreaccion !: any;

    constructor(descripcion:string,puestoTrabajo:PuestoTrabajo,competencia:DetallePlantilla[]){
        this.descripcion = descripcion;
        this.puestoTrabajo = puestoTrabajo;
        this.competencia = competencia;
    }
}
