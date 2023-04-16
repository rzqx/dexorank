inThisBuild(
  List(
    scalaVersion := "2.13.10",
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    version := "0.1.0-SNAPSHOT",
    organization := "io.github.rzqx",
    organizationName := "rzqx",
    scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.6.0",
    scalacOptions ++= List("-Wunused")
  )
)

lazy val root = (project in file(".")).settings(
  name := "dexorank",
  libraryDependencies ++= List(
    "org.scalameta" %% "munit" % "0.7.29" % Test
  ),
)