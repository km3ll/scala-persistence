package me.khazaddum.persistence.cassandra

import java.util.Date

object CassandraRecords {

  case class CassandraAccountRecord(
    no:          String,
    dateOfOpen:  Date,
    dateOfClose: Option[Date],
    balance:     BigDecimal
  )

}
