package contract.service.store

import contract.service.Service

abstract class RemoveProductService extends Service [RemoveProductService.Body, Unit]

object RemoveProductService {

  case class Body(userId: Long,productName: String)

}
