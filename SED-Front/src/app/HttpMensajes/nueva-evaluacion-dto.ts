import { Empleado } from "../modelo/empleado";
import { EspecificacionDePuesto } from "../modelo/especificacion-puesto";
import { PlantillaEvaluacion } from "../modelo/plantilla-evaluacion";
import { Sucursal } from "../modelo/sucursal";

export class NuevaEvaluacionDto{
    fechaInicioEvaluacion : any;
    fechaFinEvaluacion : any;
    sucursal : Sucursal;
    especificacionPuesto : EspecificacionDePuesto;
    plantillaEvaluacion : PlantillaEvaluacion;
    empleadoEvaluador : Empleado;
}