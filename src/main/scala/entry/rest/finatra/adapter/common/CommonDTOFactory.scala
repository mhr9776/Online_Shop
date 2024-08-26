package entry.rest.finatra.adapter.common


import entry.rest.finatra.adapter.common.api.ZonedDateTimeDTO

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object CommonDTOFactory {

  def zonedDateTime: ZonedDateTime => ZonedDateTimeDTO = o =>
    o format DateTimeFormatter.ISO_ZONED_DATE_TIME

}
