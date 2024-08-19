package entry.rest.finatra.adapter.auth.api

case class UserDTO(
                    id: Long,
                    username: String,
                    password: String,
                    name: String,
                    eMail: String,
                    role: String
                  )
