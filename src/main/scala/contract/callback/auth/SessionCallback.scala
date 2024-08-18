package contract.callback.auth

import domain.auth.Session

import scala.concurrent.Future

abstract class SessionCallback {

  def add(key: String, userID: Long, username: String): Future[Session]

  def get(key: String): Future[Option[Session]]

  def remove(key: String): Future[Unit]

}