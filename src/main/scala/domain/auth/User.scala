package domain.auth

import java.security.Permission
import java.time.ZonedDateTime


case class User(
                 id: Long,
                 username: String,
                 password: String,
                 name: String,
                 eMail: String,
                 role: User.Role,
                 /*signUpAt: ZonedDateTime*/
               ) {

  def setEMail(eMail: String): User = {
    copy(eMail = eMail)
  }

  def setName(name: String): User = {
    copy(name = name)
  }

  def setPassword(password: String): User = {
    copy(password = password)
  }

}
  object User {

    type Role = String

    object Role {

      val SELLER: Role = "Seller"
      val CUSTOMER: Role = "Customer"

    }


  }

