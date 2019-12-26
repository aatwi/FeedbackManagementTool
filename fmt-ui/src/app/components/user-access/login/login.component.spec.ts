import {async, TestBed} from '@angular/core/testing';

import {LoginComponent} from './login.component';
import {RouterTestingModule} from "@angular/router/testing";
import {FormsModule} from "@angular/forms";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {InputValidator} from "../../../helpers/input-validator";

describe('LoginComponent', () => {
  const WRONG_EMAIL_OR_PASSWORD_MESSAGE = '*Wrong Username or Password!';
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
        loginComponent.loginButtonClicked = true;
      }
    );
  }));

  it('Should call InputValidator.validateLoginInput on data validation', () => {
    spyOn(InputValidator, 'validateLoginInput').and.returnValue("AnyErrorMessage");

    loginComponent.loginButtonClicked = true;
    let result: boolean = loginComponent.inputIsInvalid();

    expect(InputValidator.validateLoginInput).toHaveBeenCalled();
    expect(result).toBe(true);
    expect(loginComponent.errorMessage).toBe("AnyErrorMessage")
  });

  it("Should return the message '" + WRONG_EMAIL_OR_PASSWORD_MESSAGE + "' if the email provided does not exist or password is wrong.", () => {
    loginComponent.failedPassword = true;
    expect(loginComponent.inputIsInvalid()).toEqual(true);
    expect(loginComponent.errorMessage).toEqual(WRONG_EMAIL_OR_PASSWORD_MESSAGE);
  });

});
