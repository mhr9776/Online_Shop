package contract.service

import scala.concurrent.{ExecutionContext, Future}

abstract class Service[Request, Response] {

  def call(body: Request)(implicit ec: ExecutionContext): Future[Response]

}
