import xerial.sbt.Sonatype.*

val Scala212 = "2.12.17"
val Scala213 = "2.13.10"
val Scala3 = "3.2.2"

ThisBuild / crossScalaVersions := Seq(Scala212, Scala213, Scala3)
ThisBuild / scalaVersion := Scala213

ThisBuild / organization := "io.github.rzqx"
ThisBuild / organizationName := "rzqx"

ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision
ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.6.0"
ThisBuild / scalacOptions ++= List("-Ywarn-unused")

ThisBuild / licenses := Seq("APL2" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt"))
ThisBuild / sonatypeProjectHosting := Some(
  GitHubHosting("rzqx", "dexorank", "me@melvinlow.com"))

ThisBuild / publishTo := sonatypePublishToBundle.value

lazy val root = (project in file(".")).settings(
  name := "dexorank",
  description := "Base 10 Lexorank",
  libraryDependencies ++= List(
    "org.scalameta" %% "munit" % "0.7.29" % Test
  )
)
