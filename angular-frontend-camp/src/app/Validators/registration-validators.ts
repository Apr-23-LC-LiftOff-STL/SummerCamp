import { FormControl, ValidationErrors } from '@angular/forms';

export class RegistrationValidators {
  static notOnlyWhitespace(control: FormControl): ValidationErrors | null {
    if (control.value != null && control.value.trim().length === 0) {
      return { 'notOnlyWhitespace': true };
    } else {
      return null;
    }
  } // static method notOnlyWhtieSpace - custom validator

  static requiredFieldMinSize2NotOnlyWhitespace(
    control: FormControl
  ): ValidationErrors | null {
    if (control.value != null && control.value.trim().length === 0) {
      return { 'requiredFieldMinSize2NotOnlyWhitespace': true };
    } else if (control.value.trim().length < 2) {
      return { 'requiredFieldMinSize2NotOnlyWhitespace': true };
    } else {
      return null;
    }
  } // static method multi check custom validator
}
