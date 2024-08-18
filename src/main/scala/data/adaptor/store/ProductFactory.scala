package data.adaptor.store

import domain.store.Product
import scalikejdbc.WrappedResultSet


object ProductFactory {

  def product(dto: WrappedResultSet): Product =
    Product(
      dto long  "id", dto string "name", dto string "details", dto bigDecimal  "price", dto int "inventory")


}
