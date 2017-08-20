sbtPlugin := true

name := "ssys-git-versioning"

organization := "ru.simplesys"

version := "1.0.3-SNAPSHOT"

scalaVersion := "2.10.6"

scalacOptions := Seq(
    "-feature",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-deprecation",
    "-unchecked")

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.8.4")


