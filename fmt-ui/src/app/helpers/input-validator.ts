export class InputValidator {
  static isValidString(value: string): boolean {
    return value != undefined && value.length != 0;
  }

  static isEmailValid(userEmail: string): boolean {
    const emailRegEx = new RegExp('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+[.][a-zA-Z0-9]+$');
    return this.isValidString(userEmail) && emailRegEx.test(userEmail);
  }

}
