package uk.co.newday.solutions

import org.apache.spark.sql.DataFrame
import org.scalatest.FunSuite
import uk.co.newday.settings.{Configurable, MovieSettings, RatingsSettings}

/**
 * Test suite for [[Exercise1Candidate]]
 */
class Exercise1CandidateTests extends FunSuite {

  test("dataframes row count matches expected row counts"){
    val settings: (Configurable, Configurable) = (MovieSettings, RatingsSettings)
    val dataFrames: (DataFrame, DataFrame) = Exercise1Candidate.execute(settings)

    assert(dataFrames._1.count() == 3883)
    assert(dataFrames._2.count() == 1000209)
  }
}
