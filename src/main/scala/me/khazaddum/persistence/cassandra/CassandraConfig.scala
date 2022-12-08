package me.khazaddum.persistence.cassandra

case class CassandraConfig(
  host:     String,
  port:     Int,
  keyspace: String
)