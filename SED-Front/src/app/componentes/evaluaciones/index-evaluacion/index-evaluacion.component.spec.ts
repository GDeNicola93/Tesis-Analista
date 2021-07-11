import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndexEvaluacionComponent } from './index-evaluacion.component';

describe('IndexEvaluacionComponent', () => {
  let component: IndexEvaluacionComponent;
  let fixture: ComponentFixture<IndexEvaluacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IndexEvaluacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IndexEvaluacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
