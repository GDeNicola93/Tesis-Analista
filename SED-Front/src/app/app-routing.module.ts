import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AreasComponent } from './componentes/areas/areas/areas.component';
import { NuevaComponent } from './componentes/areas/nueva/nueva.component';
import { IndexEmpleadosComponent } from './componentes/empleados/index-empleados/index-empleados.component';
import { NuevoEmpleadoComponent } from './componentes/empleados/nuevo-empleado/nuevo-empleado.component';
import { HomeComponent } from './componentes/home/home.component';
import { LoginComponent } from './componentes/login/login.component';
import { IndexPuestosTrabajoComponent } from './componentes/puestosTrabajo/index-puestos-trabajo/index-puestos-trabajo.component';
import { NuevoPuestosTrabajoComponent } from './componentes/puestosTrabajo/nuevo-puestos-trabajo/nuevo-puestos-trabajo.component';
import { IndexSucursalComponent } from './componentes/sucursales/index-sucursal/index-sucursal.component';
import { NuevaSucursalComponent } from './componentes/sucursales/nueva-sucursal/nueva-sucursal.component';
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

  //Empleados
  {path: 'empleados',component:IndexEmpleadosComponent,canActivate:[GeneralGuard],data: { expectedRol: ["Administrador"]}},
  {path: 'empleados/nuevo',component:NuevoEmpleadoComponent,canActivate:[GeneralGuard],data: { expectedRol: ["Administrador"]}},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
