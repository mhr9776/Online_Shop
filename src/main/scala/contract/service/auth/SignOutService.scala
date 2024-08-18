package contract.service.auth

import contract.service.Service

abstract class SignOutService extends Service[SignOutService.Body, Unit]

object SignOutService {

  case class Body(key: String)

}
