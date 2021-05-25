export class Competencia {
    id?: number;
    descripcion : string;
    nombre : string;


    constructor(descripcion:string,nombre:string){
        this.descripcion = descripcion;
        this.nombre = nombre;
    }
}
