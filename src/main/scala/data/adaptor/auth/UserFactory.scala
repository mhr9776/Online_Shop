package data.adaptor.auth

import domain.auth.User
import scalikejdbc.WrappedResultSet

object UserFactory {

  def user(dto: WrappedResultSet): User =
    User(
      dto long "id", dto string "username", dto string "password", dto string "name", dto string "eMail", dto string "role"/*, dto zonedDateTime "signUpAt"*/)

}
