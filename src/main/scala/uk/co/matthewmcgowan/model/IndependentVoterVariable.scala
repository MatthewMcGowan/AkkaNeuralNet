package uk.co.matthewmcgowan.model

import uk.co.matthewmcgowan.model.types.Location.Location
import uk.co.matthewmcgowan.model.types.Sex.Sex

/**
  * Created by Matt on 02/04/2017.
  */
trait IndependentVoterVariable {
  val sex: Sex
  val age: Age
  val location: Location
  val income: Double

  type Age = Double
}