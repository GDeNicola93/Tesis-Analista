import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AreasComponent } from './componentes/areas/areas/areas.component';
import { NuevaComponent } from './componentes/areas/nueva/nueva.component';
import { IndexCompetenciaComponent } from './componentes/competencias/index-competencia/index-competencia.component';
import { NuevaCompetenciaComponent } from './componentes/competencias/nueva-competencia/nueva-competencia.component';
import { IndexEmpleadosComponent } from './componentes/empleados/index-empleados/index-empleados.component';
import { NuevoEmpleadoComponent } from './componentes/empleados/nuevo-empleado/nuevo-empleado.component';
import { VerEmpleadoComponent } from './componentes/empleados/ver-empleado/ver-empleado.component';
import { IndexEspecificacionPuestoComponent } from './componentes/especificacionPuesto/index-especificacion-puesto/index-especificacion-puesto.component';
import { NuevaEspecificacionPuestoComponent } from './componentes/especificacionPuesto/nueva-especificacion-puesto/nueva-especificacion-puesto.component';
import { VerEspecificacionPuestoComponent } from './componentes/especificacionPuesto/ver-especificacion-puesto/ver-especificacion-puesto.component';
import { HomeComponent } from './componentes/home/home.component';
import { LoginComponent } from './componentes/login/login.component';
import { IndexPlantillaEvaluacionComponent } from './componentes/plantillaEvaluacion/index-plantilla-evaluacion/index-plantilla-evaluacion.component';
import { NuevaPlantillaEvaluacionComponent } from './componentes/plantillaEvaluacion/nueva-plantilla-evaluacion/nueva-plantilla-evaluacion.component';
import { IndexPuestosTrabajoComponent } from './componentes/puestosTrabajo/index-puestos-trabajo/index-puestos-trabajo.component';
import { NuevoPuestosTrabajoComponent } from './componentes/puestosTrabajo/nuevo-puestos-trabajo/nuevo-puestos-trabajo.component';
import { IndexSucursalComponent } from './componentes/sucursales/index-sucursal/index-sucursal.component';
import { NuevaSucursalComponent } from './componentes/sucursales/nueva-sucursal/nueva-sucursal.component';
import { PerfilUsuarioComponent } from './componentes/usuario/perfil-usuario/perfil-usuario.component';
import { SubirFotoPerfilComponent } from './componentes/usuario/subir-foto-perfil/subir-foto-perfil.component';
import { UpdatePasswordComponent } from './componentes/usuario/update-password/update-password.component';
import { GeneralGuard } from './guards/general.guard';
import { LoginGuard } from './guards/login.guard';

const routes: Routes = [
  {path: '', component: LoginComponent, canActivate: [LoginGuard]},
  {path: 'home',component:HomeComponent,canActivate:[GeneralGuard],data: { expectedRol: ['Administrador','Evaluador','Empleado']}},
  
  // Areas
  {path: 'areas',component:AreasComponent,canActivate:[GeneralGuard],data: { expectedRol: ["Administrador"]}},
  {path: 'areas/nueva',component:NuevaComponent,canActivate:[GeneralGuard],data: { expectedRol: ["Administrador"]}},
  
  // Sucursales
  {path: 'sucursales/nueva',component:NuevaSucursalComponent,canActivate:[GeneralGuard],data: { expectedRol: ["Administrador"]}},
  {path: 'sucursales',component:IndexSucursalComponent,canActivate:[GeneralGuard],data: { expectedRol: ["Administrador"]}},
  
  //Puestos de Trabajo
  {path: 'puestos-trabajo',component:IndexPuestosTrabajoComponent,canActivate:[GeneralGuard],data: { expectedRol: ["Administrador"]}},
  {path: 'puestos-trabajo/nuevo',component:NuevoPuestosTrabajoComponent,canActivate:[GeneralGuard],data: { expectedRol: ["Administrador"]}},

  //Especificacion de Puestos de Trabajo
  {path: 'especificacion-puestos-trabajo',component:IndexEspecificacionPuestoComponent,canActivate:[GeneralGuard],data: { expectedRol: ["Administrador"]}},
  {path: 'especificacion-puestos-trabajo/nueva',component:NuevaEspecificacionPuestoComponent,canActivate:[GeneralGuard],data: { expectedRol: ["Administrador"]}},
  {path: 'especificacion-puestos-trabajo/ver/:id',component:VerEspecificacionPuestoComponent,canActivate:[GeneralGuard],data: { expectedRol: ["Administrador"]}},

  //Empleados
  {path: 'empleados',component:IndexEmpleadosComponent,canActivate:[GeneralGuard],data: { expectedRol: ["Administrador"]}},
  {path: 'empleados/nuevo',component:NuevoEmpleadoComponent,canActivate:[GeneralGuard],data: { expectedRol: ["Administrador"]}},
  {path: 'empleados/ver/:id',component:VerEmpleadoComponent,canActivate:[GeneralGuard],data: { expectedRol: ["Administrador"]}},

  //Usuario
  {path: 'usuario/mi-perfil',component:PerfilUsuarioComponent,canActivate:[GeneralGuard],data: { expectedRol: ['Administrador','Evaluador','Empleado']}},
  {path: 'usuario/subir-foto-perfil',component:SubirFotoPerfilComponent,canActivate:[GeneralGuard],data: { expectedRol: ['Administrador','Evaluador','Empleado']}},
  {path: 'usuario/actualizar-contrasena',component:UpdatePasswordComponent,canActivate:[GeneralGuard],data: { expectedRol: ['Administrador','Evaluador','Empleado']}},

  //Plantillas de Evaluación
  {path: 'plantilla-evaluacion',component:IndexPlantillaEvaluacionComponent,canActivate:[GeneralGuard],data: { expectedRol: ['Administrador']}},
  {path: 'plantilla-evaluacion/nueva',component:NuevaPlantillaEvaluacionComponent,canActivate:[GeneralGuard],data: { expectedRol: ['Administrador']}},

  //Competencias
  {path: 'competencia',component:IndexCompetenciaComponent,canActivate:[GeneralGuard],data: { expectedRol: ['Administrador']}},
  {path: 'competencia/nueva',component:NuevaCompetenciaComponent,canActivate:[GeneralGuard],data: { expectedRol: ['Administrador']}},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
