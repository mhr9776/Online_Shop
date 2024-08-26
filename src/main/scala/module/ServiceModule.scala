package module

import com.twitter.inject.TwitterModule
import contract.service
import contract.service.store.GetAllProductService
import core.useCase.auth.{AuthorizeUseCase, SignInUseCase, SignOutUseCase, SignUpUseCase}
import core.useCase.store
import core.useCase.store.{AddProductUseCase, AddToOrderUseCase, GetAllProductUseCase, GetOrderUseCase, RemoveProductUseCase}

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
    bind(classOf[service.store.AddToOrderService]) to classOf[AddToOrderUseCase]
//    bind(classOf[service.store.GetOrderService]) to classOf[GetOrderUseCase]
//    bind(classOf[service.store.UpdatePostService]) to classOf[application.blog.UpdatePostUseCase]

  }

}
