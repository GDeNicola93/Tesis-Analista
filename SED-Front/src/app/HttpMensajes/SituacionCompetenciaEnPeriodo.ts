import { Competencia } from "../modelo/competencia"

export interface SituacionCompetenciaEnPeriodoDTO {
    periodo: any
    competencia: Competencia;
}

export interface RespuestaSituacionCompetenciaDTO {
    puestosTrabajo: PuestosTrabajo[]
    competencia : string
    periodo : any
    totalEvaluados: number
    cantNoEvaluados: number
    cantSuperaronOalcanzaronMin: number
    porcentajeNoAlcanzaronMinimoRequerido: number
    porcentajeSuperaronOalcanzaronMin: number
    cantidadNoAlcanzaronMinimoRequerido: number
    porcentajeNoEvaluados: number
}

export interface PuestosTrabajo {
    nombrePuesto: string
    sucursal: string
    cantSuperaronOalcanzaronMin: number
    cantidadNoAlcanzaronMinimoRequerido: number
    cantNoEvaluados: number
    totalEvaluados: number
    porcentajeNoAlcanzaronMinimoRequerido: number
    porcentajeSuperaronOalcanzaronMin: number
    porcentajeNoEvaluados: number
}

