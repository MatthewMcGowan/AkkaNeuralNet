package uk.co.matthewmcgowan

import akka.actor.Actor
import uk.co.matthewmcgowan.model.types.PoliticalInclination
import uk.co.matthewmcgowan.model.types.PoliticalInclination.PoliticalInclination
import uk.co.matthewmcgowan.model.{IndependentVoterVariable, KnownVoter}

import scala.util.Random

/**
  * Created by Matt on 02/04/2017.
  */
class AgeIncomePerceptronActor extends Actor {
  private var weights = getInitialWeights
  private val trainingAlpha = 0.1

  override def receive: Receive = {
    case m: KnownVoter => weights = TrainWeights(m, weights)
    case m: IndependentVoterVariable => sender() ! (m, predictedValue(m))
  }

  def TrainWeights(m: KnownVoter, w: Weights): Weights = {
    def adj(input: Double, delta: Double): Double = trainingAlpha * delta * input
    val delta = predictedValue(m).encoding - m.politicalInclination.encoding
    Weights(
      w.first - adj(m.age, delta),
      w.second - adj(m.income, delta),
      w.bias - adj(1, delta)
    )
  }

  def predictedValue(m: IndependentVoterVariable): PoliticalInclination = {
    val r = weights.first * m.age + weights.second * m.income + weights.bias
    PoliticalInclination.fromEncodedDouble(r)
  }

  private def getInitialWeights: Weights = {
    val hi = 0.01
    val lo = -0.01
    def genWeight() = (hi - lo) * Random.nextDouble() + lo

    Weights(genWeight(), genWeight(), genWeight())
  }

  case class Weights(first: Double, second: Double, bias: Double)
}

