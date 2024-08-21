package entry.rest.finatra.adapter.store

import contract.service.store.{AddProductService, GetAllProductService, RemoveProductService}
import entry.rest.finatra.RequestWrapper
import entry.rest.finatra.adapter.store.api.{AddProductBodyDTO, GetAllProductBodyDTO, RemoveProductBodyDTO}

object StoreFactory {

  def addProductRequest: (RequestWrapper, AddProductBodyDTO) => AddProductService.Body = (rw, dto) =>
    AddProductService.Body(rw.getUserID,dto.name, dto.details,dto.price,dto.inventory)

  def removeProductRequest: (RequestWrapper,RemoveProductBodyDTO) => RemoveProductService.Body = (rw, dto) =>
    RemoveProductService.Body(rw.getUserID, dto.productName)

  def getALlProductRequest:(RequestWrapper,GetAllProductBodyDTO) => GetAllProductService.Body = (rw, _) =>
    GetAllProductService.Body(rw.getUserID)
}
