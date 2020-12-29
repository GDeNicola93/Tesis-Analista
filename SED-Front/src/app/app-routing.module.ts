import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AreasComponent } from './componentes/areas/areas/areas.component';
import { NuevaComponent } from './componentes/areas/nueva/nueva.component';
import { HomeComponent } from './componentes/home/home.component';
import { LoginComponent } from './componentes/login/login.component';
import { IndexSucursalComponent } from './componentes/sucursales/index-sucursal/index-sucursal.component';
import { NuevaSucursalComponent } from './componentes/sucursales/nueva-sucursal/nueva-sucursal.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'home',component:HomeComponent},
  {path: 'areas',component:AreasComponent},
  {path: 'areas/nueva',component:NuevaComponent},
  {path: 'sucursales/nueva',component:NuevaSucursalComponent},
  {path: 'sucursales',component:IndexSucursalComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
