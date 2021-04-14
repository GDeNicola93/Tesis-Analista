export class ComportamientoPlantilla {
    id?: number;
    descComportamiento : string;
    grado : string;
    valoracionNumerica : number;

    constructor(descComportamiento:string,grado:string,valoracionNumerica:number) {
        this.descComportamiento = descComportamiento;
        this.grado = grado;
        this.valoracionNumerica = valoracionNumerica;
    }
}
