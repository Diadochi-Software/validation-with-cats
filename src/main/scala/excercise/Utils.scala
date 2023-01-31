package tech.diadochi.validation
package excercise

import scala.util.matching.Regex

object Utils {

  val productCatalogue: List[String] = List("X486", "D123", "A987", "B123", "C456", "Z999")
  val productPrices: Map[String, Double] = Map("X486" -> 19.99, "D123" -> 29.99, "A987" -> 39.99, "B123" -> 49.99, "C456" -> 59.99, "Z999" -> 69.99)
  val productCodeRegex: Regex = "^[A-Z][0-9]{3}$".r

}
