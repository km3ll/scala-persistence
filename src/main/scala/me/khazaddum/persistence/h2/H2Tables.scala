package me.khazaddum.persistence.h2

import slick.lifted.TableQuery
import java.sql.Timestamp
import H2Records.H2AccountRecord
import slick.jdbc.H2Profile.api._

object H2Tables {

  val tbl_Accounts = TableQuery[H2AccountTable]

  class H2AccountTable( tag: Tag ) extends Table[H2AccountRecord]( tag, "tbl_accounts" ) {

    def no = column[String]( "no", O.PrimaryKey )
    def dateOfOpen = column[Timestamp]( "dateOfOpen" )
    def dateOfClose = column[Option[Timestamp]]( "dateOfClose" )
    def balance = column[BigDecimal]( "balance" )

    def * = ( no, dateOfOpen, dateOfClose, balance ) <> ( H2AccountRecord.tupled, H2AccountRecord.unapply )

  }

}

