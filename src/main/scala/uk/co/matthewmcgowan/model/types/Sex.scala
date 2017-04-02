package uk.co.matthewmcgowan.model.types

/**
  * Created by Matt on 02/04/2017.
  */
object Sex  {
  sealed abstract class Sex(val encoding: Int)

  case object Male extends Sex(1)
  case object Female extends Sex(-1)
}
