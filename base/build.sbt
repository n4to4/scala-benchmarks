ThisBuild / scalaVersion := "2.12.13"
//ThisBuild / scalaVersion := "2.13.6"
//ThisBuild / scalaVersion := "3.0.0"

enablePlugins(JmhPlugin)

scalacOptions += "-deprecation"

//ThisBuild / scalacOptions := Seq("-Yexplicit-nulls")
Global / onChangedBuildSource := ReloadOnSourceChanges

//
// dependencies
//

//libraryDependencies ++= Seq(
//  "org.typelevel" %% "cats-core" % "2.6.1",
//  "org.typelevel" %% "alleycats-core" % "2.6.1",
//  "org.typelevel" % "kind-projector_2.13.6" % "0.13.0"
//)
//
//addCompilerPlugin(
//  "org.typelevel" % "kind-projector" % "0.13.0" cross CrossVersion.full
//)
//
//val circeVersion = "0.14.1"
//libraryDependencies ++= Seq(
//  "io.circe" %% "circe-core",
//  "io.circe" %% "circe-generic",
//  "io.circe" %% "circe-generic-extras",
//  "io.circe" %% "circe-parser"
//).map(_ % circeVersion)

//scalacOptions += "-Ymacro-annotations"
//resolvers += Resolver.sonatypeRepo("releases")
//addCompilerPlugin(
//  "org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full
//)
