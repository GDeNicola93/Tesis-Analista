export class ComportamientoPlantilla {
    id?: number;
    descComportamiento : string;
    grado : string;
    valoracionNumerica : number;
    esAprobada : boolean;

    constructor(descComportamiento:string,grado:string,valoracionNumerica:number,esAprobada:boolean) {
        this.descComportamiento = descComportamiento;
        this.grado = grado;
        this.valoracionNumerica = valoracionNumerica;
        this.esAprobada = esAprobada;
    }
}
