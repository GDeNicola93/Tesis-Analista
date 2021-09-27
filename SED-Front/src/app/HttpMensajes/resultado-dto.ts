import { ComportamientoPlantilla } from "../modelo/comportamiento-plantilla";
import { DetallePlantilla } from "../modelo/detalle-plantilla";

export class ResultadoDto{
    idDetalleEvaluacion : number;
    detallePlantilla : DetallePlantilla;
    comportamientoPlantillaSeleccionado : ComportamientoPlantilla;

    constructor(idDetalleEvaluacion : number,detallePlantilla : DetallePlantilla,comportamientoPlantillaSeleccionado : ComportamientoPlantilla){
        this.idDetalleEvaluacion = idDetalleEvaluacion;
        this.detallePlantilla = detallePlantilla;
        this.comportamientoPlantillaSeleccionado = comportamientoPlantillaSeleccionado;
    }
}