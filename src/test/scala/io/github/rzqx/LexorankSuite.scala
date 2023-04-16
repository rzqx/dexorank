package io.github.rzqx

class LexorankSuite extends munit.FunSuite {
  test("should calculate toString properly for zero values") {
    val expected = "0.0"

    assertEquals(Lexorank("0").toString, expected)
    assertEquals(Lexorank(".0").toString, expected)
    assertEquals(Lexorank(".00").toString, expected)
    assertEquals(Lexorank("0.").toString, expected)
    assertEquals(Lexorank("00.").toString, expected)
    assertEquals(Lexorank("0.0").toString, expected)
    assertEquals(Lexorank("00.0").toString, expected)
    assertEquals(Lexorank("00.00").toString, expected)
    assertEquals(Lexorank("0000").toString, expected)
  }

  test("should calculate toPaddedString properly for zero values") {
    val expected = "000.0"

    assertEquals(Lexorank("0").toPaddedString(3), expected)
    assertEquals(Lexorank(".0").toPaddedString(3), expected)
    assertEquals(Lexorank(".00").toPaddedString(3), expected)
    assertEquals(Lexorank("0.").toPaddedString(3), expected)
    assertEquals(Lexorank("00.").toPaddedString(3), expected)
    assertEquals(Lexorank("0.0").toPaddedString(3), expected)
    assertEquals(Lexorank("00.0").toPaddedString(3), expected)
    assertEquals(Lexorank("00.00").toPaddedString(3), expected)
    assertEquals(Lexorank("0000").toPaddedString(3), expected)
  }
}
