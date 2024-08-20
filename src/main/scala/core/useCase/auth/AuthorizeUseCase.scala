package core.useCase.auth

import com.google.inject.{Inject, Singleton}
import contract.callback.auth.SessionCallback
import contract.service.auth.AuthorizeService
import util.AuthUtils

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AuthorizeUseCase @Inject()(sessionCallback: SessionCallback) extends AuthorizeService {


  override def call(body: AuthorizeService.Body)(implicit ec: ExecutionContext): Future[Unit] = for {
    // Checking invalid key
    _ <- if (AuthUtils.sessionKey(body.session.userID, body.session.username) != body.session.key) {
      Future failed new Exception("User has not logged in!")
    } else {
      Future.unit
    }

    // Checking logged out session
    sessionOption <- sessionCallback get body.session.key
    _ <- sessionOption match {
      case Some(_) => Future.unit
      case None => Future failed new Exception("User has not logged in!") // Show the same error to hide the auth procedure
    }
  } yield ()

}

