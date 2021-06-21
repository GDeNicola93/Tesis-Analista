import { DetallePlantilla } from "./detalle-plantilla";
import { EspecificacionDePuesto } from "./especificacion-puesto";
import { PuestoTrabajo } from "./puesto-trabajo";

export class PlantillaEvaluacion {
    id?: number;
    descripcion : string;
    puestoTrabajo : EspecificacionDePuesto;
    detallePlantilla : DetallePlantilla[] = [];
    estaEnCurso !: boolean;
    fechaCreaccion !: any;

    constructor(descripcion:string,puestoTrabajo:EspecificacionDePuesto,detallePlantilla:DetallePlantilla[]){
        this.descripcion = descripcion;
        this.puestoTrabajo = puestoTrabajo;
        this.detallePlantilla = detallePlantilla;
    }
}
