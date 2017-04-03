package uk.co.matthewmcgowan.model.types

/**
  * Created by Matt on 02/04/2017.
  */
object PoliticalInclination {
  sealed abstract class PoliticalInclination(val encoding: Int)

  case object Liberal extends PoliticalInclination(1)
  case object Conservative extends PoliticalInclination(-1)

  def fromEncodedDouble(d: Double): PoliticalInclination = {
    if (d > 0) PoliticalInclination.Liberal
    else PoliticalInclination.Conservative
  }
}
