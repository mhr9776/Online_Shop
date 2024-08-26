package core.useCase.store

import com.google.inject.{Inject, Singleton}
import contract.callback.auth.{PermissionCallback, SessionCallback, UserCallback, UserPermissionCallback}
import contract.callback.store.ProductCallback
import contract.service.store
import contract.service.store.RemoveProductService
import domain.auth.UserPermission

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class RemoveProductUseCase @Inject()(productCallback: ProductCallback, userCallback: UserCallback,
                                     userPermissionCallback: UserPermissionCallback) extends RemoveProductService {

  override def call(body: RemoveProductService.Body)(implicit ec: ExecutionContext): Future[Unit] = for {
    //    // Step 1: Check if the user is logged in and has a valid session
    //    session <- sessionCallback get body.userId
    //    _ = session getOrElse {
    //      Future.failed(new Exception("Invalid session key"))
    //    }

    // Step 2: Check if the user has the permission to remove an item
    hasPermission <- userPermissionCallback getByPermission(body.userId, UserPermission.Permission.Remove_Product)
    _ = hasPermission getOrElse {
      Future.failed(new Exception("User does not have permission to remove items"))
    }

    // Step 3: Check if the item exists in the database
    itemExists <- productCallback getByName body.productName
    _ = itemExists getOrElse {
      Future.failed(new Exception(s"Item with name '${body.productName}' does not exist"))
    }

    // Step 4: Remove the item from the database
    _ <- productCallback.removeByName(body.productName)

  } yield ()
}
