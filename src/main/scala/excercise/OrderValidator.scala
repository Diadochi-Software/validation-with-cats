package tech.diadochi.validation
package excercise

class OrderValidator {

  import OrderValidator._

  def validate(order: Order): OrderValidation[Order] = ???

}

object OrderValidator {

  type OrderValidation[A]

}
