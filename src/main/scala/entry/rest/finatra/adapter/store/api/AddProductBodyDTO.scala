package entry.rest.finatra.adapter.store.api

case class AddProductBodyDTO(name : String,details: String, price: BigDecimal, inventory: Int)
