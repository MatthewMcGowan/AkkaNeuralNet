package uk.co.matthewmcgowan.model

import uk.co.matthewmcgowan.model.types.PoliticalInclination.PoliticalInclination

/**
  * Created by Matt on 02/04/2017.
  */
trait DependentVoterVariable {
  val politicalInclination: PoliticalInclination
}
