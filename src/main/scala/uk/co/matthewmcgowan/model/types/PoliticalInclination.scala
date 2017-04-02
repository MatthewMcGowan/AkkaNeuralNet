package uk.co.matthewmcgowan.model.types

/**
  * Created by Matt on 02/04/2017.
  */
object PoliticalInclination {
  sealed abstract class PoliticalInclination(val encoding: (Int, Int, Int))

  // Dependent variable, so 1-of-N dummy encoding used
  case object Liberal extends PoliticalInclination((1, 0, 0))
  case object Moderate extends PoliticalInclination((0, 1, 0))
  case object Conservative extends PoliticalInclination((0, 0, 1))
}
