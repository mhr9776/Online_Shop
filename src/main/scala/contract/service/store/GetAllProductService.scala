package contract.service.store

import contract.service.Service
import domain.store.Product

abstract class GetAllProductService extends Service[GetAllProductService.Body, Vector[Product]]

object GetAllProductService {

  case class Body(userId: Long)

}
