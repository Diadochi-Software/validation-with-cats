package tech.diadochi.validation
package `try`

import Model.{User, UserForm}

import scala.util.{Failure, Success, Try}

class UserValidator {

  private def assertHasFirstName(firstName: String) =
    if (firstName.isBlank) Failure(new Exception("First name is required"))
    else Success(firstName)

  private def assertHasLastName(lastName: String) =
    if (lastName.isBlank) Failure(new Exception("Last name is required"))
    else Success(lastName)

  private def assertHasEmail(email: String) =
    if (email.isBlank) Failure(new Exception("Email is required"))
    else Success(email)

  private def assertEmailFormat(email: String) =
    if (!email.contains("@")) Failure(new Exception("Email is invalid"))
    else Success(email)

  private def assertHasPassword(password: String) =
    if (password.isBlank) Failure(new Exception("Password is required"))
    else Success(password)

  private def assertHasConfirmPassword(confirmPassword: String) =
    if (confirmPassword.isBlank) Failure(new Exception("Confirm password is required"))
    else Success(confirmPassword)

  private def assertPasswordsMatch(password: String, confirmPassword: String) =
    if (password != confirmPassword) Failure(new Exception("Passwords do not match"))
    else Success(password)

  private def assertHasPhoneNumber(phoneNumber: String) =
    if (phoneNumber.isBlank) Failure(new Exception("Phone number is required"))
    else Success(phoneNumber)

  private def assertHasWebsite(website: String) =
    if (website.isBlank) Failure(new Exception("Website is required"))
    else Success(website)

  def attemptValidateUser(info: UserForm): Try[User] =
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
