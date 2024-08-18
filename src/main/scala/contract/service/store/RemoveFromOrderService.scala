package contract.service.store

import contract.service.Service
import domain.store.Order

abstract class RemoveFromOrderService extends Service[RemoveFromOrderService.Body,Order]

object RemoveFromOrderService{

  case class Body(userId : Long,productId : Long)
}
