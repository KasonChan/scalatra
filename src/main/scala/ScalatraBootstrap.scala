import com.kasonchan.routes.{ Routes, ActorsRoutes }
import com.kasonchan.actors.Worker
import _root_.akka.actor.{ Props, ActorSystem }
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {

  val actors = ActorSystem("actors")
  val w = actors.actorOf(Props[Worker])

  override def init(context: ServletContext) {
    // Context mount to different routes
    context.mount(new Routes, "/*")
    // When url starting with actors is requested, context will mount to the
    // ActorsRoutes class
    context.mount(new ActorsRoutes(actors, w), "/actors/*")
  }

  override def destroy(context: ServletContext) {
    // When the servlet is closed, shutdown actor system
    actors.shutdown()
  }

}
