package me.khazaddum.persistence.map

import com.typesafe.scalalogging.LazyLogging
import me.khazaddum.persistence.Daos.AccountDao
import scala.collection.mutable
import scala.concurrent.{ ExecutionContext, Future }

case class RepositoryInMap( executionContext: ExecutionContext ) extends LazyLogging {

  implicit val ec: ExecutionContext = executionContext

  private var repository: mutable.Map[String, AccountDao] = mutable.Map.empty

  def find( no: String ): Future[Option[AccountDao]] = Future {
    logger.debug( s"Finding account no '$no'" )
    repository.get( no )
  }

  def upsert( account: AccountDao ): Future[AccountDao] = Future {
    logger.debug( s"Upserting account '$account'" )
    repository = repository += ( ( account.no, account ) )
    account
  }

}