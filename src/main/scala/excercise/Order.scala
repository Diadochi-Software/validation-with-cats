package tech.diadochi.validation
package excercise

case class Order(productCode: ProductCode, quantity: OrderQunatity, price: OrderPrice)

case class FormattedCode(value: String) extends AnyVal

case class ProductCode(value: String) extends AnyVal

case class OrderQunatity(value: Int) extends AnyVal

case class OrderPrice(value: Double) extends AnyVal

case class OrderForm(productCode: String, quantity: Int, price: Double)
