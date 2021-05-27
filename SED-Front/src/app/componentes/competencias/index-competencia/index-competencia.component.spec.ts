import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndexCompetenciaComponent } from './index-competencia.component';

describe('IndexCompetenciaComponent', () => {
  let component: IndexCompetenciaComponent;
  let fixture: ComponentFixture<IndexCompetenciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IndexCompetenciaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IndexCompetenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
