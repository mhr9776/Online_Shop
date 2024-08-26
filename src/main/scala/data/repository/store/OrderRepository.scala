package data.repository.store

import contract.callback.store.OrderCallback
import data.adaptor.store.OrderFactory
import domain.store.{Order, ProductInOrder}
import module.DatabaseModule
import module.DatabaseModule.onlineShop
import org.json4s.{DefaultFormats, Formats}
import org.json4s.jackson.Serialization.write
import scalikejdbc.{NamedDB, scalikejdbcSQLInterpolationImplicitDef}

import scala.concurrent.Future

class OrderRepository extends OrderCallback with DatabaseModule {

  implicit val format: Formats = DefaultFormats

  override def add(UserId: Long, products: Vector[ProductInOrder], quantity: Int): Future[Long] = Future {
    val productsJ = write(products)
    NamedDB(onlineShop) localTx { implicit session =>
      sql"""
        INSERT INTO order(user_id,product_name,quantity)
        VALUES ($UserId,$productsJ,$quantity)
      """.updateAndReturnGeneratedKey()
    }
  }

  override def get(UserId: Long): Future[Vector[Order]] = Future {
    NamedDB(onlineShop) readOnly { implicit session =>
      sql"""
        SELECT *
        FROM order
        WHERE user_id = $UserId
      """.map(OrderFactory.order).list().toVector
    }
  }

  override def remove(UserId: Long, ProductId: Long): Future[Unit] = ???

  override def update(userId: Long, productId: Long, quantity: Int): Future[Unit] = ???
}
