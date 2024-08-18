package entry.rest.finatra.filters

import com.google.inject.Inject
import com.twitter.finagle.Service
import com.twitter.finagle.SimpleFilter
import com.twitter.finagle.http.Request
import com.twitter.finagle.http.Response
import com.twitter.util.Future
import contract.service.auth.AuthorizeService
import domain.auth.Session
import entry.rest.finatra.util.FutureUtils.ToTwitterFuture
import scala.concurrent.ExecutionContext.Implicits.global

class AuthorizationFilter @Inject()(authorizeService: AuthorizeService) extends SimpleFilter[Request, Response] {

  override def apply(request: Request, service: Service[Request, Response]): Future[Response] = {
    (request.cookies get "key", request.cookies get "userID", request.cookies get "username") match {
      case (Some(key), Some(userID), Some(username)) =>
        for {
          _ <- authorizeService.call(AuthorizeService.Body(Session(key.value, userID.value.toLong, username.value))).toTwitter()
          response <- service(request)
        } yield response
      case _ => Future.exception(new Exception("Login is required!"))
    }
  }

}