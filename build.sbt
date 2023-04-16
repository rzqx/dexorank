ThisBuild / scalaVersion := "2.13.10"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.github.rzqx"
ThisBuild / organizationName := "rzqx"

lazy val root = (project in file(".")).settings(
  name := "dexorank",
  libraryDependencies ++= List(
    "org.scalameta" %% "munit" % "0.7.29" % Test
  )
)