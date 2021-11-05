import { Competencia } from "./competencia";
import { ComportamientoPlantilla } from "./comportamiento-plantilla";
import { Objetivo } from "./objetivo";

export class DetallePlantilla {
    id?: number;
    competencia : Competencia;
    esPreguntaObjetivo : boolean;
    obj !: Objetivo;
    gradoMinimoRequerido : string;
    comportamiento : ComportamientoPlantilla[] = [];

    constructor(competencia:Competencia,esPreguntaObjetivo : boolean,obj:any,gradoMinimoRequerido : string,comportamiento:ComportamientoPlantilla[]){
        this.competencia = competencia;
        this.esPreguntaObjetivo = esPreguntaObjetivo;
        this.obj = obj;
        this.gradoMinimoRequerido = gradoMinimoRequerido;
        this.comportamiento = comportamiento;
    }
}
