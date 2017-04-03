package uk.co.matthewmcgowan

import akka.actor.{ActorRef, ActorSystem}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import org.scalatest.{BeforeAndAfterAll, FunSpecLike, Matchers}
import uk.co.matthewmcgowan.model.types._
import uk.co.matthewmcgowan.model.{KnownVoter, UnknownVoter}

import scala.util.Random

/**
  * Created by Matt on 02/04/2017.
  */
class AgeIncomePerceptronActorSpec extends TestKit(ActorSystem("AgeIncomePerceptronActorSpec")) with ImplicitSender
  with FunSpecLike with Matchers with BeforeAndAfterAll {
  describe("An AgeIncomePerceptronActor") {
    val ref = TestActorRef(new AgeIncomePerceptronActor)

    describe("when trained with a strong positive correlation") {
      describe("between Age and Conservatism") {
        it("should predict Conservative for a high Age") {
          sendToRefShuffled(oldConservativeData, ref, 200)

          val m = UnknownVoter(Sex.Male, 1, Location.Rural, 0.5)
          ref ! m

          expectMsg((m, PoliticalInclination.Conservative))
        }

        it("should predict Liberal for a low Age") {
          sendToRefShuffled(oldConservativeData, ref, 200)

          val m = UnknownVoter(Sex.Male, 0, Location.Rural, 0.5)
          ref ! m

          expectMsg((m, PoliticalInclination.Liberal))
        }
      }

      describe("between Income and Conservatism") {
        it("should predict Conservative for a high Income") {
          sendToRefShuffled(richConservativeData, ref, 200)

          val m = UnknownVoter(Sex.Male, 0.5, Location.Rural, 1)
          ref ! m

          expectMsg((m, PoliticalInclination.Conservative))
        }

        it("should predict Liberal for a low Income") {
          sendToRefShuffled(richConservativeData, ref, 200)

          val m = UnknownVoter(Sex.Male, 1, Location.Rural, 0)
          ref ! m

          expectMsg((m, PoliticalInclination.Liberal))
        }
      }
    }
  }

  val oldConservativeData: List[KnownVoter] = List(
    KnownVoter(Sex.Male, 1, Location.Rural, 0.5, PoliticalInclination.Conservative),
    KnownVoter(Sex.Male, 0, Location.Rural, 0.5, PoliticalInclination.Liberal))

  val richConservativeData: List[KnownVoter] = List(
    KnownVoter(Sex.Male, 0.5, Location.Rural, 1, PoliticalInclination.Conservative),
    KnownVoter(Sex.Male, 0.5, Location.Rural, 0, PoliticalInclination.Liberal))

  private def sendToRefShuffled(l: List[KnownVoter], ref: ActorRef, n: Int = 1): Unit = {
    val ext = List.fill(n)(l).flatten
    val extShuf = Random.shuffle(ext)
    extShuf.foreach(ref ! _)
  }
}
