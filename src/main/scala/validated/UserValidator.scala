package tech.diadochi.validation
package validated

import Model.{User, UserForm}

import cats.data.Validated.{invalidNec, validNec}
import cats.data.ValidatedNec
import cats.implicits.catsSyntaxTuple6Semigroupal

class UserValidator {

  import UserValidator._

  private def validateHasFirstName(firstName: String): UserValidation[String] =
    if (firstName.isBlank) invalidNec(MustHaveFirstName) else validNec(firstName)

  private def validateHasLastName(lastName: String): UserValidation[String] =
    if (lastName.isBlank) invalidNec(MustHaveLastName) else validNec(lastName)

  private def validateHasEmail(email: String): UserValidation[String] =
    if (email.isBlank) invalidNec(MustHaveEmail) else validNec(email)

  private def validateEmailFormat(email: String): UserValidation[String] =
    if (!email.contains("@")) invalidNec(MustHaveValidEmail) else validNec(email)

  private def validateEmail(email: String): UserValidation[String] =
    validateHasEmail(email) andThen validateEmailFormat

  private def validateHasPassword(password: String): UserValidation[String] =
    if (password.isBlank) invalidNec(MustHavePassword) else validNec(password)

  private def validatePasswordsMatch(passwords: (String, String)): UserValidation[String] =
    if (passwords._1 != passwords._2) invalidNec(PasswordsMustMatch) else validNec(passwords._1)

  private def validatePassword(password: String, confirmPassword: String): UserValidation[String] =
    (validateHasPassword(password) product validateHasPassword(confirmPassword)) andThen validatePasswordsMatch

  private def validateHasPhoneNumber(phoneNumber: String): UserValidation[String] =
    if (phoneNumber.isBlank) invalidNec(MustHavePhoneNumber) else validNec(phoneNumber)

  private def validateHasWebsite(website: String): UserValidation[String] =
    if (website.isBlank) invalidNec(MustHaveWebsite) else validNec(website)

  def validatedUser(info: UserForm): UserValidation[User] = {
    (
      validateHasFirstName(info.firstName),
      validateHasLastName(info.lastName),
      validateEmail(info.email),
      validatePassword(info.password, info.confirmPassword),
      validateHasPhoneNumber(info.phoneNumber),
      validateHasWebsite(info.website)
    ) mapN { (firstName, lastName, email, password, phoneNumber, website) =>
      User(firstName, lastName, email, phoneNumber, website, password)
    }
  }

}

object UserValidator {

  type UserValidation[A] = ValidatedNec[UserValidationError, A]

  sealed trait UserValidationError extends Exception {
    def message: String
  }

  case object MustHaveFirstName extends UserValidationError {
    override def message: String = "First name is required"
  }

  case object MustHaveLastName extends UserValidationError {
    override def message: String = "Last name is required"
  }

  case object MustHaveEmail extends UserValidationError {
    override def message: String = "Email is required"
  }

  case object MustHaveValidEmail extends UserValidationError {
    override def message: String = "Email is invalid"
  }

  case object MustHavePassword extends UserValidationError {
    override def message: String = "Password is required"
  }

  case object MustHaveConfirmPassword extends UserValidationError {
    override def message: String = "Confirm password is required"
  }

  case object PasswordsMustMatch extends UserValidationError {
    override def message: String = "Passwords do not match"
  }

  case object MustHavePhoneNumber extends UserValidationError {
    override def message: String = "Phone number is required"
  }

  case object MustHaveWebsite extends UserValidationError {
    override def message: String = "Website is required"
  }

}
