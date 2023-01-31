package tech.diadochi.validation
package either

import Model.{User, UserForm}

class UserValidator {

  import UserValidator._

  private def assertHasFirstName(firstName: String) = if (firstName.isBlank) Left(MustHaveFirstName) else Right(firstName)

  private def assertHasLastName(lastName: String) = if (lastName.isBlank) Left(MustHaveLastName) else Right(lastName)

  private def assertHasEmail(email: String) = if (email.isBlank) Left(MustHaveEmail) else Right(email)

  private def assertEmailFormat(email: String) = if (!email.contains("@")) Left(MustHaveValidEmail) else Right(email)

  private def assertHasPassword(password: String) = if (password.isBlank) Left(MustHavePassword) else Right(password)

  private def assertHasConfirmPassword(confirmPassword: String) =
    if (confirmPassword.isBlank) Left(MustHaveConfirmPassword) else Right(confirmPassword)

  private def assertPasswordsMatch(password: String, confirmPassword: String) =
    if (password != confirmPassword) Left(PasswordsMustMatch) else Right(password)

  private def assertHasPhoneNumber(phoneNumber: String) = if (phoneNumber.isBlank) Left(MustHavePhoneNumber) else Right(phoneNumber)

  private def assertHasWebsite(website: String) = if (website.isBlank) Left(MustHaveWebsite) else Right(website)

  def eitherUserOrValidationError(info: UserForm): Either[UserValidationError, User] =
    for {
      firstName <- assertHasFirstName(info.firstName)
      lastName <- assertHasLastName(info.lastName)
      email <- assertHasEmail(info.email)
      email <- assertEmailFormat(email)
      password <- assertHasPassword(info.password)
      confirmPassword <- assertHasConfirmPassword(info.confirmPassword)
      password <- assertPasswordsMatch(password, confirmPassword)
      phoneNumber <- assertHasPhoneNumber(info.phoneNumber)
      website <- assertHasWebsite(info.website)
    } yield User(firstName, lastName, email, phoneNumber, website, password)


}

object UserValidator {

  sealed trait UserValidationError {
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
