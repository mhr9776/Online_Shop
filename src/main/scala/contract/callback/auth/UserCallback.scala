package contract.callback.auth

import domain.auth.User

import scala.concurrent.Future

abstract class UserCallback {

  def add(username: String, password: String, name: String, eMail: String, role: User.Role): Future[Long]

  def get(id: Long): Future[Option[User]]

  def getBy(username:String) : Future[Option[User]]

  def remove(id: Long): Future[Unit]

  def update(
              id: Long,
              username: Option[String] = None,
              password: Option[String] = None,
              name: Option[String] = None,
              eMail: Option[String] = None
            ): Future[Unit]


}
