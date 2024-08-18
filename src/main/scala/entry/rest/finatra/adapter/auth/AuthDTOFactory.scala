package entry.rest.finatra.adapter.auth

import domain.auth.{Session, User}
import entry.rest.finatra.adapter.auth.api.{SessionDTO, UserDTO}

object AuthDTOFactory {

  def session: Session => SessionDTO = o =>
    SessionDTO(o.key, o.userID, o.username)

  def user: User => UserDTO = o =>
    UserDTO(o.id, o.username, o.password, o.name, o.eMail, o.role, CommonDTOFactory zonedDateTime o.signUpAt)

}
