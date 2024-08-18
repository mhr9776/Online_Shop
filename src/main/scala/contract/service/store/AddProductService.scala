package contract.service.store

import contract.service.Service

abstract class AddProductService extends Service[AddProductService.Body, Product]

object AddProductService {

  case class Body(userId: Long,details: String,name : String, price: BigDecimal, inventory: Int)

}
