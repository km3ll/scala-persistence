package me.khazaddum.persistence.cassandra

import com.outworkers.phantom.dsl._
import pureconfig._
import pureconfig.generic.auto._

object CassandraConnector {

  private val config = ConfigSource.default.at( "cassandra" ).load[CassandraConfig]
    .getOrElse( CassandraConfig( "localhost", 9142, "khazad_dum" ) )

  val default: CassandraConnection = ContactPoint( config.host, config.port )
    .noHeartbeat()
    .keySpace( config.keyspace )

}
