import {Rol} from "../modelo/rol";
export class EmpleadoVerDto{
    legajo : string;
    nombreApellido : string;
    dni : string;
    fechaDeNacimiento : string;
    nombreUsuario : string;
    nombreFotoPerfil : string;
    email : string;
    roles : Rol[] = [];
    habilitado : boolean;
    puestos : string[] = [];
}