package contract.service.auth

import contract.service.Service
import domain.auth.{Session, User}

abstract class SignInService extends Service[SignInService.Body, Session]

object SignInService {

  case class Body(username: String, password: String)

}


