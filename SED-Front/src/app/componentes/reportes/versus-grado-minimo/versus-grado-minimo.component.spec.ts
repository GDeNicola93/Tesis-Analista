import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VersusGradoMinimoComponent } from './versus-grado-minimo.component';

describe('VersusGradoMinimoComponent', () => {
  let component: VersusGradoMinimoComponent;
  let fixture: ComponentFixture<VersusGradoMinimoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VersusGradoMinimoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VersusGradoMinimoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
