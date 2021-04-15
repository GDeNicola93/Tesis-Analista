import { DetallePlantilla } from "./detalle-plantilla";
import { PuestoTrabajo } from "./puesto-trabajo";

export class PlantillaEvaluacion {
    id?: number;
    descripcion : string;
    puestoTrabajo : PuestoTrabajo;
    detallePlantilla : DetallePlantilla[] = [];
    estaEnCurso !: boolean;
    fechaCreaccion !: any;

    constructor(descripcion:string,puestoTrabajo:PuestoTrabajo,detallePlantilla:DetallePlantilla[]){
        this.descripcion = descripcion;
        this.puestoTrabajo = puestoTrabajo;
        this.detallePlantilla = detallePlantilla;
    }
}
