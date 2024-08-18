package contract.callback.store

import domain.store.{Order, Product}

import scala.concurrent.Future

abstract class OrderCallback {

  def add(UserId: Long,ProductId: Long, quantity : Int) : Future[Long]

  def get(UserId: Long): Future[Option[Order]]


  def remove(UserId: Long,ProductId: Long): Future[Unit]

  def update(userId : Long,productId : Long,quantity : Int): Future[Unit]}
