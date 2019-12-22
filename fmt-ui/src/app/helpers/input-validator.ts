export class InputValidator {

  static validateUserCreationInput(userEmail: string, userName: string, password: string, verifiedPassword: string): string {
    let errorMessage: string;
    if (!InputValidator.isEmailValid(userEmail)) {
      errorMessage = '*Please enter a valid email!';
    } else if (!InputValidator.isValidString(userName)) {
      errorMessage = '*Please enter a valid name!';
    } else if (!InputValidator.isValidString(password)) {
      errorMessage = '*Please enter a valid password!';
    } else if (InputValidator.passwordDontMatch(password, verifiedPassword)) {
      errorMessage = '*Passwords do not match!';
    }
    return errorMessage;
  }

  static isValidString(value: string): boolean {
    return value != undefined && value.length != 0;
  }

  static isEmailValid(userEmail: string): boolean {
    const emailRegEx = new RegExp('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+[.][a-zA-Z0-9]+$');
    return this.isValidString(userEmail) && emailRegEx.test(userEmail);
  }

  private static passwordDontMatch(password1: string, password2: string) {
    return password1 != password2;
  }
}
