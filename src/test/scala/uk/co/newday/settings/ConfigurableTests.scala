package uk.co.newday.settings

import org.scalatest.FunSuite

class ConfigurableTests extends FunSuite{

  test("default values assigned"){
    val configurable = new Configurable
    assert(configurable.source == None && configurable.path == Some("src/main/resources") && configurable.format == Some("csv") &&
    configurable.header == Some(false) && configurable.delimiter == Some("::"))
  }

  test("values are assigned during object instantiation") {
    val configurable = new Configurable(source = Some("A"), path = Some("/tmp/"), format = Some("parquet"),
      header = Some(true), delimiter = Some(";"))

    assert(configurable.source == Some("A") && configurable.path == Some("/tmp/") && configurable.format == Some("parquet") &&
      configurable.header == Some(true) && configurable.delimiter== Some(";"))
  }

  test("values can be reassigned after object instantiation"){
    val configurable = new Configurable
    assert(configurable.withSource("B").source == Some("B") && configurable.withPath("/tmp/").path == Some("/tmp/")
      && configurable.withFormat("csv").format == Some("csv") && configurable.withHeader(false).header == Some(false)
      && configurable.withDelimiter(",").delimiter == Some(","))
  }
}
