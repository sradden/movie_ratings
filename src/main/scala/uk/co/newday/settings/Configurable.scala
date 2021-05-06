package uk.co.newday.settings

/**
 * Case class for configuration settings
 * @param source the name of the file to load
 * @param path the path to the file
 * @param format the format of the file
 * @param header true if the file has a header row, else false
 * @param delimiter the column delimiter in the file
 */
case class Configurable (val source: Option[String] = None,
                         val path: Option[String] = Some("src/main/resources"),
                         val format: Option[String] = Some("csv"),
                         val header: Option[Boolean] = Some(false),
                         val delimiter: Option[String] = Some("::")
                        )
{
  /**
   *
   * @param source the name of the file to load
   * @return a new [[Configurable]] with the specified source
   */
  def withSource(source: String): Configurable = this.copy(source = Some(source))

  /**
   *
   * @param path the location to load from
   * @return a new [[Configurable]] with the specified path
   */
  def withPath(path: String): Configurable = this.copy(path = Some(path))

  /**
   *
   * @param format the format of the source
   * @return a new [[Configurable]] with the specified format
   */
  def withFormat(format: String): Configurable = this.copy(format = Some(format))

  /**
   *
   * @param header true if source has a header, else false
   * @return a new [[Configurable]] with the specified header
   */
  def withHeader(header: Boolean): Configurable = this.copy(header = Some(header))

  /**
   *
   * @param delimiter the column delimiter in the source
   * @return a new [[Configurable]] with the specified delimiter
   */
  def withDelimiter(delimiter: String): Configurable = this.copy(delimiter = Some(delimiter))
}
