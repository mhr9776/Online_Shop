package contract.service.store

import contract.service.Service

abstract class GetAllProductService extends Service[GetAllProductService.Body, List[Product]]

object GetAllProductService {

  case class Body()

}
