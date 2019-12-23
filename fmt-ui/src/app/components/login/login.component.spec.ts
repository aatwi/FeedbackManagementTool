import {async, TestBed} from '@angular/core/testing';

import {LoginComponent} from './login.component';
import {RouterTestingModule} from "@angular/router/testing";
import {FormsModule} from "@angular/forms";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {InputValidator} from "../../helpers/input-validator";

describe('LoginComponent', () => {
  let wrongUserNamePassword = '*Wrong Username or Password!';
  let loginComponent: LoginComponent;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        FormsModule,
        HttpClientTestingModule
      ],
      declarations: [LoginComponent]
    })
      .compileComponents().then(() => {
      loginComponent = new LoginComponent(null, null);
      loginComponent.userPassword = 'password';
      loginComponent.userEmail = 'user@fmt.com';
        loginComponent.loginButtonClicked = true;
      }
    );
  }));

  it('Should not raise and exception when email has the right format', () => {
    loginComponent.userEmail = 'user@fmt.com';
    expect(loginComponent.inputIsInvalid()).toEqual(false);
  });

  it('Should call InputValidator.validateLoginInput on data validation', () => {
    spyOn(InputValidator, 'validateLoginInput');

    loginComponent.loginButtonClicked = true;
    loginComponent.inputIsInvalid();

    expect(InputValidator.validateLoginInput).toHaveBeenCalled();
  });

  it('should raise an error if the user provided a wrong username or password', () => {
    loginComponent.failedPassword = true;
    assertWrongUserOrPassword();
  });

  function assertWrongUserOrPassword() {
    expect(loginComponent.inputIsInvalid()).toEqual(true);
    expect(loginComponent.errorMessage).toEqual(wrongUserNamePassword);
  }
});
