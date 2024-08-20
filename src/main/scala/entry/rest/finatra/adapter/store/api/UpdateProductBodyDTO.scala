package entry.rest.finatra.adapter.store.api

case class UpdateProductBodyDTO(productName: String, details: Option[String], price: Option[BigDecimal], inventory: Option[Int])
