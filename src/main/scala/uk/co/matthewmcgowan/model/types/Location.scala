package uk.co.matthewmcgowan.model.types

/**
  * Created by Matt on 02/04/2017.
  */
object Location {
  sealed abstract class Location(val encoding: (Int, Int))

  // Independent variable, so used 1-of-(N-1) encoding
  case object Rural extends Location((0, 1))
  case object Suburban extends Location((1, 0))
  case object Urban extends Location((-1, -1))
}
