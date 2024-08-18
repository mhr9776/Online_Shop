package domain.store

import java.util.Date

case class Order(
                  id: String,
                  userId: Long,
                  products: List[Product],
                  totalAmount: Double,
                  orderDate: Date
                )

