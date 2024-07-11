import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestimonalsComponent } from './testimonals.component';

describe('TestimonalsComponent', () => {
  let component: TestimonalsComponent;
  let fixture: ComponentFixture<TestimonalsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TestimonalsComponent]
    });
    fixture = TestBed.createComponent(TestimonalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
