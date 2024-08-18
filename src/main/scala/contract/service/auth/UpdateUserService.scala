package contract.service.auth

import contract.service.Service
import domain.auth.User

abstract class UpdateUserService extends Service[UpdateUserService.Body, User]

object UpdateUserService {

  case class Body(password: String,eMail: String)

}


