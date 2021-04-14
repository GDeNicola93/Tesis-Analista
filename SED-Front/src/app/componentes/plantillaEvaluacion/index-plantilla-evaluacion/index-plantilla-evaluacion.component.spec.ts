import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndexPlantillaEvaluacionComponent } from './index-plantilla-evaluacion.component';

describe('IndexPlantillaEvaluacionComponent', () => {
  let component: IndexPlantillaEvaluacionComponent;
  let fixture: ComponentFixture<IndexPlantillaEvaluacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IndexPlantillaEvaluacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IndexPlantillaEvaluacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
