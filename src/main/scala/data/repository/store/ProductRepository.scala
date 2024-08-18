package data.repository.store

import contract.callback.store.ProductCallback
import data.adaptor.store.ProductFactory
import module.DatabaseModule
import module.DatabaseModule.onlineShop
import scalikejdbc.{NamedDB, scalikejdbcSQLInterpolationImplicitDef}

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


  override def add(productId: Long,
                   name: String,
                   details: String,
                   price: BigDecimal,
                   inventory: Int): Future[Long] = Future {
    NamedDB(onlineShop) localTx { implicit session =>
      sql"""
        INSERT INTO product(id, name,details,price,inventory)
        VALUES ($productId,$name,$details,$price,$inventory )
      """.updateAndReturnGeneratedKey()
    }
  }

  override def remove(id: Long): Future[Unit] = Future {
    NamedDB(onlineShop) localTx { implicit session =>
      sql"""
            Delete from product
            where id = $id
            """.update
    }
  }

  override def update(
                       id :Long,
                       name : Option[String],
                      inventory : Option[Int],
                      detail : Option[String],
                      price : Option[BigDecimal]): Future[Unit] = Future {
    NamedDB(onlineShop) localTx { implicit session =>
      sql"""
        UPDATE product
        SET
          name =$name,
          inventory = $inventory,
          details = $detail,
          price = $price
        WHERE id = $id
      """.update()
    }
  }

  override def getAll: Future[Option[Product]] = ???
}


