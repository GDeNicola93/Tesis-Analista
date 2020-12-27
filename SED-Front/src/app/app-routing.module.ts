import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AreasComponent } from './componentes/areas/areas/areas.component';
import { NuevaComponent } from './componentes/areas/nueva/nueva.component';
import { HomeComponent } from './componentes/home/home.component';
import { LoginComponent } from './componentes/login/login.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'home',component:HomeComponent},
  {path: 'areas',component:AreasComponent},
  {path: 'areas/nueva',component:NuevaComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
