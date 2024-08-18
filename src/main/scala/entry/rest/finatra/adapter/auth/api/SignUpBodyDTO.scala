package entry.rest.finatra.adapter.auth.api


case class SignUpBodyDTO(username: String, password: String, name: String, eMail: String, role :String)
