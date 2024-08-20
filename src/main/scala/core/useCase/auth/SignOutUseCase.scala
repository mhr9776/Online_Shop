package core.useCase.auth

import com.google.inject.{Inject, Singleton}
import contract.callback.auth.SessionCallback
import contract.service.auth.SignOutService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SignOutUseCase @Inject()(sessionCallback: SessionCallback) extends SignOutService {

  override def call(body: SignOutService.Body)(implicit ec: ExecutionContext): Future[Unit] = {
    sessionCallback remove body.key
  }

}