package contract.service.store

import contract.service.Service

abstract class UpdateProductService extends Service[UpdateProductService.Body, Unit]

object UpdateProductService {

  case class Body(userId: Long, productName: String, details: Option[String], price: Option[BigDecimal], inventory: Option[Int])

}
