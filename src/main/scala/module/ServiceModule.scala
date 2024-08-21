package module

import com.twitter.inject.TwitterModule
import contract.service
import contract.service.store.GetAllProductService
import core.useCase.auth.{AuthorizeUseCase, SignInUseCase, SignOutUseCase, SignUpUseCase}
import core.useCase.store
import core.useCase.store.{AddProductUseCase, GetAllProductUseCase, RemoveProductUseCase}

object ServiceModule extends TwitterModule {

  protected override def configure(): Unit = {

    // Auth
    bind(classOf[service.auth.AuthorizeService]) to classOf[AuthorizeUseCase]
    bind(classOf[service.auth.SignInService]) to classOf[SignInUseCase]
    bind(classOf[service.auth.SignOutService]) to classOf[SignOutUseCase]
    bind(classOf[service.auth.SignUpService]) to classOf[SignUpUseCase]
    bind(classOf[service.store.AddProductService]) to classOf[AddProductUseCase]
    // store
    bind(classOf[service.store.RemoveProductService]) to classOf[RemoveProductUseCase]
    bind(classOf[service.store.GetAllProductService]) to classOf[GetAllProductUseCase]
//    bind(classOf[service.store.GetAllProductService]) to classOf[product.GetAllProductUseCase]
//    bind(classOf[service.store.]) to classOf[application.blog.PublishPostUseCase]
//    bind(classOf[service.store.UpdatePostService]) to classOf[application.blog.UpdatePostUseCase]

  }

}
