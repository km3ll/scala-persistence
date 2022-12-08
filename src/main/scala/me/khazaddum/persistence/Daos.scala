package me.khazaddum.persistence

import java.util.Date

object Daos {

  case class AccountDao(
    no:          String,
    dateOfOpen:  Date,
    dateOfClose: Option[Date] = None,
    balance:     BigDecimal
  )

}