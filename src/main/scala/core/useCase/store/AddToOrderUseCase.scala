package core.useCase.store

import com.google.inject.Inject
import contract.callback.auth.UserPermissionCallback
import contract.callback.store.{OrderCallback, ProductCallback}
import contract.service.store.AddToOrderService
import domain.auth.UserPermission
import domain.store.Order

import scala.concurrent.{ExecutionContext, Future}


class AddToOrderUseCase @Inject()(productCallback: ProductCallback, userPermissionCallback: UserPermissionCallback,
                                  orderCallback: OrderCallback) extends AddToOrderService {

  override def call(body: AddToOrderService.Body)(implicit ec: ExecutionContext): Future[Vector[Order]] = for {

    // Step 1: Check if the user has the permission to add a product
    permissionOption <- userPermissionCallback getByPermission(body.userId, UserPermission.Permission.Add_To_Order)
    _ = permissionOption getOrElse {
      Future.failed(throw new Exception("User does not have permission to add to order any product"))
    }

    // Step 2: Check if the product name exists in the database
    //      productOption <- productCallback getByName body.productName.head.productName
    productOption <- Future sequence body.productName.map(productCallback.getByName)
    products = productOption map {
      case Some(value) => value
      case None => throw new Exception(s"Product with name does not exists")
    }

    _ = if (productOption.isEmpty) new Exception(s"Product with name '${body.productName.head.productName}' does not exists")


    // step 3: check if the product is in stock
    _ = if (productOption.get.inventory < body.quantity) new Exception(s"Product with name '${body.productName.head.productName}' is not in stock")

    //step 4: add to order
    _
      <- orderCallback.add(body.userId, body.productName, body.quantity)



    // step 5: update inventory
    _
      <- productCallback.update(body.productName.head.productName, inventory = Some(productOption.get.inventory - body.quantity))

    // step 6: return the updated order
    order
      <- orderCallback.get(body.userId)

  }

  yield order
}


