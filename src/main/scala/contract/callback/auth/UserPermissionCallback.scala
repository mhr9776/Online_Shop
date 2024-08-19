package contract.callback.auth

import domain.auth.UserPermission

import scala.concurrent.Future

abstract class UserPermissionCallback {

  def get(UserId: Long) : Future[Option[UserPermission]]

  def getByPermission(UserId: Long, permissionId: Long): Future[Option[UserPermission]]

  def addBatch(userId:Long,permissionId: Vector[Long]):Future[Int]

}
