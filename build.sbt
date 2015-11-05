name := "scala-selenium-browser-tests"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
"org.specs2" % "specs2-core_2.11" % "3.6.5" % "test",
"org.fluentlenium" % "fluentlenium-core" % "0.10.6" % "test"
)

parallelExecution in Test := true


