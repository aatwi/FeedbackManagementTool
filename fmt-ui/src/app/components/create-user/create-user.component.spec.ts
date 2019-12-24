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
import {InputValidator} from "../../helpers/input-validator";

describe('CreateUserComponent', () => {
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
    });
  }));

  it('Should call DataTransferService.setLoggedInUser on user creation', () => {
    userComponent.createUser();
    expect(dataSrv.setLoggedInUser).toHaveBeenCalled();
  });

  it('Should call InputValidator.validateUserCreationInput on data validation', () => {
    spyOn(InputValidator, 'validateUserCreationInput').and.returnValue("AnyErrorMessage");

    userComponent.createUserButtonClicked = true;
    let result: boolean = userComponent.inputIsInvalid();

    expect(InputValidator.validateUserCreationInput).toHaveBeenCalled();
    expect(result).toBe(true);
    expect(userComponent.errorMessage).toBe("AnyErrorMessage");
  });

  it('Should return false all the data are valid', () => {
    userComponent.userEmail = "TestUser@email.com";
    userComponent.userName = "Test User";
    userComponent.password = "TestUserPassword";
    userComponent.passwordReEntered = "TestUserPassword";
    userComponent.createUserButtonClicked = true;

    expect(userComponent.inputIsInvalid()).toBe(false);
  });

});
