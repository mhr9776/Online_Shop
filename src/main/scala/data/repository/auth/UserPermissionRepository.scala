package data.repository.auth

import com.twitter.finagle.http.filter.LoggingFilter.formatter
import contract.callback.auth.UserPermissionCallback
import data.Utils.SQLContext
import data.adaptor.auth.UserPermissionFactory
import domain.auth.UserPermission
import module.DatabaseModule
import module.DatabaseModule.onlineShop
import scalikejdbc.{NamedDB, SQLSyntax, scalikejdbcSQLInterpolationImplicitDef}

import java.time.ZonedDateTime
import scala.concurrent.Future

class UserPermissionRepository extends UserPermissionCallback with DatabaseModule {
  override def get(UserId: Long): Future[Option[UserPermission]] =Future {
    NamedDB(onlineShop) readOnly { implicit session =>
      sql"""
        SELECT *
        FROM user_permission
        WHERE user_id = $UserId
      """.map(UserPermissionFactory.userPermission).single()
    }
  }

  override def getByPermission(UserId: Long, permissionId: Long): Future[Option[UserPermission]] = Future {
    NamedDB(onlineShop) readOnly { implicit session =>
      sql"""
        SELECT *
        FROM user_permission
        WHERE user_id,permission_id = $UserId,$permissionId
      """.map(UserPermissionFactory.userPermission).single()
    }
  }

  override def addBatch(userId: Long, permissionId: Vector[Long]): Future[Int] = {
    if (permissionId.isEmpty) Future.successful(0) else Future{
      val permissionIdQL =ql"$permissionId"
      NamedDB(onlineShop) localTx { implicit session =>
        sql"""
          INSERT INTO user_permission(user_id,permission_id)
            (
            SELECT $userId, permission_id
            FROM UNNEST($permissionIdQL) AS data(permission_id)
            )
          """.update()

      }
    }
  }
}
