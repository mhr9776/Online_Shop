package data.repository.auth

import contract.callback.auth.UserPermissionCallback
import module.DatabaseModule
import scalikejdbc.NamedDB

import scala.concurrent.Future

class UserPermissionRepository extends UserPermissionCallback with DatabaseModule {
  override def get(UserId: Long): Future[Vector[Permission]] = ???

  override def getByPermission(UserId: Long, permissionId: Long): Future[Option[Permission]] = ???

  override def addBatch(userId: Long, permissionId: Vector[Long]): Future[Int] =(
  userId: Long,
  permissionId: Vector[Long]
  ): Future[Int] = {
    If(entityIDs.isEmpty) `then` 0 elseDo Future {
      val entityIDsQL = ql"$entityIDs"
      val propertyIDsQL = ql"$propertyIDs"
      val valuesQL = ql"$values"
      NamedDB(webDB) localTx { implicit session =>
        sql"""
          INSERT INTO tag.tag(entity_id, property_id, value, list, user_id_of_last_action, addition_time, last_action_time)
            (
            SELECT entity_id, property_id, value, $list, $userIDOfLastAction, $time, $time
            FROM UNNEST($entityIDsQL, $propertyIDsQL, $valuesQL) AS data(entity_id, property_id, value)
            )
          """.update()
      }
    }
  }
}