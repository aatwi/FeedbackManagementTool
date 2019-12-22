import {InputValidator} from "./input-validator";

describe('Should return an EMAIL_ERROR_MESSAGE when email is of wrong format', () => {
  const EMAIL_ERROR_MESSAGE = '*Please enter a valid email!';

  const parameters = [
    {description: "Should return error message on empty email input", input: ""},
    {description: "Should return error message on null email input", input: undefined},
    {description: "Should return error message on email input with just account name", input: "user"},
    {description: "Should return error message on email input missing the sign", input: "user.com"},
    {description: "Should return error message on email input missing the level domain", input: "user@fmt."},
    {description: "Should return error message on email input missing the full domain name", input: "user@"},
    {description: "Should return error message on email input missing the full root name", input: "user@.com"}
  ];

  parameters.forEach((parameter) => {
    it(parameter.description, () => {
        let actualMessage = InputValidator.validateUserCreationInput(parameter.input, null, null, null);
        expect(actualMessage).toBe(EMAIL_ERROR_MESSAGE);
      }
    )
  });
});

describe("Should return PASSWORD_ERROR_MESSAGE when the password is of wrong format", () => {
  const PASSWORD_ERROR_MESSAGE = '*Please enter a valid password!';
  const PASSWORD_DO_NOT_MATCH_MESSAGE = '*Passwords do not match!';

  const parameters = [
    {description: "Should return error message on empty password", passwordOne: "", passwordTwo: "", expected: PASSWORD_ERROR_MESSAGE},
    {description: "Should return error message on undefined password", passwordOne: undefined, passwordTwo: "", expected: PASSWORD_ERROR_MESSAGE},
    {description: "Should return error message when passwords don't match", passwordOne: "PassOne", passwordTwo: "PassTwo", expected: PASSWORD_DO_NOT_MATCH_MESSAGE}
  ];

  parameters.forEach((parameter) => {
    it(parameter.description, () => {
        let actualMessage = InputValidator.validateUserCreationInput("Name@mail.com", "Name", parameter.passwordOne, parameter.passwordTwo);
        expect(actualMessage).toBe(parameter.expected);
      }
    )
  });
});

describe('Should return NAME_ERROR_MESSAGE when the User Name is of wrong format', () => {
  const NAME_ERROR_MESSAGE = '*Please enter a valid name!';

  const parameters = [
    {description: "Should return error message on empty User Name", userName: "", expected: NAME_ERROR_MESSAGE},
    {description: "Should return error message on undefined password", userName: undefined, expected: NAME_ERROR_MESSAGE}
  ];

  parameters.forEach((parameter) => {
    it(parameter.description, () => {
        let actualMessage = InputValidator.validateUserCreationInput("Name@mail.com", parameter.userName, null, null);
        expect(actualMessage).toBe(parameter.expected);
      }
    )
  });
});

describe('Should return empty string when the input is correct', () => {
  it("Should return empty string when the input is correct", () => {
    let actualMessage = InputValidator.validateUserCreationInput("Name@mail.com", "Name", "Pass", "Pass");
    expect(actualMessage).toBe(undefined);
  });
});
