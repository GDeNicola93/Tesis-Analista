import { Objetivo } from "./objetivo";
import { PuestoTrabajo } from "./puesto-trabajo";
import { Sucursal } from "./sucursal";

export class EspecificacionDePuesto{
    id : number;
    puesto : PuestoTrabajo;
    sucursal : Sucursal;
    objetivos : Objetivo[];
    descripcion : string;

    constructor(puesto : PuestoTrabajo,sucursal : Sucursal,objetivos : Objetivo[],descripcion : string){
        this.puesto = puesto;
        this.sucursal = sucursal;
        this.objetivos = objetivos;
        this.descripcion = descripcion;
    }
}