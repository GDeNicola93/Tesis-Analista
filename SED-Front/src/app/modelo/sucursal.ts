import { Area } from "./area";

export class Sucursal {
    id?: number;
    nombre : string = '';
    descripcion : string = '';
    areas : Area[] = [];
    
    constructor(nombre:string,descripcion:string,areas:Area[]){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.areas = areas;
    }
}
