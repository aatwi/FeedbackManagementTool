import {inject, TestBed} from '@angular/core/testing';

import {LoginService} from './login.service';
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";

describe('LoginService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [LoginService],
    imports: [HttpClientTestingModule]
  }));

  it('should return a valid user if authenticated ',
    inject([HttpTestingController, LoginService],
      (httpMock: HttpTestingController, service: LoginService) => {

        service.login('USER_ONE@FMT.COM', '0b14d501a594442a01c6859541bcb3e8164d183d32937b851835442f69d5c94e')
          .subscribe(data => {
            expect(data.name).toBe('USER ONE');
            expect(data.email).toBe('USER_ONE@FMT.COM');
          });

        const request = httpMock.expectOne('http://localhost:8080/api/v1/login/USER_ONE@FMT.COM/0b14d501a594442a01c6859541bcb3e8164d183d32937b851835442f69d5c94e');
        expect(request.request.method).toEqual('GET');

        request.flush({'name': 'USER ONE', 'email': 'USER_ONE@FMT.COM'});
      })
  );

  it('should return an empty User if not authenticated',
    inject([HttpTestingController, LoginService],
      (httpMock: HttpTestingController, service: LoginService) => {

        service.login('USER_ONE@FMT.COM', 'WrongPassword')
          .subscribe(data => {
            expect(data.name).toBeUndefined();
            expect(data.email).toBeUndefined();
          });

        const request = httpMock.expectOne('http://localhost:8080/api/v1/login/USER_ONE@FMT.COM/WrongPassword');
        expect(request.request.method).toEqual('GET');

        request.flush({});
      })
  );

});
