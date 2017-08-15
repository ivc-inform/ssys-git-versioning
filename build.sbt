sbtPlugin := true

name := "ssys-git-versioning"

organization := "ru.simplesys"

version := "1.0.4-SNAPSHOT"

scalaVersion := "2.12.3"

scalacOptions := Seq(
      "-feature",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-language:postfixOps",
      "-deprecation",
      "-unchecked")

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.9.3")

