package contract.service.auth

import contract.service.Service
import domain.auth.UserPermission

import java.security.Permission

abstract class GetUserPermissionsService extends Service[GetUserPermissionsService.Body, Vector[UserPermission]]

object GetUserPermissionsService {

  case class Body(id: Long)

}