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
    NuevoEmpleadoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
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
