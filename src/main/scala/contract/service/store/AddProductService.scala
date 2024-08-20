package contract.service.store
import domain.store.Product
import contract.service.Service

abstract class AddProductService extends Service[AddProductService.Body, Product]

object AddProductService {

  case class Body(userId: Long, name : String,details: String, price: BigDecimal, inventory: Int)

}
