import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndexEvaluarComponent } from './index-evaluar.component';

describe('IndexEvaluarComponent', () => {
  let component: IndexEvaluarComponent;
  let fixture: ComponentFixture<IndexEvaluarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IndexEvaluarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IndexEvaluarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
