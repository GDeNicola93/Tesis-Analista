import { Area } from "./area";
import { Objetivo } from "./objetivo";
import { Sucursal } from "./sucursal";

export class PuestoTrabajo {
    id?: number;
    nombrePuesto : string = '';
    sucursal : Sucursal;
    area : Area;
    objetivosPuesto : Objetivo[] = [];

    constructor(nombrePuesto : string,sucursal:Sucursal,area:Area,objetivos:Objetivo[]){
        this.nombrePuesto = nombrePuesto;
        this.sucursal = sucursal;
        this.area = area;
        this.objetivosPuesto = objetivos;
    }
}