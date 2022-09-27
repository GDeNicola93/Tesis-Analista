export interface RespuestaSituacionCompetenciaDTO {
    puestosTrabajo: PuestoTrabajoSituacionCompetenciaDTO[]
    porcentajeNoEvaluados: number
    cantSuperaronOalcanzaronMin: number
    cantidadNoAlcanzaronMinimoRequerido: number
    porcentajeNoAlcanzaronMinimoRequerido: number
    porcentajeSuperaronOalcanzaronMin: number
    cantNoEvaluados: number
    totalEvaluados: number
}

export interface PuestoTrabajoSituacionCompetenciaDTO {
    nombrePuesto: string
    sucursal: string
    cantSuperaronOalcanzaronMin: number
    cantidadNoAlcanzaronMinimoRequerido: number
    cantNoEvaluados: number
    porcentajeNoEvaluados: number
    porcentajeNoAlcanzaronMinimoRequerido: number
    porcentajeSuperaronOalcanzaronMin: number
    totalEvaluados: number
}
