package contract.service.store

import contract.service.Service
import domain.store.Order

abstract class UpdateOrderService extends Service[UpdateOrderService.Body,Order]

object UpdateOrderService{

  case class Body(userId : Long,productId : Long,quantity : Int)
}
