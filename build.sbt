scalaVersion := "2.11.8"

val sparkVersion = "2.2.0"

// group id,artefact id, version
// specify %% if you want sbt to resolve the scala version
// specify % if you require a specific version of scala
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.2.0" % "provided",
  "org.apache.spark" %%"spark-sql" % "2.2.0" % "provided",
  "org.scalatest" % s"scalatest_${scalaBinaryVersion.value}" % sparkVersion % "test")

lazy val commonSettings = Seq(
  name := "movie_ratings",
  organization := "uk.co.newday",
  version := "1.0"
)

lazy val movieRatings : Project = (project in file("."))
  .settings(
    commonSettings: _*)
  .settings(
    // skip tests during assembly
    test in assembly := {},
    assembly / mainClass := Some("uk.co.newday.MoviesRatingsCandidate"),
    assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false),
    // exclude data files from .jar
    excludeFilter in resources := "*.dat",
    assembly / assemblyOutputPath := file(s"/out/artificat/${name.value}_${scalaBinaryVersion.value}-${sparkVersion}_${version.value}.jar")
  )