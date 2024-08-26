package core.useCase.store

import com.google.inject.Inject
import contract.callback.auth.UserPermissionCallback
import contract.callback.store.ProductCallback
import contract.service.store
import contract.service.store.GetAllProductService
import domain.auth.UserPermission
import domain.store.Product

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class GetAllProductUseCase @Inject()(productCallback: ProductCallback, userPermissionCallback: UserPermissionCallback) extends GetAllProductService {

  override def call(body:GetAllProductService.Body)(implicit ec: ExecutionContext): Future[Vector[Product]] = for {

    // Step 1: Check if the user has the permission to add a product
    permissionOption <- userPermissionCallback getByPermission (body.userId, UserPermission.Permission.Get_All_Product)
    _ = permissionOption getOrElse {
      Future.failed(new Exception("User does not have permission to see products"))
    }
    // Step 3: Add the new product to the database
    product <- productCallback getAll

  } yield product
}




