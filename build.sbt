organization := "middle-earth"
name := "scala-persistence"
version := "0.1"
scalaVersion := "2.12.8"

libraryDependencies ++= SbtDependencies.persistence
scalacOptions ++= SbtOptions.compiler

addCommandAlias( "check", "clean; update; compile; test:compile; test")
addCommandAlias( "coverage", "clean; compile; test:compile; coverage; test; coverageReport")