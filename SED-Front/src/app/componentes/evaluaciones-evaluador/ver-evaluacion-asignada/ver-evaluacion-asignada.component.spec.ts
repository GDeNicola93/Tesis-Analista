import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerEvaluacionAsignadaComponent } from './ver-evaluacion-asignada.component';

describe('VerEvaluacionAsignadaComponent', () => {
  let component: VerEvaluacionAsignadaComponent;
  let fixture: ComponentFixture<VerEvaluacionAsignadaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerEvaluacionAsignadaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VerEvaluacionAsignadaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
