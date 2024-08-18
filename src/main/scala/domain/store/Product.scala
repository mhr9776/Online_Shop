package domain.store

case class Product(
                    id: Long,
                    name: String,
                    details: String,
                    price: BigDecimal,
                    inventory: Int
                  )
