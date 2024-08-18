package core.usecase.product

import com.google.inject.Inject
import contract.callback.auth.{PermissionCallback, SessionCallback}
import contract.callback.store.ProductCallback
import contract.service.store.UpdateProductService

import scala.concurrent.{ExecutionContext, Future}

class UpdateProductUseCase @Inject()(productCallback: ProductCallback, permissionCallback: PermissionCallback, sessionCallback: SessionCallback) extends UpdateProductService {

  override def call(body: UpdateProductService.Body)(implicit ec: ExecutionContext): Future[Unit] = for {
    // Step 1: Check if the user is logged in and has a valid session
    sessionOption <- sessionCallback get body.userId
    _ = sessionOption getOrElse {
      Future.failed(new Exception("Invalid session key"))
    }

    // Step 2: Check if the user has the permission to add a product
    permissionOption <- permissionCallback get(body.userId, Permission.Update_Product)
    _ = permissionOption getOrElse {
      Future.failed(new Exception("User does not have permission to update"))
    }

    // Step 3: Check if the product name already exists in the database
    productOption <- productCallback getByName body.productName
    _ = productOption getOrElse{
      Future.failed(new Exception(s"Product with name '${body.productName}' is not exists"))
    }


    // Step 4: Add the new product to the database
    productUpdate <- productCallback update (body.productName, body.inventory, body.details, body.price)
  } yield ()

}