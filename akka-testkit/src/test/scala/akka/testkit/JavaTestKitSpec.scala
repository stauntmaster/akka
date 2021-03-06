package akka.testkit

import language.postfixOps

import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers
import org.scalatest.{ BeforeAndAfterEach, WordSpec }
import akka.actor._
import scala.concurrent.{ Future, Await }
import scala.concurrent.duration._
import akka.pattern.ask

@org.junit.runner.RunWith(classOf[org.scalatest.junit.JUnitRunner])
class JavaTestKitSpec extends AkkaSpec with DefaultTimeout {

  "JavaTestKit" must {

    "be able to receiveN messages" in {
      new JavaTestKit(system) {
        val sent = List(1, 2, 3, 4, 5)
        for (m ← sent) { getRef() ! m }
        val received = receiveN(sent.size, 5 seconds);
        sent.toSet must be(received.toSet)
      }
    }

    "be able to receiveN messages with default duration" in {
      new JavaTestKit(system) {
        val sent = List(1, 2, 3)
        for (m ← sent) { getRef() ! m }
        val received = receiveN(sent.size);
        sent.toSet must be(received.toSet)
      }
    }

  }

}
