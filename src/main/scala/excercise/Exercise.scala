package tech.diadochi.validation
package excercise

object Exercise {

  private val orderValidator = new OrderValidator

  private val validOrder = OrderForm("A987", 1, 39.99)
  private val orderWithNegativeQuantity = OrderForm("A987", -1, 39.99)
  private val orderWithZeroQuantity = OrderForm("A987", 0, 39.99)
  private val orderWithInvalidProductCode = OrderForm("A98", 1, 39.99)
  private val orderWithInvalidPrice = OrderForm("A987", 1, 39.98)
  private val orderWithMultipleErrors = OrderForm("A98", -1, 39.98)

  private def printOrder(order: OrderForm): Unit = {
    println(s"Order: productCode=${order.productCode}, quantity=${order.quantity}, price=${order.price}")
    println(orderValidator.validate(order))
  }

  def main(args: Array[String]): Unit = {
    printOrder(validOrder)
    printOrder(orderWithNegativeQuantity)
    printOrder(orderWithZeroQuantity)
    printOrder(orderWithInvalidProductCode)
    printOrder(orderWithInvalidPrice)
    printOrder(orderWithMultipleErrors)
  }

}
