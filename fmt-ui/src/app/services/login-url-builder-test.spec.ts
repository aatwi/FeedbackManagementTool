import {LoginUrlBuilder} from "./login-url-builder";

it('should build a valid Url for Login', () => {
  const expected = 'http://localhost:4200/api/login/userone/password';

  const actual = LoginUrlBuilder.logInUrl().withUser('userone').withPassword('password').build();
  expect(actual).toEqual(expected);
});
