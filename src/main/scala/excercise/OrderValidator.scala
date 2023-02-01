package tech.diadochi.validation
package excercise

class OrderValidator {

  import OrderValidator._

  def validate(order: OrderForm): OrderValidation[Order] = ???

}

object OrderValidator {

  type OrderValidation[A]

}
