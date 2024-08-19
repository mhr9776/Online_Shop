package core.usecase.product

import com.google.inject.{Inject, Singleton}
import contract.callback.auth.{UserCallback, UserPermissionCallback}
import contract.service.auth.SignUpService
import domain.auth.{User, UserPermission}
import util.AuthUtils

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SignUpUseCase @Inject()(userCallback: UserCallback, userPermissionCallback: UserPermissionCallback) extends SignUpService {

  override def call(body: SignUpService.Body)(implicit ec: ExecutionContext): Future[User] = {
    // Persist hashed password
    val hashedPassword = AuthUtils hashPassword body.password

    for {
      // Check username availability
      previousUserOption <- userCallback getBy body.username
      _ <- previousUserOption match {
        case None => Future.unit
        case Some(previousUser) => Future failed new Exception(s"This username is taken: ${previousUser.username}")
      }

      // Create user
      userID <- userCallback.add(body.username, hashedPassword, body.name, body.eMail, body.role)
      _ <- body.role match {
        case User.Role.SELLER => userPermissionCallback.addBatch(userID, Vector(UserPermission.Permission.Add_Product,
          UserPermission.Permission.Remove_Product, UserPermission.Permission.Update_Product,
          UserPermission.Permission.Get_Product))
        case User.Role.CUSTOMER => userPermissionCallback.addBatch(userID,Vector(UserPermission.Permission.Add_To_Order))
      }
      createdUser <- userCallback.get(userID)
    }yield createdUser getOrElse (throw new Exception("not found"))
  }


}
