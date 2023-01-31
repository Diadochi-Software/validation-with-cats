package tech.diadochi.validation

object Model {

  case class UserForm(firstName: String, lastName: String, email: String, phoneNumber: String, website: String, password: String, confirmPassword: String)

  case class User(firstName: String, lastName: String, email: String, phoneNumber: String, website: String, password: String)

}
