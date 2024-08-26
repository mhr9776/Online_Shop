package contract.callback.store

import domain.store.{Order, ProductInOrder}
import entry.rest.finatra.adapter.store.api.ProductInorderDTO

import scala.concurrent.Future

abstract class OrderCallback {

  def add(UserId: Long, products: Vector[ProductInOrder], quantity : Int) : Future[Long]

  def get(UserId: Long): Future[Vector[Order]]


  def remove(UserId: Long,ProductId: Long): Future[Unit]

  def update(userId : Long,productId : Long,quantity : Int): Future[Unit]}
