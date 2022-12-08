package me.khazaddum.persistence.h2

import java.sql.Timestamp
import H2Records.H2AccountRecord
import me.khazaddum.persistence.Daos.AccountDao

trait H2Mappers {

  def recordToDao( record: H2AccountRecord ): AccountDao = {
    AccountDao( record.no, record.dateOfOpen, record.dateOfClose, record.balance )
  }

  def daoToRecord( dao: AccountDao ): H2AccountRecord = {
    H2AccountRecord( dao.no, new Timestamp( dao.dateOfOpen.getTime ), dao.dateOfClose.map( dt => new Timestamp( dt.getTime ) ), dao.balance )
  }

}