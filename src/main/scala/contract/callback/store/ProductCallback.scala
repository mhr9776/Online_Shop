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
              updateName: Option[String]= None,
              inventory : Option[Int] = None,
              detail : Option[String] = None,
              price : Option[BigDecimal]=None): Future[Unit]


  def getAll:Future[Vector[Product]]

}
