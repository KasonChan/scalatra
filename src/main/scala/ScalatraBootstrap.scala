import com.mongodb.casbah.Imports._
import com.kasonchan.routes.{ Routes, ActorsRoutes }
import com.kasonchan.actors.Worker
import com.kasonchan.controllers.MongoController
import _root_.akka.actor.{ Props, ActorSystem }
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {

  val actors = ActorSystem("actors")
  val w = actors.actorOf(Props[Worker])

  // val uri = MongoClientURI("mongodb://playchatReadWrite:readWritePlaychat@45.55.86.67:27017/?authMechanism=MONGODB-CR")
  // val mongoClient = MongoClient(uri)

  val server = new ServerAddress("45.55.86.67", 27017)
  val credentials = MongoCredential.createMongoCRCredential("playchatReadWrite", 
    "playchat", 
    Array('r','e','a','d','W','r','i','t','e','P','l','a','y','c','h','a','t'))
  val mongoClient = MongoClient(server, List(credentials))

  val mongoDB = mongoClient("playchat")
  val mongoCollUsers = mongoDB("users")
  val mongoCollRooms = mongoDB("rooms")

  override def init(context: ServletContext) {
    // Context mount to different routes
    context.mount(new Routes, "/*")
    // When url starting with actors is requested, context will mount to the
    // ActorsRoutes class
    context.mount(new ActorsRoutes(actors, w), "/actors/*")
    // Mongodb
    context.mount(new MongoController(mongoCollUsers), "/mongo/users/*")
    context.mount(new MongoController(mongoCollRooms), "/mongo/rooms/*")
  }

  override def destroy(context: ServletContext) {
    // When the servlet is closed, shutdown actor system
    actors.shutdown()
  }

}
