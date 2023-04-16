package io.github.rzqx

import scala.util.Try

final class Dexorank private (val decimal: BigDecimal)
    extends AnyVal
    with Ordered[Dexorank]
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

  override def compare(that: Dexorank): Int = {
    this.decimal.compare(that.decimal)
  }

  override def _1: BigDecimal = decimal

  override def canEqual(that: Any): Boolean = that.isInstanceOf[Dexorank]
}

object Dexorank {
  def between(a: Dexorank, b: Dexorank): Dexorank = {
    val (lower, upper) = if (a < b) (a, b) else (b, a)

    val d = (upper.decimal - lower.decimal) / 2

    val truncated = if (d >= 1) {
      d.setScale(0, BigDecimal.RoundingMode.DOWN)
    } else {
      d.round(new java.math.MathContext(1, java.math.RoundingMode.DOWN))
    }

    new Dexorank(lower.decimal + truncated)
  }

  def between(a: String, b: String): Dexorank =
    between(Dexorank(a), Dexorank(b))

  def parse(value: String): Try[Dexorank] = Try(Dexorank(value))

  def apply(decimal: BigDecimal): Dexorank = {
    if (decimal.signum == -1) {
      throw new IllegalArgumentException("Lexorank cannot be negative")
    } else {
      if (decimal.isWhole) {
        new Dexorank(decimal.setScale(1, BigDecimal.RoundingMode.DOWN))
      } else {
        new Dexorank(decimal.underlying.stripTrailingZeros())
      }
    }
  }

  def apply(value: String): Dexorank = apply(BigDecimal(value))
}
