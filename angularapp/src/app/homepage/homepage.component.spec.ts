import { ComponentFixture, TestBed } from '@angular/core/testing';
// import { RouterTestingModule } from '@angular/router/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HomepageComponent } from './homepage.component';

describe('HomepageComponent', () => {
  let component: HomepageComponent;
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule], 
    providers: [HomepageComponent]
  }));
  beforeEach(() => {
    const fixture = TestBed.createComponent(HomepageComponent);
    component = fixture.componentInstance;
  });
  it('FE_HomePageTest', () => {
    expect(component).toBeTruthy();
  });
});