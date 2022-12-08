package me.khazaddum.persistence.h2

import java.sql.Timestamp

object H2Records {

  case class H2AccountRecord(
    no:          String,
    dateOfOpen:  Timestamp,
    dateOfClose: Option[Timestamp],
    balance:     BigDecimal
  )

}
