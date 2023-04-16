package io.github.rzqx

import scala.util.Try

class DexorankSuite extends munit.FunSuite {
  test("should calculate toString properly for zero values") {
    val expected = "0.0"

    for {
      sign <- List("-", "")
      prefix <- List("", "0", "00")
      dot <- List(".", "")
      postfix <- List("", "0", "00")
    } yield {
      val input = sign + prefix + dot + postfix

      if (input.contains("0")) {
        assertEquals(Dexorank(input).toString, expected)
      }
    }
  }

  test("should calculate toPaddedString properly for zero values") {
    val expected = "000.0"

    for {
      sign <- List("-", "")
      prefix <- List("", "0", "00")
      dot <- List(".", "")
      postfix <- List("", "0", "00")
    } yield {
      val input = sign + prefix + dot + postfix

      if (input.contains("0")) {
        assertEquals(Dexorank(input).toPaddedString(3), expected)
      }
    }
  }

  test("should calculate toString properly for non-zero values") {
    assertEquals(Dexorank("1").toString, "1.0")
    assertEquals(Dexorank("001.00").toString, "1.0")
    assertEquals(Dexorank("001.").toString, "1.0")
    assertEquals(Dexorank("001").toString, "1.0")
    assertEquals(Dexorank("000.0100").toString, "0.01")
    assertEquals(Dexorank("001.0100").toString, "1.01")
  }

  test("should calculate toPaddedString properly for non-zero values") {
    assertEquals(Dexorank("1").toPaddedString(3), "001.0")
    assertEquals(Dexorank("001.00").toPaddedString(3), "001.0")
    assertEquals(Dexorank("001.").toPaddedString(3), "001.0")
    assertEquals(Dexorank("001").toPaddedString(3), "001.0")
    assertEquals(Dexorank("000.0100").toPaddedString(3), "000.01")
    assertEquals(Dexorank("001.0100").toPaddedString(3), "001.01")
  }

  test("should not allow creating a negative Lexorank") {
    assert(Dexorank.parse("-1").isFailure)
    assert(Try(Dexorank("-1")).isFailure)
  }

  test("should test equality between Lexoranks") {
    assertEquals(Dexorank("1"), Dexorank("1"))
    assertEquals(Dexorank("1.0"), Dexorank("1"))
    assertEquals(Dexorank("1.0"), Dexorank("0001.000"))
    assertNotEquals(Dexorank("1"), Dexorank("2"))
  }

  test("should calculate between two Lexoranks") {
    assertEquals(Dexorank.between("1", "1"), Dexorank("1"))

    assertEquals(Dexorank.between("1", "2"), Dexorank("1.5"))
    assertEquals(Dexorank.between("2", "1"), Dexorank("1.5"))

    assertEquals(Dexorank.between("1", "3"), Dexorank("2"))
    assertEquals(Dexorank.between("3", "1"), Dexorank("2"))

    assertEquals(Dexorank.between("1", "4"), Dexorank("2"))
    assertEquals(Dexorank.between("4", "1"), Dexorank("2"))

    assertEquals(Dexorank.between("0.01", "0.02"), Dexorank("0.015"))
    assertEquals(Dexorank.between("0.02", "0.01"), Dexorank("0.015"))

    assertEquals(Dexorank.between("0.01", "0.04"), Dexorank("0.02"))
    assertEquals(Dexorank.between("0.04", "0.01"), Dexorank("0.02"))

    assertEquals(Dexorank.between("1.001", "1.01"), Dexorank("1.005"))
    assertEquals(Dexorank.between("1.01", "1.001"), Dexorank("1.005"))

    assertEquals(Dexorank.between("30", "60"), Dexorank("45"))
    assertEquals(Dexorank.between("60", "30"), Dexorank("45"))

    assertEquals(Dexorank.between("30", "60.2"), Dexorank("45"))
    assertEquals(Dexorank.between("60.2", "30"), Dexorank("45"))
  }
}
