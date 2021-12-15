import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompararResultadosPorCompetenciaComponent } from './comparar-resultados-por-competencia.component';

describe('CompararResultadosPorCompetenciaComponent', () => {
  let component: CompararResultadosPorCompetenciaComponent;
  let fixture: ComponentFixture<CompararResultadosPorCompetenciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompararResultadosPorCompetenciaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompararResultadosPorCompetenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
