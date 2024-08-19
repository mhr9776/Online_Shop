import com.google.inject.Module
import com.twitter.finagle.http.Request
import com.twitter.finagle.http.Response
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters._
import com.twitter.finatra.http.routing.HttpRouter
import module.{CallbackModule, ServiceModule}
import entry.rest.finatra.controller.{AuthenticationController, unsafe}
import entry.rest.finatra.filters.AuthorizationFilter
import entry.rest.finatra.util.CustomScalaObjectMapperModule

object Application extends HttpServer {

  override def jacksonModule: Module = CustomScalaObjectMapperModule

  override protected def defaultHttpPort: String = ":8080"

  override val modules: Seq[Module] = Seq(CallbackModule, ServiceModule)

  override protected def configureHttp(router: HttpRouter): Unit = {
    // Filters
    router.filter[CommonFilters]
    router.filter[LoggingMDCFilter[Request, Response]]
    router.filter[TraceIdMDCFilter[Request, Response]]

    // Controllers
    // // Unauthorized Access
    router.add[unsafe.AuthenticationController]
    // // Authorized Access
    router.add[AuthorizationFilter, AuthenticationController]
    //    router.add[AuthorizationFilter, BlogController]
  }

}