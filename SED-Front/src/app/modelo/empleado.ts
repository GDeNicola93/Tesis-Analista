import { Estado } from "./estado";
import { PuestoTrabajo } from "./puesto-trabajo";
import { Usuario } from "./usuario";

export class Empleado {
    id?: number;
    legajo : string ="";
    nombre : string = "";
    apellido : string = "";
    dni : string = "";
    email : string = "";
    fechaDeNacimiento : any;
    estado : Estado = "";
    puestosTrabajo : PuestoTrabajo[] = [];
    usuario!: Usuario;

    constructor(legajo:string,nombre:string,apellido:string,dni:string,email:string,fechaDeNacimiento : any,estado:Estado,puestosTrabajo:PuestoTrabajo[]){
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.estado = estado;
        this.puestosTrabajo = puestosTrabajo;
    }

}
