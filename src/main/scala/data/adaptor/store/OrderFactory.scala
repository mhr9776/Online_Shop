package data.adaptor.store


import domain.store.{Order, ProductInOrder}
import entry.rest.finatra.adapter.store.StoreDTOFactory
import entry.rest.finatra.adapter.store.api.ProductInorderDTO
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods
import scalikejdbc.WrappedResultSet

object OrderFactory {
  import Getters._

  def order(dto: WrappedResultSet): Order =

    Order(dto long  "id", dto long "user_id", dto products "product_name")

  object Getters {

    private implicit val formats: DefaultFormats.type = DefaultFormats

    implicit class ProductGetter(wr: WrappedResultSet) {

      private def transformMany(json: String): Vector[ProductInOrder] =
        JsonMethods.parse(json).extract[Vector[ProductInOrder]] // map StoreDTOFactory.productInOrder

      def products(columnLabel: String): Vector[ProductInOrder] =
        transformMany(wr string columnLabel)

    }

  }
}
