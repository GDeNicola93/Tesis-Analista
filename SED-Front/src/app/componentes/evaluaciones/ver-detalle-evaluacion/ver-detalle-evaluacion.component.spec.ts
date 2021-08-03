import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerDetalleEvaluacionComponent } from './ver-detalle-evaluacion.component';

describe('VerDetalleEvaluacionComponent', () => {
  let component: VerDetalleEvaluacionComponent;
  let fixture: ComponentFixture<VerDetalleEvaluacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerDetalleEvaluacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VerDetalleEvaluacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
