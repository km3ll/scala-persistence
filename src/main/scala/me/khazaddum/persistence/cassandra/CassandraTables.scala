package me.khazaddum.persistence.cassandra

import me.khazaddum.persistence.cassandra.CassandraRecords.CassandraAccountRecord
import com.outworkers.phantom.dsl._
import scala.concurrent.Future

object CassandraTables {

  abstract class CassandraAccountTable extends Table[CassandraAccountTable, CassandraAccountRecord] with RootConnector {

    object no extends StringColumn with PartitionKey
    object dateOfOpen extends DateColumn
    object dateOfClose extends OptionalDateColumn
    object balance extends BigDecimalColumn

    def find( no: String ): Future[Option[CassandraAccountRecord]] = {
      select.where( _.no eqs no ).one()
    }

    def upsert( record: CassandraAccountRecord ): Future[ResultSet] = {
      insert()
        .value( _.no, record.no )
        .value( _.dateOfOpen, record.dateOfOpen )
        .value( _.dateOfClose, record.dateOfClose )
        .value( _.balance, record.balance )
        .future()
    }

  }

}
