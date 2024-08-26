package entry.rest.finatra.adapter.store.api


import entry.rest.finatra.adapter.common.api.ZonedDateTimeDTO

import java.util.Date

case class OrderDTO(id: Long,
                    userId: Long,
                    products: Vector[ProductInorderDTO],
                    totalAmount: BigDecimal,
                    orderDate: ZonedDateTimeDTO
                    )
