package me.khazaddum.persistence.cassandra

import java.sql.Timestamp
import me.khazaddum.persistence.Daos.AccountDao
import me.khazaddum.persistence.cassandra.CassandraRecords.CassandraAccountRecord

trait CassandraMappers {

  def recordToDao( record: CassandraAccountRecord ): AccountDao = {
    AccountDao( record.no, record.dateOfOpen, record.dateOfClose, record.balance )
  }

  def daoToRecord( dao: AccountDao ): CassandraAccountRecord = {
    CassandraAccountRecord(
      dao.no,
      new Timestamp( dao.dateOfOpen.getTime ),
      dao.dateOfClose.map( date => new Timestamp( date.getTime ) ),
      dao.balance
    )
  }

}
