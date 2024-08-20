package entry.rest.finatra.adapter.store.api

case class ProductDTO(id: Long,
                      name: String,
                      details: String,
                      price: BigDecimal,
                      inventory: Int)
