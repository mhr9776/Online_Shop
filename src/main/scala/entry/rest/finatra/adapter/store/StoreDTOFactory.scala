package entry.rest.finatra.adapter.store

import domain.store.Product
import entry.rest.finatra.adapter.store.api.ProductDTO

object StoreDTOFactory {
  def product: Product => ProductDTO = o =>
    ProductDTO(o.id, o.name, o.details, o.price, o.inventory)

  def products(o: Vector[Product]): Vector[ProductDTO] =
    o map product

}
