ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "validation-with-cats",
    libraryDependencies ++= Seq("org.typelevel" %% "cats-core" % "2.9.0", "org.typelevel" %% "cats-kernel" % "2.9.0", "org.scalatest" %% "scalatest" % "3.2.15" % "test"),
    idePackagePrefix := Some("tech.diadochi.validation")
  )
