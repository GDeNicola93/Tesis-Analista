export class EmpleadoIndexDto{
    idEmpleado: number;
    legajo : string;
    nombreApellido : string;
    habilitado : boolean;
    sucursalesTrabajo : String[] = [];
}