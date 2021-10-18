//ThisBuild / scalaVersion := "2.12.12"
ThisBuild / scalaVersion := "2.13.6"
//ThisBuild / scalaVersion := "3.0.2"

Global / onChangedBuildSource := ReloadOnSourceChanges
enablePlugins(JmhPlugin)

libraryDependencies ++= Seq(
  // compilerPlugin(
  //   ("org.scalamacros" % "paradise" % "2.1.1").cross(CrossVersion.patch)
  // ),
  // //
  // "com.twitter" %% "util-core" % "20.3.0",
  // "com.github.ben-manes.caffeine" % "caffeine" % "2.8.0",
  // //
  // "io.circe" %% "circe-core" % circe,
  // "io.circe" %% "circe-generic" % circe,
  // "io.circe" %% "circe-generic-extras" % circe,
  // "io.circe" %% "circe-derivation" % "0.13.0-M5",
  // "io.circe" %% "circe-derivation-annotations" % "0.13.0-M5",
  // "io.circe" %% "circe-parser" % circe,
  // "io.circe" %% "circe-refined" % circe,
  // "eu.timepit" %% "refined" % "0.9.3",
  // //"io.catbird" %% "catbird-util" % "21.5.0",
  // "org.typelevel" %% "cats-core" % "2.2.0" //"2.6.1",
)
