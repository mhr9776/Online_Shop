package core.usecase.product

import com.google.inject.Inject
import contract.callback.auth.UserPermissionCallback
import contract.callback.store.ProductCallback
import contract.service.store.AddProductService
import domain.auth.UserPermission

import scala.Option.option2Iterable
import scala.concurrent.{ExecutionContext, Future}

class AddProductUseCase @Inject()(
                                   productCallback: ProductCallback, userPermissionCallback: UserPermissionCallback)
  extends AddProductService {

  override def call(body: AddProductService.Body)(implicit ec: ExecutionContext): Future[Product] = for {

    // Step 1: Check if the user has the permission to add a product
    permissionOption <- userPermissionCallback getByPermission (body.userId, UserPermission.Permission.Add_Product)
    _ = permissionOption getOrElse {
      Future.failed(new Exception("User does not have permission to add products"))
    }

    // Step 2: Check if the product name already exists in the database
    productOption <- productCallback getByName body.name
    _ = if(productOption.nonEmpty) new Exception(s"Product with name '${body.name}' already exists")



    // Step 3: Add the new product to the database
    productId <- productCallback add(body.name, body.details, body.price, body.inventory)
    product <- productCallback.get(productId)
  } yield product
    }
