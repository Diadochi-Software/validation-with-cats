package tech.diadochi.validation
package excercise

object Exercise {

  private val orderValidator = new OrderValidator

  private val validOrder = Order("A987", 1, 39.99)
  private val orderWithNegativeQuantity = Order("A987", -1, 39.99)
  private val orderWithZeroQuantity = Order("A987", 0, 39.99)
  private val orderWithInvalidProductCode = Order("A98", 1, 39.99)
  private val orderWithInvalidPrice = Order("A987", 1, 39.98)
  private val orderWithMultipleErrors = Order("A98", -1, 39.98)

  private def printOrder(order: Order): Unit = {
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
