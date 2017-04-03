package uk.co.matthewmcgowan.model

import uk.co.matthewmcgowan.model.types.Location.Location
import uk.co.matthewmcgowan.model.types.Sex.Sex

/**
  * Created by Matt on 02/04/2017.
  */
case class UnknownVoter(sex: Sex,
                      age: Double,
                      location: Location,
                      income: Double)
  extends IndependentVoterVariable