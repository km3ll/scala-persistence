package me.khazaddum.persistence.h2

import me.khazaddum.persistence.h2.H2Tables.tbl_Accounts
import com.typesafe.scalalogging.LazyLogging
import me.khazaddum.persistence.Daos.AccountDao
import slick.jdbc.H2Profile.api._

import scala.concurrent.{ ExecutionContext, Future }

case class RepositoryInH2( configPath: String, executionContext: ExecutionContext ) extends H2Mappers with LazyLogging {

  implicit val ec: ExecutionContext = executionContext

  val db = Database.forConfig( configPath: String )

  def find( no: String ): Future[Option[AccountDao]] = {

    logger.debug( s"Finding account no '$no'" )
    val action = tbl_Accounts.filter( _.no === no ).result

    db.run( action.headOption ).map { record =>
      record.map( recordToDao )
    }

  }

  def upsert( account: AccountDao ): Future[AccountDao] = {

    logger.debug( s"Upserting account '$account'" )
    val record = daoToRecord( account )
    val action = tbl_Accounts.+=( record )

    db.run( action ).map { result =>
      logger.debug( s"Account no '${account.no}' upsert result: $result" )
      account
    }.recoverWith {
      case ex: Exception =>
        logger.error( s"Account no '${account.no}' could not be upserted", ex )
        Future.failed( ex )
    }

  }

  def start(): Future[Unit] = {

    logger.info( s"Creating DB connection" )
    val action = tbl_Accounts.schema.create
    db.run( action )

  }

  def stop(): Future[Unit] = Future {

    logger.info( s"Closing DB connection" )
    db.close()

  }

}