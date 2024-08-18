package contract.service.auth

import contract.service.Service
import domain.auth.Session

abstract class AuthorizeService extends Service[AuthorizeService.Body, Unit]

object AuthorizeService {

  case class Body(session: Session)

}
