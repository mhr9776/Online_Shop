package entry.rest.finatra.adapter.store.api

import domain.store.ProductInOrder

case class AddToOrderBodyDTO(productName: Vector[String], quantity: Int)
