package tech.diadochi.validation
package exceptions

import Model.{User, UserForm}

class UserValidator {

  def validateUser(info: UserForm): User = {
    if (info.firstName.isBlank) throw new Exception("First name is required")
    if (info.lastName.isBlank) throw new Exception("Last name is required")
    if (info.email.isBlank) throw new Exception("Email is required")
    if (!info.email.contains("@")) throw new Exception("Email is invalid")
    if (info.phoneNumber.isBlank) throw new Exception("Phone number is required")
    if (info.website.isBlank) throw new Exception("Website is required")
    if (info.password.isBlank) throw new Exception("Password is required")
    if (info.confirmPassword.isBlank) throw new Exception("Confirm password is required")
    if (info.password != info.confirmPassword) throw new Exception("Passwords do not match")
    User(info.firstName, info.lastName, info.email, info.phoneNumber, info.website, info.password)
  }

}
