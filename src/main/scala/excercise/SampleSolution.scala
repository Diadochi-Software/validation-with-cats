package tech.diadochi.validation
package excercise

import cats.data.Validated.{invalidNec, valid, validNec}
import cats.data.ValidatedNec

class SampleSolution {

  import SampleSolution._

  private def validateQuantity(quantity: Int) =
    if (quantity > 0) validNec(OrderQunatity(quantity))
    else invalidNec(InvalidQuantity(quantity))

  private def validateProductExists(code: FormattedCode) = Utils.productPrices.get(code.value) match {
    case Some(_) => validNec(ProductCode(code.value))
    case None => invalidNec(ProductCodeNotFound(code.value))
  }

  private def validateProductoFormat(order: String) =
    if (Utils.productCodeRegex.matches(order)) validNec(FormattedCode(order))
    else invalidNec(InvalidProductCode(order))

  private def validateProduct(order: String) = validateProductoFormat(order) andThen validateProductExists

  private def validatePrices(code: ProductCode, quantity: OrderQunatity, price: Double) =
    if (price == Utils.productPrices(code.value) * quantity.value) valid(OrderPrice(price))
    else invalidNec(InvalidPrice(price, Utils.productPrices(code.value) * quantity.value))

  private def validateBase(order: OrderForm): ValidatedNec[OrderError, (ProductCode, OrderQunatity)] =
    validateProduct(order.productCode) product validateQuantity(order.quantity)

  def validate(order: OrderForm): OrderValidation[Order] =
    validateProduct(order.productCode) product validateQuantity(order.quantity) andThen {
      case (code, quantity) => validatePrices(code, quantity, order.price) map (price => Order(code, quantity, price))
    }


}

object SampleSolution {

  type OrderValidation[A] = ValidatedNec[OrderError, Order]

  sealed trait OrderError {
    def message: String
  }

  case class InvalidProductCode(code: String) extends OrderError {
    override def message: String = s"Product code $code is not X123"
  }

  case class ProductCodeNotFound(code: String) extends OrderError {
    override def message: String = s"Product code $code not found"
  }

  case class InvalidQuantity(quantity: Int) extends OrderError {
    override def message: String = s"Quantity $quantity is not greater than 0"
  }

  case class InvalidPrice(actual: Double, expected: Double) extends OrderError {
    override def message: String = s"Price $actual should be $expected"
  }


}


