package module

import com.twitter.inject.TwitterModule
import contract.callback
import data.repository
object CallbackModule extends TwitterModule {

  protected override def configure(): Unit = {

    // Auth
    bind(classOf[callback.auth.SessionCallback]) to classOf[repository.auth.SessionRepository]
    bind(classOf[callback.auth.UserCallback]) to classOf[repository.auth.UserRepository]
    bind(classOf[callback.auth.UserPermissionCallback]) to classOf[repository.auth.UserPermissionRepository]

    // Blog
    bind(classOf[callback.store.ProductCallback]) to classOf[repository.store.ProductRepository]
    bind(classOf[callback.store.OrderCallback]) to classOf[repository.store.OrderRepository]
//    bind(classOf[callback.store.PostCallback]) to classOf[inmem.store.PostRepository]

  }

}