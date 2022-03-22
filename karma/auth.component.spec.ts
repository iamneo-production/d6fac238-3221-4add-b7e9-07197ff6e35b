import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthComponent } from './auth.component';

describe('AuthComponent', () => {
  let component: AuthComponent;
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule], 
    providers: [AuthComponent]
  }));
  beforeEach(() => {
    const fixture = TestBed.createComponent(AuthComponent);
    component = fixture.componentInstance;
  });
  it('FE_AuthTest', () => {
    expect(component).toBeTruthy();
  });
});