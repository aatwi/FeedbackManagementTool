import {async, TestBed} from '@angular/core/testing';

import {CreateUserComponent} from './create-user.component';
import {FormsModule} from "@angular/forms";

describe('CreateUserComponent', () => {
  let emailErrorMessage = '*Please enter a valid email!';
  let nameErrorMessage = '*Please enter a valid name!';
  let passwordErrorMessage = '*Please enter a valid password!';
  let passwordDontMatchErrorMessage = '*Passwords do not match!';
  let userComponent: CreateUserComponent;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        FormsModule
      ],
      declarations: [CreateUserComponent]
    })
      .compileComponents().then(() => {
      userComponent = new CreateUserComponent();
      userComponent.userEmail = "TestUser@email.com";
      userComponent.userName = "Test User";
      userComponent.password = "TestUserPassword";
      userComponent.passwordReEntered = "TestUserPassword";
      userComponent.createUserButtonClicked = true;
    });
  }));

  it('should notify the user if email is empty', () => {
    userComponent.userEmail = "";
    expect(userComponent.invalidInput()).toEqual(true);
    expect(userComponent.errorMessage).toEqual(emailErrorMessage);
  });

  it('should notify the user if email was in a wrong format', () => {
    userComponent.userEmail = "user@fmt.";
    expect(userComponent.invalidInput()).toEqual(true);
    expect(userComponent.errorMessage).toEqual(emailErrorMessage);
  });

  it('should notify the user if name is empty', () => {
    userComponent.userName = "";
    expect(userComponent.invalidInput()).toEqual(true);
    expect(userComponent.errorMessage).toEqual(nameErrorMessage);
  });

  it('should notify the user if password is empty', () => {
    userComponent.password = "";
    expect(userComponent.invalidInput()).toEqual(true);
    expect(userComponent.errorMessage).toEqual(passwordErrorMessage);
  });

  it('should notify the user if passwords do not match', () => {
    userComponent.password = "abc";
    userComponent.passwordReEntered = "abc2";
    expect(userComponent.invalidInput()).toEqual(true);
    expect(userComponent.errorMessage).toEqual(passwordDontMatchErrorMessage);
  });
});
