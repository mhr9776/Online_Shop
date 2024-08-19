package data.adaptor.auth

import domain.auth.UserPermission
import scalikejdbc.WrappedResultSet

object UserPermissionFactory {

  def userPermission(dto: WrappedResultSet): UserPermission =
    UserPermission(
      dto long "user_id", dto long  "permission_id")


}
