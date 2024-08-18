package contract.callback.auth

import java.security.Permission
import scala.concurrent.Future

abstract class PermissionCallback {

  def get(userId:Long,permissionId:Long) :Future[Option[Permission]]

  def getAll : Future[Vector[Permission]]
}
