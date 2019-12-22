import {async, TestBed} from '@angular/core/testing';

import {CreateUserComponent} from './create-user.component';
import {FormsModule} from "@angular/forms";
import {DataTransferService} from "../../services/data-transfer.service";
import {CreateUserService} from "../../services/create-user.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {AppModule} from "../../app.module";
import {RouterTestingModule} from "@angular/router/testing";
import {User} from "../../domain/user";
import {Observable, of} from "rxjs";

describe('CreateUserComponent', () => {
  let emailErrorMessage = '*Please enter a valid email!';
  let nameErrorMessage = '*Please enter a valid name!';
  let passwordErrorMessage = '*Please enter a valid password!';
  let passwordDontMatchErrorMessage = '*Passwords do not match!';
  let userComponent: CreateUserComponent;
  let dataSrv: DataTransferService;

  beforeEach(async(() => {
    let userObservable: Observable<User> = new Observable(() => {
      new User("Test User", "TestUser@email.com", "TestUserPassword")
    });
    const createUserSrv = jasmine.createSpyObj('CreateUserService', ['createUser']);
    createUserSrv.createUser.and.returnValue(of(userObservable));

    TestBed.configureTestingModule({
      imports: [
        AppModule,
        HttpClientTestingModule,
        RouterTestingModule,
        FormsModule
      ],
      providers: [
        {provide: CreateUserService, useValue: createUserSrv},
        DataTransferService],
      declarations: []
    })
      .compileComponents().then(() => {
      userComponent = TestBed.createComponent(CreateUserComponent).componentInstance;

      dataSrv = TestBed.get(DataTransferService);
      spyOn(dataSrv, 'setLoggedInUser');

      userComponent.dataService = dataSrv;
      userComponent.createUserService = createUserSrv;

      userComponent.userEmail = "TestUser@email.com";
      userComponent.userName = "Test User";
      userComponent.password = "TestUserPassword";
      userComponent.passwordReEntered = "TestUserPassword";
      userComponent.createUserButtonClicked = true;
    });
  }));

  it('should the value of the LoggedInUser in dataService when a new user is created', () => {
    userComponent.createUser();
    expect(dataSrv.setLoggedInUser).toHaveBeenCalled();
  });

  it('should notify the user if email is empty', () => {
    userComponent.userEmail = "";
    expect(userComponent.inputIsInvalid()).toEqual(true);
    expect(userComponent.errorMessage).toEqual(emailErrorMessage);
  });

  it('should notify the user if email was in a wrong format', () => {
    userComponent.userEmail = "user@fmt.";
    expect(userComponent.inputIsInvalid()).toEqual(true);
    expect(userComponent.errorMessage).toEqual(emailErrorMessage);
  });

  it('should notify the user if name is empty', () => {
    userComponent.userName = "";
    expect(userComponent.inputIsInvalid()).toEqual(true);
    expect(userComponent.errorMessage).toEqual(nameErrorMessage);
  });

  it('should notify the user if password is empty', () => {
    userComponent.password = "";
    expect(userComponent.inputIsInvalid()).toEqual(true);
    expect(userComponent.errorMessage).toEqual(passwordErrorMessage);
  });

  it('should notify the user if passwords do not match', () => {
    userComponent.password = "abc";
    userComponent.passwordReEntered = "abc2";
    expect(userComponent.inputIsInvalid()).toEqual(true);
    expect(userComponent.errorMessage).toEqual(passwordDontMatchErrorMessage);
  });
});
