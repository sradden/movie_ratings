# movie_ratings


## Requirements
- spark version 2.2.0

- scala version 2.11.8

## Setup
Add sbt-assembly as a dependency in  `project/assembly.sbt`:

```scala
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "x.y.z")
```
## Build and packaging
`build.sbt` compiles and packages the project into `movie_ratings_%SPARK_VERSION%.jar`

## Main class
The application can be run using the following code:

``` scala
new MoviesRatingsCandidate.main(new MovieSettings, new RatingsSettings)
```
## spark-submit
For the purpose of this exercise a `SparkSession` is created to run locally.
```scala
private [this] lazy val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
```
It is not a recommended approach for a Production scenario. Instead you should either specify spark configuration in a configuration file or pass as key-value pairs args via the command line to spark-submit.

```
./bin/spark-submit \
  --class <main-class> \
  --master <master-url> \
  --deploy-mode <deploy-mode> \
  --conf <key>=<value> \
  ... # other options
  <application-jar> \
  [application-arguments]
 ```
