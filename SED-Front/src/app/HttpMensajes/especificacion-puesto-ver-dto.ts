import { Objetivo } from "../modelo/objetivo";

export class EspecificacionDePuestoVerDto{
    id : number;
    nombrePuesto : string;
    descripcion : string;
    sucursalNombre : string
    areaNombre : string;
    objetivosActivos : Objetivo[];
}