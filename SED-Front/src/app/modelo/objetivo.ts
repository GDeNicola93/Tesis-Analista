export class Objetivo {
    id?: number;
    descripcion : string = '';
    enCurso : boolean = true;

    constructor(descripcion : string){
        this.descripcion = descripcion;
        this.enCurso = true;
    }
}