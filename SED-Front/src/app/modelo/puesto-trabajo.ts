import { Area } from "./area";
import { Objetivo } from "./objetivo";
import { Sucursal } from "./sucursal";

export class PuestoTrabajo {
    id?: number;
    nombrePuesto : string = '';
    area : Area;

    constructor(nombrePuesto : string,area:Area){
        this.nombrePuesto = nombrePuesto;
        this.area = area;
    }
}