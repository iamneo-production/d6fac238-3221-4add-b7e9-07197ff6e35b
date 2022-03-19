import { ComponentFixture, TestBed } from '@angular/core/testing';
// import { RouterTestingModule } from '@angular/router/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ViewComponent } from './view.component';

describe('ViewComponent', () => {
  let component: ViewComponent;
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule], 
    providers: [ViewComponent]
  }));
  beforeEach(() => {
    const fixture = TestBed.createComponent(ViewComponent);
    component = fixture.componentInstance;
  });
  it('FE_ViewTest', () => {
    expect(component).toBeTruthy();
  });
});