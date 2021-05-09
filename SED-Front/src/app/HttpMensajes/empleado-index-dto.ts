export class EmpleadoIndexDto{
    id: number;
    legajo : string;
    nombreApellido : string;
    habilitado : boolean;
    lugaresTrabajo : String[] = [];
}