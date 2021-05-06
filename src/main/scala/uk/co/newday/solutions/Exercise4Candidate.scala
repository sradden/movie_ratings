package uk.co.newday.solutions

import org.apache.spark.sql.{DataFrame, SaveMode}
import org.apache.spark.sql.functions.lit

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Exercise4Candidate {

  /**
   * writes all the specified [[DataFrame]] to parquet files
   * @param movies the movies [[DataFrame]]
   * @param ratings the ratings [[DataFrame]]
   * @param movieRatings the movies and ratings [[DataFrame]]
   * @param ratingWithRankTop3 the top 3 ranked movies per user [[DataFrame]]
   */
  def execute(movies: DataFrame, ratings: DataFrame, movieRatings:DataFrame, ratingWithRankTop3:DataFrame) = {

    writeToParquet(movies, "movies")
    writeToParquet(ratings, "ratings")
    writeToParquet(movieRatings, "movieRatings")
    writeToParquet(ratingWithRankTop3, "ratingWithRankTop3")
  }

  private [this] def writeToParquet(dataFrame: DataFrame, path: String): Unit ={

    dataFrame.
      withColumn("processedAt", lit(DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now)))
      .write
      .mode(SaveMode.Overwrite)
      .partitionBy("processedAt","movieId")
      .parquet(s"output/$path/")
  }
}
