package uk.co.newday

import org.apache.spark.sql.DataFrame
import uk.co.newday.settings.{Configurable}
import uk.co.newday.solutions._
object MoviesRatingsCandidate extends App {

  def main(args: (Configurable, Configurable)) = {

    val (movies, ratings): (DataFrame,DataFrame) = solutions.Exercise1Candidate.execute(args)
    val movieRatings: DataFrame = Exercise2Candidate.execute(movies, ratings)
    val ratingWithRankTop3: DataFrame = Exercise3Candidate.execute(movies, ratings)
    Exercise4Candidate.execute(movies, ratings, movieRatings, ratingWithRankTop3)
  }
}
