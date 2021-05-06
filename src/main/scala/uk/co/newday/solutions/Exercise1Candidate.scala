package uk.co.newday.solutions

import org.apache.spark.sql.functions.split
import org.apache.spark.sql.{DataFrame, SparkSession}
import uk.co.newday.settings.Configurable

object Exercise1Candidate {

  //Please load movies and ratings csv's in output dataframes.
  private [this] lazy val spark = SparkSession.builder().config("spark.master", "local").getOrCreate()
  import spark.implicits._

  private case class Movie (movieId:Int, title:String, genre:String)
  private case class Rating (userId:Int, movieId:Int, rating:Int, timestamp:Int)

  /**
   * Return the movies and ratings data as separate DataFrames
   * @param settings
   * @return
   */
  def execute(settings: (Configurable, Configurable)): (DataFrame, DataFrame) =
  {

    // The movies file has a delimiter with multiple characters so need to read this
    // into single column before splitting into multiple columns
    val moviesLoaded = load(settings._1)
    val movies = moviesLoaded
      // safe to convert here because all the movie id's are integers
      // expr .filter(col("movieId").rlike("^[0-9]*$") === true
        .withColumn("movieId", split(moviesLoaded("_c0"),s"${settings._1.delimiter.get}")(0).cast("integer"))
        .withColumn("title", split(moviesLoaded("_c0"),s"${settings._1.delimiter.get}")(1))
        .withColumn("genre", split(moviesLoaded("_c0"),s"${settings._1.delimiter.get}")(2))
      .drop("_c0")
      .as[Movie]

    val ratingsLoaded = load(settings._2)
    val ratings = ratingsLoaded
      .withColumn("userId", split(ratingsLoaded("_c0"),s"${settings._2.delimiter.get}")(0).cast("integer"))
      .withColumn("movieId", split(ratingsLoaded("_c0"),s"${settings._2.delimiter.get}")(1).cast("integer"))
      .withColumn("rating", split(ratingsLoaded("_c0"),s"${settings._2.delimiter.get}")(2).cast("integer"))
      .withColumn("timestamp", split(ratingsLoaded("_c0"),s"${settings._2.delimiter.get}")(3).cast("integer"))
      .drop("_c0")
      .as[Rating]

    (movies.toDF(), ratings.toDF())
  }

  /**
   * Uses the specified settings to load source into a [[DataFrame]]
   * @param settings settings that control where the data is loaded from and what format
   * @return a [[DataFrame]] with the specified source
   */
  private def load(settings: Configurable) : DataFrame = {
    spark.read
      .format(settings.format.get)
      .option("header", s"${settings.header.get}")
      .load(s"${settings.path.get}/${settings.source.get}")
  }
}
