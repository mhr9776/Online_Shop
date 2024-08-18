package entry.rest.finatra.adapter.auth

import contract.service.auth.{SignInService, SignOutService, SignUpService}
import entry.rest.finatra.{RequestWrapper, UnauthenticatedRequestWrapper}
import entry.rest.finatra.adapter.auth.api.{SignInBodyDTO, SignOutBodyDTO, SignUpBodyDTO}

object AuthFactory {

  def signInRequest: (UnauthenticatedRequestWrapper, SignInBodyDTO) => SignInService.Body = (_, dto) =>
    SignInService.Body(dto.username, dto.password)

  def signOuRequest: (RequestWrapper, SignOutBodyDTO) => SignOutService.Body = (rw, _) =>
    SignOutService.Body(rw.getKey)

  def signUpRequest: (UnauthenticatedRequestWrapper, SignUpBodyDTO) => SignUpService.Body = (_, dto) =>
    SignUpService.Body(dto.username, dto.password, dto.name, dto.eMail,dto.role)

}
