package uk.co.newday.solutions

import org.apache.spark.sql.functions.{col, lit}
import org.scalatest.FunSuite
import uk.co.newday.settings.{MovieSettings, RatingsSettings}

class Exercise3CandidateTests extends FunSuite {

  test("top 3 films for userId 5"){
    val settings = (MovieSettings, RatingsSettings)
    val dataFrames = Exercise1Candidate.execute(settings)
    val top3 = Exercise3Candidate.execute(dataFrames._1, dataFrames._2)
    val userId5Top3 = top3.filter(col("userId") === lit(5))
    val rows = userId5Top3.collect()

    assert(rows(0)(1) == 29 && rows(0)(2) ==5)
    assert(rows(1)(1) == 50 && rows(1)(2) ==5)
    assert(rows(2)(1) == 913 && rows(2)(2) ==5)
  }
}
