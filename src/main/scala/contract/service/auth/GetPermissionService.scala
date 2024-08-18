package contract.service.auth

import contract.service.Service

import java.security.Permission

abstract class GetPermissionService extends Service[GetPermissionService.Body,Permission]

object GetPermissionService{

  case class Body(userId : Long)
}
