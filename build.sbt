sbtPlugin := true

name := "ssys-git-versioning"

organization := "ru.simplesys"

version := "1.0.3"

//scalaVersion := "2.10.5"

//crossScalaVersions := Seq("2.11.7", "2.10.5")

scalacOptions := Seq(
      "-feature",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-language:postfixOps",
      "-deprecation",
      "-unchecked")

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.8.4")

