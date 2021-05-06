package uk.co.newday.settings

import org.scalatest.FunSuite

/**
 * Test suite for [[RatingsSettings]]
 */
class RatingsSettingsTests extends FunSuite{

  test("default values after init"){
    val settings = RatingsSettings
    assert(settings.source == Some("ratings.dat"))
    assert(settings.format == Some("csv"))
    assert(settings.delimiter == Some("::"))
    assert(settings.path == Some("src/main/resources"))
    assert(settings.header == Some(false))
  }
}
