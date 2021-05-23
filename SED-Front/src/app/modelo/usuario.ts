import { Empleado } from "./empleado";
import { Rol } from "./rol";

export class Usuario {
    id?: number;
    nombreUsuario : string ="";
    password : string ="";
    habilitado : boolean = false;
    roles : Rol[] = [];
    empleado : Empleado;
    nombreFotoPerfil : string = "";

    constructor(nombreUsuario : string,password : string,roles : Rol[],empleado : Empleado){
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.habilitado = true;
        this.roles = roles;
        this.empleado = empleado;
        this.nombreFotoPerfil = null;
    }
    
}
