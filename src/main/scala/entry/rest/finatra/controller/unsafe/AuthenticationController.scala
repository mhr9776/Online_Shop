package entry.rest.finatra.controller.unsafe

import com.google.inject.Inject
import com.twitter.finagle.http.Cookie
import contract.service.auth.{SignInService, SignUpService}
import com.twitter.finatra.http.Controller
import entry.rest.finatra.UnauthenticatedRequestWrapper
import entry.rest.finatra.adapter.auth.{AuthDTOFactory, AuthFactory}
import entry.rest.finatra.adapter.auth.api.{SignInBodyDTO, SignUpBodyDTO}

import scala.concurrent.ExecutionContext.Implicits._

class AuthenticationController @Inject()(signInService: SignInService, signUpService: SignUpService) extends Controller {

  prefix("/api/v1") {

    post("/users", "Sign Up") { requestWrapper: UnauthenticatedRequestWrapper =>
      val requestDTO = requestWrapper.getRequestDTO[SignUpBodyDTO]
      signUpService call AuthFactory.signUpRequest(requestWrapper, requestDTO) map AuthDTOFactory.user map { serviceResponseDTO =>
        response created serviceResponseDTO
      }
    }

    post("/users/:username/sessions", "Sign In") { requestWrapper: UnauthenticatedRequestWrapper =>
      val requestDTO = requestWrapper.getRequestDTO[SignInBodyDTO]
      signInService call AuthFactory.signInRequest(requestWrapper, requestDTO) map { serviceResponse =>
        response.created()
          .cookie(new Cookie("key", serviceResponse.key, path = Some("/")))
          .cookie(new Cookie("userID", serviceResponse.userID.toString, path = Some("/")))
          .cookie(new Cookie("username", serviceResponse.username, path = Some("/")))
      }
    }

  }

}
