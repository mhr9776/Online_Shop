package entry.rest.finatra.adapter.common

import entry.rest.finatra.adapter.common.api.ZonedDateTimeDTO

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object CommonFactory {

  def zonedDateTime: ZonedDateTimeDTO => ZonedDateTime = dto =>
    ZonedDateTime.parse(dto, DateTimeFormatter.ISO_ZONED_DATE_TIME)

}
