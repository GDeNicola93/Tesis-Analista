export class Competencia {
    id?: number;
    descripcion : string;
    nombre : string;
    porcentajeMinAprobacionDeComp : number;


    constructor(descripcion:string,nombre:string,porcentajeMinAprobacionDeComp:number){
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.porcentajeMinAprobacionDeComp = porcentajeMinAprobacionDeComp;
    }
}
