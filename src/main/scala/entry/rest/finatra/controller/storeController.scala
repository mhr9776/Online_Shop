package entry.rest.finatra.controller

import com.google.inject.Inject
import com.twitter.finatra.http.Controller
import contract.service.store.{AddProductService, RemoveProductService}
import entry.rest.finatra.RequestWrapper
import entry.rest.finatra.adapter.store.{StoreDTOFactory, StoreFactory}
import entry.rest.finatra.adapter.store.api.{AddProductBodyDTO, RemoveProductBodyDTO}
import module.DatabaseModule.executionContext

class storeController @Inject()(addProductService: AddProductService,removeProductService: RemoveProductService) extends Controller {
  prefix("/api/v1") {

    post("/product", "Add Product") { requestWrapper: RequestWrapper =>
      val requestDTO = requestWrapper.getRequestDTO[AddProductBodyDTO]
      addProductService call StoreFactory.addProductRequest(requestWrapper, requestDTO) map StoreDTOFactory.product map { responseDTO =>
        response created   responseDTO
      }
    }

    delete("/product/remove", "remove Product") { requestWrapper: RequestWrapper =>
      val requestDTO = requestWrapper.getRequestDTO[RemoveProductBodyDTO]
      removeProductService call StoreFactory.removeProductRequest(requestWrapper, requestDTO) map { responseDTO =>
        response ok  responseDTO
      }
    }
  }
}
