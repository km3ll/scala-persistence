package me.khazaddum.persistence.cassandra

import com.typesafe.scalalogging.LazyLogging
import com.outworkers.phantom.dsl._
import me.khazaddum.persistence.Daos.AccountDao
import scala.concurrent.{ ExecutionContext, Future }
import scala.concurrent.duration._

case class RepositoryInCassandra( executionContext: ExecutionContext ) extends CassandraMappers with LazyLogging {

  implicit val ec: ExecutionContext = executionContext

  object db extends CassandraDB( CassandraConnector.default )

  def find( no: String ): Future[Option[AccountDao]] = {

    logger.debug( s"Finding account no '$no'" )
    db.accounts.find( no ).map { optionRecord =>
      optionRecord.map( recordToDao )
    }

  }

  def upsert( account: AccountDao ): Future[AccountDao] = {

    logger.debug( s"Upserting account '$account'" )
    val record = daoToRecord( account )
    val result: Future[ResultSet] = for {
      res1 <- db.accounts.upsert( record )
    } yield res1

    result.map { result =>
      logger.debug( s"Account no '${account.no}' upsert result: $result" )
      account
    }.recoverWith {
      case ex: Exception =>
        logger.error( s"Account no '${account.no}' could not be upserted", ex )
        Future.failed( ex )
    }

  }

  def start(): Future[Unit] = Future {

    logger.info( s"Creating DB connection" )
    db.create( 30.seconds )
    ()

  }

  def stop(): Future[Unit] = Future {
    logger.info( s"Closing DB connection" )
    db.shutdown()
  }

}