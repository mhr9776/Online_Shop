package domain.store

import java.time.ZonedDateTime
import java.util.Date

case class Order(
                  id: Long,
                  userId: Long,
                  products: Vector[ProductInOrder]
                ){

  lazy val totalAmount: Long = 1
  lazy val orderDate: ZonedDateTime = ZonedDateTime.now()
}

