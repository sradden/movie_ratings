package uk.co.newday.settings

import org.scalatest.FunSuite

class MovieSettingsTests extends FunSuite {

  test("Default values after init"){
    val settings = MovieSettings
    assert(settings.source == Some("movies.dat"))
    assert(settings.format == Some("csv"))
    assert(settings.delimiter == Some("::"))
    assert(settings.path == Some("src/main/resources"))
    assert(settings.header == Some(false))
  }
}
