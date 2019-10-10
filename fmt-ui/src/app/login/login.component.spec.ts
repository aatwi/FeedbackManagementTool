import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {LoginComponent} from './login.component';
import {RouterTestingModule} from "@angular/router/testing";
import {FormsModule} from "@angular/forms";

describe('LoginComponent', () => {
  let component: LoginComponent;
  let loginComponent: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        FormsModule
      ],
      declarations: [LoginComponent]
    })
      .compileComponents().then(() => {
        loginComponent = new LoginComponent();
        loginComponent.loginButtonClicked = true;
      }
    );
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should notify the user when then email empty', () => {
    loginComponent.userEmail = '';
    expect(loginComponent.invalidInput()).toEqual(true);
  });

  it('should notify the user when the email is undefined', () => {
    loginComponent.userEmail = undefined;
    expect(loginComponent.invalidInput()).toEqual(true);
  });

  it('should notify the user when they enter an email without the @', () => {
    loginComponent.userEmail = 'user.com';
    expect(loginComponent.invalidInput()).toEqual(true);
  });

  it('should notify the user when they enter an email without the @ and .domain ', () => {
    loginComponent.userEmail = 'user';
    expect(loginComponent.invalidInput()).toEqual(true);
  });

  it('should notify the user when they enter an email without the domain ', () => {
    loginComponent.userEmail = 'user@fmt.';
    expect(loginComponent.invalidInput()).toEqual(true);
  });

  it('should notify the user when they enter an email without the .domain ', () => {
    loginComponent.userEmail = 'user@fmt';
    expect(loginComponent.invalidInput()).toEqual(true);
  });

  it('should not raise and exception when email has the right format', () => {
    loginComponent.userEmail = 'user@fmt.com';
    expect(loginComponent.invalidInput()).toEqual(false);
  });

});
