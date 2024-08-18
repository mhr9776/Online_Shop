package contract.service.store

import contract.service.Service
import domain.store.Order

abstract class AddToOrderService extends Service[AddToOrderService.Body,Order]

object AddToOrderService{

  case class Body(userId : Long,productId : Long, quantity : Int)
}


