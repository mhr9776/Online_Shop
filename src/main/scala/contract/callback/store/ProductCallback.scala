package contract.callback.store

import scala.concurrent.Future
import domain.store.Product
abstract class ProductCallback {

  def add(name: String,
          details: String,
          price: BigDecimal,
          inventory: Int) : Future[Long]
  
  def get(id: Long): Future[Option[Product]]

  def getByName(name:String) : Future[Option[Product]]


  def removeByName(name: String): Future[Unit]


  def update( name : String ,
              updateName: Option[String],
              inventory : Option[Int],
              detail : Option[String],
              price : Option[BigDecimal]): Future[Unit]


  def getAll:Future[Option[Product]]

}
