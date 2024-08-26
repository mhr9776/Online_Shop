package entry.rest.finatra.adapter.store

import contract.callback.store.ProductCallback
import contract.service.store.{AddProductService, AddToOrderService, GetAllProductService, GetOrderService, RemoveProductService}
import entry.rest.finatra.RequestWrapper
import entry.rest.finatra.adapter.store.api.{AddProductBodyDTO, AddToOrderBodyDTO, GetAllProductBodyDTO, GetOrderBodyDTO, RemoveProductBodyDTO}

object StoreFactory {

  def addProductRequest: (RequestWrapper, AddProductBodyDTO) => AddProductService.Body = (rw, dto) =>
    AddProductService.Body(rw.getUserID,dto.name, dto.details,dto.price,dto.inventory)

  def removeProductRequest: (RequestWrapper,RemoveProductBodyDTO) => RemoveProductService.Body = (rw, dto) =>
    RemoveProductService.Body(rw.getUserID, dto.productName)

  def getALlProductRequest:(RequestWrapper,GetAllProductBodyDTO) => GetAllProductService.Body = (rw, _) =>
    GetAllProductService.Body(rw.getUserID)

  def addToOrderRequest: (RequestWrapper, AddToOrderBodyDTO) => AddToOrderService.Body = (rw, dto) =>
    AddToOrderService.Body(rw.getUserID, dto.productName, dto.quantity)

  def getOrderRequest: (RequestWrapper, GetOrderBodyDTO) => GetOrderService.Body = (rw, _) =>
    GetOrderService.Body(rw.getUserID)
}
