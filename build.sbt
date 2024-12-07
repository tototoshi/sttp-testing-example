val scalaVersion_3 = "3.5.2"

inThisBuild(
  List(
    scalaVersion := scalaVersion_3,
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision
  )
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "sttp-testing-example",
    organization := "com.github.tototoshi",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scalaVersion_3,
    scalacOptions ++= Seq("-Wunused:imports"),
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client3" %% "core" % "3.10.1",
      "com.softwaremill.sttp.client3" %% "play-json" % "3.10.1",
      "org.scalatest" %% "scalatest" % "3.2.19" % "test"
    )
  )
