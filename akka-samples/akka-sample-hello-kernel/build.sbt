import NativePackagerHelper._

name := "hello-kernel"

version := "0.1"

val akkaVersion = "2.3-SNAPSHOT"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-kernel" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.0.7"
)

mainClass in Compile := Some("akka.kernel.Main")

enablePlugins(JavaServerAppPackaging)

mappings in Universal ++= {
  // copy additional scripts from bin directory
  directory("bin") ++
  // copy configuration files
  contentOf("src/main/resources").toMap.mapValues("config/" + _)
}

// add 'config' directory first in the classpath of the start script
scriptClasspath := Seq("../config/") ++ scriptClasspath.value
