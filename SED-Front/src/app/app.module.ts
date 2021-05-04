import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './componentes/login/login.component';
import { HomeComponent } from './componentes/home/home.component';
import { MenuComponent } from './componentes/menu/menu.component';
import { AreasComponent } from './componentes/areas/areas/areas.component';
import { NuevaComponent } from './componentes/areas/nueva/nueva.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NuevaSucursalComponent } from './componentes/sucursales/nueva-sucursal/nueva-sucursal.component';
import { IndexSucursalComponent } from './componentes/sucursales/index-sucursal/index-sucursal.component';
import { AuthInterceptorService } from './servicios/auth-interceptor.service';
import { NuevoPuestosTrabajoComponent } from './componentes/puestosTrabajo/nuevo-puestos-trabajo/nuevo-puestos-trabajo.component';
import { IndexPuestosTrabajoComponent } from './componentes/puestosTrabajo/index-puestos-trabajo/index-puestos-trabajo.component';
import { IndexEmpleadosComponent } from './componentes/empleados/index-empleados/index-empleados.component';
import { NuevoEmpleadoComponent } from './componentes/empleados/nuevo-empleado/nuevo-empleado.component';
import { PerfilUsuarioComponent } from './componentes/usuario/perfil-usuario/perfil-usuario.component';
import { NuevaPlantillaEvaluacionComponent } from './componentes/plantillaEvaluacion/nueva-plantilla-evaluacion/nueva-plantilla-evaluacion.component';
import { IndexPlantillaEvaluacionComponent } from './componentes/plantillaEvaluacion/index-plantilla-evaluacion/index-plantilla-evaluacion.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { VerEmpleadoComponent } from './componentes/empleados/ver-empleado/ver-empleado.component';
import { AngularCropperjsModule } from 'angular-cropperjs';
import { SubirFotoPerfilComponent } from './componentes/usuario/subir-foto-perfil/subir-foto-perfil.component';
import { UpdatePasswordComponent } from './componentes/usuario/update-password/update-password.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    MenuComponent,
    AreasComponent,
    NuevaComponent,
    NuevaSucursalComponent,
    IndexSucursalComponent,
    IndexPuestosTrabajoComponent,
    NuevoPuestosTrabajoComponent,
    IndexEmpleadosComponent,
    NuevoEmpleadoComponent,
    PerfilUsuarioComponent,
    NuevaPlantillaEvaluacionComponent,
    IndexPlantillaEvaluacionComponent,
    VerEmpleadoComponent,
    SubirFotoPerfilComponent,
    UpdatePasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    AngularCropperjsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
