package core.usecase.product

import com.google.inject.{Inject, Singleton}
import contract.callback.auth.{PermissionCallback, SessionCallback, UserCallback}
import contract.callback.store.ProductCallback
import contract.service.store
import contract.service.store.RemoveProductService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class RemoveProductUseCase @Inject()(productCallback: ProductCallback, userCallback: UserCallback, permissionCallback: PermissionCallback, sessionCallback: SessionCallback) extends RemoveProductService {

  override def call(body: RemoveProductService.Body)(implicit ec: ExecutionContext): Future[Unit] = for {
    // Step 1: Check if the user is logged in and has a valid session
    session <- sessionCallback get body.userId
    _ = session getOrElse {
      Future.failed(new Exception("Invalid session key"))
    }

    // Step 2: Check if the user has the permission to remove an item
    hasPermission <- permissionCallback get (body.userId, Permission.Remove_Product)
    _ = hasPermission getOrElse {
      Future.failed(new Exception("User does not have permission to remove items"))
    }

    // Step 3: Check if the item exists in the database
    itemExists <- productCallback getByName body.productName
    _ = itemExists getOrElse {
      Future.failed(new Exception(s"Item with name '${body.productName}' does not exist"))
    }

    // Step 4: Remove the item from the database
    removed <- productCallback.removeByName(body.productName)
    _ = removed getOrElse {Future.failed(new Exception(s"Failed to remove the item with name ${body.productName}"))
    }
  } yield removed
}