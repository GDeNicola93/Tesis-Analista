import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevaPlantillaEvaluacionComponent } from './nueva-plantilla-evaluacion.component';

describe('NuevaPlantillaEvaluacionComponent', () => {
  let component: NuevaPlantillaEvaluacionComponent;
  let fixture: ComponentFixture<NuevaPlantillaEvaluacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NuevaPlantillaEvaluacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NuevaPlantillaEvaluacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
