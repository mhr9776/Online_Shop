package data.repository.auth

import com.google.inject.Singleton
import contract.callback.auth.SessionCallback
import domain.auth.Session
import module.SimpleInMemoryModule

import scala.concurrent.Future

@Singleton
class SessionRepository extends SessionCallback with SimpleInMemoryModule[Session] {

  override val name: String = "Session"

  override def add(key: String, userID: Long, username: String): Future[Session] = {
    val session = Session(key, userID, username)
    addToMemory(session).map(_ => session)
  }

  override def get(key: String): Future[Option[Session]] = Future {
    data.find(_.key == key)
  }

  override def remove(key: String): Future[Unit] = {
    removeFromMemory(_.key == key)
  }

}