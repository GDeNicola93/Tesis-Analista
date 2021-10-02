import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './componentes/login/login.component';
import { HomeComponent } from './componentes/home/home.component';
import { MenuComponent } from './componentes/menu/menu.component';
import { AreasComponent } from './componentes/areas/areas/areas.component';
import { NuevaComponent } from './componentes/areas/nueva/nueva.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { UnAuthorizedInterceptor } from './servicios/no-authorized-interceptor.service';
import { IndexCompetenciaComponent } from './componentes/competencias/index-competencia/index-competencia.component';
import { NuevaCompetenciaComponent } from './componentes/competencias/nueva-competencia/nueva-competencia.component';
import { IndexEspecificacionPuestoComponent } from './componentes/especificacionPuesto/index-especificacion-puesto/index-especificacion-puesto.component';
import { NuevaEspecificacionPuestoComponent } from './componentes/especificacionPuesto/nueva-especificacion-puesto/nueva-especificacion-puesto.component';
import { VerEspecificacionPuestoComponent } from './componentes/especificacionPuesto/ver-especificacion-puesto/ver-especificacion-puesto.component';
import { IndexEvaluacionComponent } from './componentes/evaluaciones/index-evaluacion/index-evaluacion.component';
import { NuevaEvaluacionComponent } from './componentes/evaluaciones/nueva-evaluacion/nueva-evaluacion.component';
import { VerEvaluacionComponent } from './componentes/evaluaciones/ver-evaluacion/ver-evaluacion.component';
import { EvaluacionesAsignadasComponent } from './componentes/evaluaciones-evaluador/evaluaciones-asignadas/evaluaciones-asignadas.component';
import { IndexEvaluarComponent } from './componentes/evaluar/index-evaluar/index-evaluar.component';
import { VerDetalleEvaluacionComponent } from './componentes/evaluaciones/ver-detalle-evaluacion/ver-detalle-evaluacion.component';
import { MisEvaluacionesComponent } from './componentes/evaluaciones-empleado/mis-evaluaciones/mis-evaluaciones.component';
import { EvaluarComponent } from './componentes/evaluar/evaluar/evaluar.component';
import { ChartsModule } from 'ng2-charts';
import { IndexDetallesEvaluacionComponent } from './componentes/evaluaciones/index-detalles-evaluacion/index-detalles-evaluacion.component';


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
    UpdatePasswordComponent,
    IndexCompetenciaComponent,
    NuevaCompetenciaComponent,
    IndexEspecificacionPuestoComponent,
    NuevaEspecificacionPuestoComponent,
    VerEspecificacionPuestoComponent,
    IndexEvaluacionComponent,
    NuevaEvaluacionComponent,
    VerEvaluacionComponent,
    EvaluacionesAsignadasComponent,
    IndexEvaluarComponent,
    VerDetalleEvaluacionComponent,
    MisEvaluacionesComponent,
    EvaluarComponent,
    IndexDetallesEvaluacionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    AngularCropperjsModule,
    ReactiveFormsModule,
    ChartsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    },
    { provide: HTTP_INTERCEPTORS, 
      useClass: UnAuthorizedInterceptor, 
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
