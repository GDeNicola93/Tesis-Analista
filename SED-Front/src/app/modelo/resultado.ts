import { ComportamientoPlantilla } from "./comportamiento-plantilla";
import { DetallePlantilla } from "./detalle-plantilla";

export class Resultado{
    id : number;
    detallePlantilla : DetallePlantilla;
    comportamientoPlantillaSeleccionado : ComportamientoPlantilla;
    planAccion : string;
    resultadoObtenidoCompetencia : number;
}