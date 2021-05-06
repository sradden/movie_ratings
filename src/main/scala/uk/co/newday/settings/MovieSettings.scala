package uk.co.newday.settings

/**
 * Specifies run-time settings for loading movies
 */
object MovieSettings extends Configurable(source = Some("movies.dat"), format = Some("csv"))