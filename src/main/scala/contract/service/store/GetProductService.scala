package contract.service.store

import contract.service.Service

abstract class GetProductService extends Service[GetProductService.Body, Product]

object GetProductService{

  case class Body(productID: Long)
}
