import {inject, TestBed} from "@angular/core/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {CreateUserService} from "./create-user.service";

describe('CreateUserService', () => {
  let restApiUrl = 'http://localhost:4200/api/';
  beforeEach(() => TestBed.configureTestingModule({
    providers: [CreateUserService],
    imports: [HttpClientTestingModule]
  }));

  it('service should be created', inject([CreateUserService], (service: CreateUserService) => {
    expect(service).toBeTruthy();
  }));

});
