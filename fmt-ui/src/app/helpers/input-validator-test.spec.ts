import {InputValidator} from "./input-validator";

describe("ValidateUserCreationInput Tests", () => {

  describe("Should return an EMAIL_ERROR_MESSAGE when email is of wrong format", () => {
    const EMAIL_ERROR_MESSAGE = "*Please enter a valid email!";
    const parameters = [
      {description: "Should return '" + EMAIL_ERROR_MESSAGE + "' if the Email provided is empty.", input: ""},
      {description: "Should return '" + EMAIL_ERROR_MESSAGE + "' if the Email provided is undefined.", input: undefined},
      {description: "Should return '" + EMAIL_ERROR_MESSAGE + "' if the Email provided is just the account name.", input: "user"},
      {description: "Should return '" + EMAIL_ERROR_MESSAGE + "' if the Email provided is missing the sign.", input: "user.com"},
      {description: "Should return '" + EMAIL_ERROR_MESSAGE + "' if the Email provided is missing the level domain.", input: "user@fmt."},
      {description: "Should return '" + EMAIL_ERROR_MESSAGE + "' if the Email provided missing the full domain name.", input: "user@"},
      {description: "Should return '" + EMAIL_ERROR_MESSAGE + "' if the Email provided missing the full root name.", input: "user@.com"}
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
      {description: "Should return '" + PASSWORD_ERROR_MESSAGE + "' if the password is empty.", passwordOne: "", passwordTwo: "", expected: PASSWORD_ERROR_MESSAGE},
      {description: "Should return '" + PASSWORD_ERROR_MESSAGE + "' if the password is undefined.", passwordOne: undefined, passwordTwo: "", expected: PASSWORD_ERROR_MESSAGE},
      {
        description: "Should return '" + PASSWORD_DO_NOT_MATCH_MESSAGE + "' if the passwords provided don't match.",
        passwordOne: "PassOne",
        passwordTwo: "PassTwo",
        expected: PASSWORD_DO_NOT_MATCH_MESSAGE
      }
    ];

    parameters.forEach((parameter) => {
      it(parameter.description, () => {
          let actualMessage = InputValidator.validateUserCreationInput("Name@mail.com", "Name", parameter.passwordOne, parameter.passwordTwo);
          expect(actualMessage).toBe(parameter.expected);
        }
      )
    });
  });

  describe("Should return NAME_ERROR_MESSAGE when the User Name is of wrong format", () => {
    const NAME_ERROR_MESSAGE = "*Please enter a valid name!";
    const parameters = [
      {description: "Should return '" + NAME_ERROR_MESSAGE + "' if the provided UserName is empty.", userName: "", expected: NAME_ERROR_MESSAGE},
      {description: "Should return '" + NAME_ERROR_MESSAGE + "' if the provided UserName is undefined.", userName: undefined, expected: NAME_ERROR_MESSAGE}
    ];

    parameters.forEach((parameter) => {
      it(parameter.description, () => {
          let actualMessage = InputValidator.validateUserCreationInput("Name@mail.com", parameter.userName, null, null);
          expect(actualMessage).toBe(parameter.expected);
        }
      )
    });
  });

  describe("Should return empty string when the input is correct", () => {
    it("Should return empty string when the input is correct", () => {
      let actualMessage = InputValidator.validateUserCreationInput("Name@mail.com", "Name", "Pass", "Pass");
      expect(actualMessage).toBe(undefined);
    });
  });
});

describe("ValidateLoginInput Tests", () => {

  describe("Should return PASSWORD_ERROR_MESSAGE when the password is not provided", () => {
    const PASSWORD_ERROR_MESSAGE = '*Please enter your password!';
    const passwordParameters = [
      {description: "Should return '" + PASSWORD_ERROR_MESSAGE + "' if the provided password is null.", input: undefined},
      {description: "Should return '" + PASSWORD_ERROR_MESSAGE + "' if the provided password is empty.", input: ""}
    ];

    passwordParameters.forEach((parameter) => {
      it(parameter.description, () => {
        let actualMessage = InputValidator.validateLoginInput("UserName", parameter.input);
        expect(actualMessage).toBe(PASSWORD_ERROR_MESSAGE);
      })
    });
  });

  describe("Should return EMAIL_ERROR_MESSAGE when the user name is not properly defined", () => {
    const EMAIL_ERROR_MESSAGE = '*Please enter a valid email!';
    const emailParameters = [
      {description: "Should return '" + EMAIL_ERROR_MESSAGE + "' if the Email provided is null.", input: undefined},
      {description: "Should return '" + EMAIL_ERROR_MESSAGE + "' if the Email provided is empty.", input: ""},
      {description: "Should return '" + EMAIL_ERROR_MESSAGE + "' if the Email provided is without the @ sign.", input: 'user.com'},
      {description: "Should return '" + EMAIL_ERROR_MESSAGE + "' if the Email provided is just the account name.", input: 'user'},
      {description: "Should return '" + EMAIL_ERROR_MESSAGE + "' if the Email provided is without the domain.", input: 'user@fmt.'}
    ];

    emailParameters.forEach((parameter) => {
      it(parameter.description, () => {
        let actualMessage = InputValidator.validateLoginInput(parameter.input, "Password");
        expect(actualMessage).toBe(EMAIL_ERROR_MESSAGE);
      });
    });
  });

  describe("Should return empty string when the input is correct", () => {
    it("Should return empty string when the input is correct", () => {
      let actualMessage = InputValidator.validateLoginInput("Name@mail.com", "Password");
      expect(actualMessage).toBe(undefined);
    });
  });
});
