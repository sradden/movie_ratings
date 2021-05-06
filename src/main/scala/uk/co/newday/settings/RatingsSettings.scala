package uk.co.newday.settings

/**
 * Provides run-time settings for loading movie ratings
 */
object RatingsSettings extends Configurable(source = Some("ratings.dat"))