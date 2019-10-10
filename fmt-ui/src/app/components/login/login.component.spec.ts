import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {LoginComponent} from './login.component';
import {RouterTestingModule} from "@angular/router/testing";
import {FormsModule} from "@angular/forms";

describe('LoginComponent', () => {
  let emailErrorMessage = '*Please enter a valid email!';
  let passwordErrorMessage = '*Please enter your password!';
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
      loginComponent.userPassword = 'password';
      loginComponent.userEmail = 'user@fmt.com';
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
    assertInvalidEmail();
  });

  it('should notify the user when the email is undefined', () => {
    loginComponent.userEmail = undefined;
    assertInvalidEmail();
  });

  it('should notify the user when they enter an email without the @', () => {
    loginComponent.userEmail = 'user.com';
    assertInvalidEmail();
  });

  it('should notify the user when they enter an email without the @ and .domain ', () => {
    loginComponent.userEmail = 'user';
    assertInvalidEmail();
  });

  it('should notify the user when they enter an email without the domain ', () => {
    loginComponent.userEmail = 'user@fmt.';
    assertInvalidEmail();
  });

  it('should notify the user when they enter an email without the .domain ', () => {
    loginComponent.userEmail = 'user@fmt';
    assertInvalidEmail();
  });

  it('should not raise and exception when email has the right format', () => {
    loginComponent.userEmail = 'user@fmt.com';
    expect(loginComponent.invalidInput()).toEqual(false);
  });

  it('should raise an error if the password is undefined', () => {
    loginComponent.userPassword = undefined;
    assertInvalidPassword();
  });

  it('should raise an error if the password is left empty', () => {
    loginComponent.userPassword = '';
    assertInvalidPassword();
  });

  function assertInvalidPassword() {
    expect(loginComponent.invalidInput()).toEqual(true);
    expect(loginComponent.errorMessage).toEqual(passwordErrorMessage);
  }

  function assertInvalidEmail() {
    expect(loginComponent.invalidInput()).toEqual(true);
    expect(loginComponent.errorMessage).toEqual(emailErrorMessage);
  }

});
