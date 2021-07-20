import { EspecificacionDePuesto } from "../modelo/especificacion-puesto";
import { Rol } from "../modelo/rol";

export class NuevoEmpleadoDto{
    //Atributos del empleado
    legajo : string;
    nombre : string;
    apellido : string;
    fechaDeNacimiento : any;
    dni : string;
    email : string;
    puestosTrabajo : EspecificacionDePuesto[];

    //Atributos del usuario
    nombreUsuario : string;
    password : string;
    repeatPassword : string;
    roles : Rol[];

}