package uk.co.newday.solutions

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{_}

object Exercise2Candidate {

  /**
   * Creates a new dataframe called movieRatings, containing the movies data and
   * 3 new columns which contain the max, min and average rating for that movie from the ratings data
   * @param movies the movies [[DataFrame]]
   * @param ratings the ratings [[DataFrame]]
   * @return a new [[DataFrame]] with 3 new columns which contain the max, min and average rating for the movie.
   */
  def execute(movies: DataFrame, ratings: DataFrame): (DataFrame) = {
    //With two dataframe apply the join as specified in the exercise.
    // dataframes join on movieId so i will use a left join on ratings in case some
    // movies do not have a rating
    val usingCols = movies.col("movieId") === ratings.col("movieId")
    val moviesRatings = movies.join(ratings, usingCols, "left_outer")
      .drop(ratings.col("movieId"))
      .groupBy("movieId", "title", "genre")
      .agg(
        min("rating").as("minRating"),
        max("rating").as("maxRating"),
        avg("rating").as("avgRating")
      )

    moviesRatings
  }
}