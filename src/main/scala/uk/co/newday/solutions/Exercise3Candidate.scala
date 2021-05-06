package uk.co.newday.solutions

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lit, row_number}

object Exercise3Candidate {

  /**
   * BONUS: Creates a new dataframe which contains each user’s (userId in the ratings data) top 3 movies based on their rating
   * @param movies the movies [[DataFrame]]
   * @param ratings the ratings [[DataFrame]]
   * @return a new [[DataFrame]] which contains each user’s (userId in the ratings data) top 3 movies based on their rating
   */
  def execute(movies: DataFrame, ratings: DataFrame): (DataFrame) = {
    // Complete the exercise and show the top 3 movies per user.

    val windowSpec = Window.partitionBy("userId").orderBy(col("rating").desc, col("movieId"))
    // this time we want all rows from ratings so we can use an inner join
    // use dense_rank to avoid gaps where there a ties
    val usingCols = movies.col("movieId") === ratings.col("movieId")
    val top3MoviesPerUser = ratings.join(movies, usingCols, "inner")
      .drop("genre", "timestamp")
      .drop(movies.col("movieId"))
      .withColumn("rowNum", row_number().over(windowSpec))
      .filter(col("rowNum").leq(lit(3)))
      .drop("rowNum")

    top3MoviesPerUser
  }
}
