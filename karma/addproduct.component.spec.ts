import { ComponentFixture, TestBed } from '@angular/core/testing';
// import { RouterTestingModule } from '@angular/router/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AddproductComponent } from './addproduct.component';

describe('AddproductComponent', () => {
  let component: AddproductComponent;
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule], 
    providers: [AddproductComponent]
  }));
  beforeEach(() => {
    const fixture = TestBed.createComponent(AddproductComponent);
    component = fixture.componentInstance;
  });
  it('FE_AddProductTest', () => {
    expect(component).toBeTruthy();
  });
});