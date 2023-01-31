package tech.diadochi.validation

import Model.UserForm
import validated.UserValidator

import cats.data.Validated.{Invalid, Valid}

object Main {
  def main(args: Array[String]): Unit = {

    val user = UserForm("John", "Doe", "jodoe.com", "555-555-5555", "https://www.johndoe.com", "password1", "password2")

    println(new`try`.UserValidator().attemptValidateUser(user))
    println(new either.UserValidator().eitherUserOrValidationError(user))

    val validatedUser = new UserValidator().validatedUser(user)
    validatedUser match {
      case Valid(user) => println(user)
      case Invalid(errors) => errors.map(e => println(e.message))
    }

  }

}