package contract.service.store

import contract.service.Service
import domain.store.Order

abstract class GetOrderService extends Service[GetOrderService.Body,Order]

object GetOrderService{

  case class Body(userId : Long)
}


