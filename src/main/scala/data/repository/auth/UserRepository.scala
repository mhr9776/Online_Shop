package data.repository.auth

import contract.callback.auth.UserCallback
import data.adaptor.auth.UserFactory
import domain.auth.User
import domain.auth.User.Role
import module.DatabaseModule
import module.DatabaseModule.onlineShop
import scalikejdbc.{NamedDB, scalikejdbcSQLInterpolationImplicitDef}

import scala.concurrent.Future

class UserRepository extends UserCallback with DatabaseModule {


  override def add(username: String, password: String, name: String, eMail: String, role: Role): Future[Long] =
    Future {
      NamedDB(onlineShop) localTx { implicit session =>
        sql"""
        INSERT INTO user(username,password,name,email,role)
        VALUES ($username,$password,$name,$eMail,$role )
      """.updateAndReturnGeneratedKey()
      }
    }

  override def get(id: Long): Future[Option[User]] = Future {
      NamedDB(onlineShop) readOnly { implicit session =>
        sql"""
        SELECT *
        FROM user
        WHERE id = $id
      """.map(UserFactory.user).single()
      }
    }

  override def getBy(username:String): Future[Option[User]] = Future {
    NamedDB(onlineShop) readOnly { implicit session =>
      sql"""
        SELECT *
        FROM user
        WHERE username = $username
      """.map(UserFactory.user).single()
    }
  }


  override def remove(id: Long): Future[Unit] = Future {
    NamedDB(onlineShop) localTx { implicit session =>
      sql"""
            Delete from user
            where id = $id
            """.update()
    }
  }

  override def update(
                       id: Long,
                       username: Option[String],
                       password: Option[String],
                       name: Option[String],
                       eMail: Option[String]): Future[Unit] = Future {
    NamedDB(onlineShop) localTx { implicit session =>
      sql"""
        UPDATE product
        SET
          username = $username,
          password = $password,
          name = $name,
          email = $eMail
        WHERE id = $id
      """.update()
    }
  }
}
