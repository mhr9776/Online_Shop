package data.repository.store

import contract.callback.store.ProductCallback
import data.adaptor.store.ProductFactory
import module.DatabaseModule
import module.DatabaseModule.onlineShop
import scalikejdbc.{NamedDB, scalikejdbcSQLInterpolationImplicitDef}
import domain.store.Product

import scala.concurrent.Future

class ProductRepository extends ProductCallback with DatabaseModule {

  override def get(id: Long): Future[Option[Product]] = Future {
    NamedDB(onlineShop) readOnly { implicit session =>
      sql"""
        SELECT *
        FROM product
        WHERE id = $id
      """.map(ProductFactory.product).single()
    }
  }


  override def add(name: String,
                   details: String,
                   price: BigDecimal,
                   inventory: Int): Future[Long] = Future {
    NamedDB(onlineShop) localTx { implicit session =>
      sql"""
        INSERT INTO product(name,details,price,inventory)
        VALUES ($name,$details,$price,$inventory )
      """.updateAndReturnGeneratedKey()
    }
  }

  override def removeByName(name: String): Future[Unit] = Future {
    NamedDB(onlineShop) localTx { implicit session =>
      sql"""
            DELETE FROM product
            WHERE name = $name
            """.update()
    }
  }

  def update(name: String,
             updateName: Option[String],
             inventory: Option[Int],
             detail: Option[String],
             price: Option[BigDecimal]): Future[Unit] = Future {
    NamedDB(onlineShop) localTx { implicit session =>
      val updateQuery =
        sql"""
        UPDATE product
        SET
          ${updateName.map(n => sql"name = $n").getOrElse(sql"")}
          ${inventory.map(i => sql", inventory = $i").getOrElse(sql"")}
          ${detail.map(d => sql", details = $d").getOrElse(sql"")}
          ${price.map(p => sql", price = $p").getOrElse(sql"")}
        WHERE name = $name
      """
      updateQuery.update()
    }
  }

  override def getAll: Future[Vector[Product]] = Future {
    NamedDB(onlineShop) localTx { implicit session =>
      sql"""
        SELECT *
        FROM product
      """.map(ProductFactory.product).list().toVector
    }
  }

  override def getByName(name: String): Future[Option[Product]] = Future {
    NamedDB(onlineShop) readOnly { implicit session =>
      sql"""
        SELECT *
        FROM product
        WHERE name = $name
      """.map(ProductFactory.product).single()
    }
  }
}


