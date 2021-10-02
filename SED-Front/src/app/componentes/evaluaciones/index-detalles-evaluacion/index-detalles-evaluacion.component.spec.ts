import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndexDetallesEvaluacionComponent } from './index-detalles-evaluacion.component';

describe('IndexDetallesEvaluacionComponent', () => {
  let component: IndexDetallesEvaluacionComponent;
  let fixture: ComponentFixture<IndexDetallesEvaluacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IndexDetallesEvaluacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IndexDetallesEvaluacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
