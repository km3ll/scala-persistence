import sbt._

object SbtDependencies {

  val basic: Seq[ModuleID] = Seq(
    "org.typelevel"               %% "cats-core"       % "2.0.0",
    "org.typelevel"               %% "cats-effect"     % "2.0.0",
    "org.apache.commons"          %  "commons-lang3"   % "3.3.2",
    "com.typesafe"                %  "config"          % "1.3.2",
    "ch.qos.logback"              %  "logback-classic" % "1.1.3",
    "io.monix"                    %% "monix"           % "2.3.3",
    "org.scalacheck"              %% "scalacheck"      % "1.14.0",
    "org.scalatest"               %% "scalatest"       % "3.0.5",
    "com.typesafe.scala-logging"  %% "scala-logging"   % "3.9.2",
    "com.github.pureconfig"       %% "pureconfig"      % "0.17.1"
  )

  val persistence: Seq[ModuleID] = Seq(
    "org.cassandraunit"           %  "cassandra-unit" % "3.1.3.2",
    "com.h2database"              %  "h2"             % "1.4.189",
    "com.outworkers"              %% "phantom-dsl"    % "2.31.0",
    "com.outworkers"              %% "phantom-jdk8"   % "2.31.0",
    "com.typesafe.slick"          %% "slick"          % "3.3.0"
  ) ++ basic

}