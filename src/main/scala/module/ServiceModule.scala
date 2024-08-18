package module

import com.twitter.inject.TwitterModule
import contract.service
import core.usecase.product

object ServiceModule extends TwitterModule {

  protected override def configure(): Unit = {

    // Auth
    bind(classOf[service.auth.AuthorizeService]) to classOf[product.AuthorizeUseCase]
    bind(classOf[service.auth.SignInService]) to classOf[product.SignInUseCase]
    bind(classOf[service.auth.SignOutService]) to classOf[product.SignUpUseCase]
    bind(classOf[service.auth.SignUpService]) to classOf[product.SignUpUseCase]
//    bind(classOf[service.auth.UpdateUserService]) to classOf[product.UpdateUserUseCase]
    // store
    bind(classOf[service.store.AddProductService]) to classOf[product.AddProductUseCase]
//    bind(classOf[service.store.AddToOrderService]) to classOf[product.AddProductUseCase]
//    bind(classOf[service.store.GetAllProductService]) to classOf[product.GetAllProductUseCase]
//    bind(classOf[service.store.]) to classOf[application.blog.PublishPostUseCase]
//    bind(classOf[service.store.UpdatePostService]) to classOf[application.blog.UpdatePostUseCase]

  }

}
