package uk.co.newday.solutions

import org.apache.spark.sql.functions.{col, lit}
import org.scalatest.FunSuite
import uk.co.newday.settings.{MovieSettings, RatingsSettings}

class Exercise2CandidateTests extends FunSuite {

  test("min, max and avg for a movie"){

    val settings = (MovieSettings, RatingsSettings)
    val dataFrames = Exercise1Candidate.execute(settings)
    val moviesRatings = Exercise2Candidate.execute(dataFrames._1, dataFrames._2)
    val dirtyDancing = moviesRatings.filter(col("movieId") === lit(1088))

    val rows = dirtyDancing.collect()(0)
    assert(rows(3) == 1 && rows(4) == 5 && rows(5) == 3.3114992721979624)
  }
}
