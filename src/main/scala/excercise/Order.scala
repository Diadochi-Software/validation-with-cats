package tech.diadochi.validation
package excercise

case class Order(productCode: String, quantity: Int, price: Double)

case class OrderForm(productCode: String, quantity: Int, price: Double)
