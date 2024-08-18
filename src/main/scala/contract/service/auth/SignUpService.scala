package contract.service.auth

import contract.service.Service
import domain.auth.User


abstract class SignUpService extends Service[SignUpService.Body, User]

object SignUpService {

  case class Body(username: String, password: String, name: String, eMail: String, role :User.Role )

}
