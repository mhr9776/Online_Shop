package entry.rest.finatra.adapter.store

import domain.store.{Order, Product, ProductInOrder}
import entry.rest.finatra.adapter.common.CommonDTOFactory
import entry.rest.finatra.adapter.store.api.{OrderDTO, ProductDTO, ProductInorderDTO}

object StoreDTOFactory {
  def product: Product => ProductDTO = o =>
    ProductDTO(o.id, o.name, o.details, o.price, o.inventory)

  def products(o: Vector[Product]): Vector[ProductDTO] =
    o map product

  def productInOrder: ProductInOrder => ProductInorderDTO = o =>
    ProductInorderDTO(o.productName, o.price)

  def productInOrders(o: Vector[ProductInOrder]): Vector[ProductInorderDTO] =
    o map productInOrder

  def order: Order => OrderDTO = o =>
    OrderDTO(o.id, o.userId, o.products map productInOrder, o.totalAmount, CommonDTOFactory zonedDateTime o.orderDate)

  def orders(o: Vector[Order]): Vector[OrderDTO] =
    o map order

}
