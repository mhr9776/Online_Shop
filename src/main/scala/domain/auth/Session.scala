package domain.auth

case class Session(key: String, userID: Long, username: String)
