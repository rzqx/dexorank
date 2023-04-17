# Dexorank

Dexorank is a base 10 version of Jira's Lexorank implemented
as a thin wrapper around `BigDecimal`. By using base 10, we
sacrifice some of the space efficiency of Lexorank (base 62) but gain the ability
to use `BigDecimal` for a straightforward implementation with little additional overhead.

## Examples

```scala
/** Create a new rank with any BigDecimal or BigDecimal string */
Dexorank(BigDecimal("1.23")) // OK
Dexorank("1.23") // OK
Dexorank("0001.23") // OK

Dexorank("not-a-number") // Exception! Use Dexorank.parse instead to handle potential errors
Dexorank.parse("not-a-number") // Returns a Try[Dexorank], in this case a Failure

/** Dexorank implements Ordered */
Dexorank("1.23") < Dexorank("1.24") // true
Dexorank("1.23") > Dexorank("1.24") // false

/** Get a rank between two others */
Dexorank.between("1", "2") // Dexorank("1.5")
Dexorank.between("1", "3") // Dexorank("2")
Dexorank.between("1", "4") // Dexorank("2") (truncates)

Dexorank.between(Dexorank("1"), Dexorank("2")) // Passing in Dexoranks is also OK

/** Do arbitrary math by grabbing the underlying BigDecimal */
val r1 = Dexorank("1")
val r2 = Dexorank("2")
val sum = Dexorank(r1.decimal + r2.decimal) // Dexorank("3")

/** 
 * Get a left padded version for lexicographic comparisons
 * 
 * The padded length refers to the length of the 
 * integral part (left of the period)
 */
Dexorank("30.2").toPaddedString(3) // "030.2"
Dexorank("0.01").toPaddedString(3) // "000.01"
```