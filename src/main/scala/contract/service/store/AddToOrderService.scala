package contract.service.store

import contract.service.Service
import domain.store.{Order, ProductInOrder}
import entry.rest.finatra.adapter.store.api.ProductInorderDTO

abstract class AddToOrderService extends Service[AddToOrderService.Body,Vector[Order]]

object AddToOrderService{

  case class Body(userId : Long, productName : Vector[String], quantity : Int)
}


