import {environment} from "../../environments/environment";

export class LoginUrlBuilder {
  private user: string;
  private password: string;
  private service = 'login/';

  private constructor() {

  }

  static logInUrl(): LoginUrlBuilder {
    return new LoginUrlBuilder()
  }

  withUser(user: string): LoginUrlBuilder {
    this.user = user;
    return this;
  }

  withPassword(password: string): LoginUrlBuilder {
    this.password = password;
    return this;
  }

  build(): string {
    return environment.restApiUrl + this.service + this.user + '/' + this.password;
  }
}
