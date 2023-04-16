package io.github.rzqx

import scala.util.Try

final class Lexorank private (val decimal: BigDecimal)
    extends AnyVal
    with Ordered[Lexorank]
    with Product1[BigDecimal]
    with Serializable {

  def toPaddedString(wholeLength: Int): String = {
    val raw = decimal.toString
    val paddingLength = wholeLength - raw.length + decimal.scale + 1

    if (paddingLength > 0) {
      val padding = "0" * paddingLength
      padding + raw
    } else raw
  }

  override def toString: String = toPaddedString(0)

  override def compare(that: Lexorank): Int = {
    this.decimal.compare(that.decimal)
  }

  override def _1: BigDecimal = decimal

  override def canEqual(that: Any): Boolean = that.isInstanceOf[Lexorank]
}

object Lexorank {
  def between(a: Lexorank, b: Lexorank): Lexorank = {
    val (lower, upper) = if (a < b) (a, b) else (b, a)

    val d = (upper.decimal - lower.decimal) / 2
    val truncated = d.round(new java.math.MathContext(1, java.math.RoundingMode.DOWN))
    new Lexorank(lower.decimal + truncated)
  }

  def parse(value: String): Try[Lexorank] = Try(Lexorank(value))

  def apply(value: String): Lexorank = {
    val decimal = BigDecimal(value)

    if (decimal.signum == -1) {
      throw new IllegalArgumentException("Lexorank cannot be negative")
    } else {
      if (decimal.isWhole) {
        new Lexorank(decimal.setScale(1, BigDecimal.RoundingMode.DOWN))
      } else {
        new Lexorank(decimal)
      }
    }
  }
}
