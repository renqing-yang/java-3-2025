package week6;

/**
 *  producer ->  message queue -> consumer
 *
 *  Queue model
 *  producer1                               consumer1[m1]
 *  producer2   ->  [m1][m2][m3][m4].. ->   consumer2[m4]
 *                                          ...
 *  Pub Sub model
 *  producer1                               consumer1[m4]
 *  producer2   ->  [m1][m2][m3][m4].. ->   consumer2[m4]
 *                                          consumer3[m4]
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  Kafka
 *              broker1                             Consumer Group1
 *              T1Partition1  Leader                Consumer1  pull message from p1
 *              T1Partition2  Follower              Consumer2  pull message from p2
 *                                                  Consumer3  idle
 *
 *              broker2
 *              T1Partition1  Follower
 *              T1Partition2  Leader
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  SQS (queue model)
 *      1. Standard Queue
 *      2. FIFO Queue
 *      3. DLQ (dead letter queue)
 *
 *      visibility timeout
 *      SQS -> lambda/ec2/consumers
 *
 *
 *  SNS (pub sub model)
 *      -> sqs
 *  SNS -> sqs -> ..,.
 *      -> sqs -> notification
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   Duplicate Messages
 *     1. Idempotent system / service
 *
 *     message ->  when status == A then change data, and status from A to B
 *
 *     2. deduplicate message in SNS / or using cache
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   Global Transactions
 *   case1
 *      service  -  message queue
 *         |
 *        DB
 *
 *     1. insert db
 *     2. send message to message queue
 *
 *     1. send message to message queue
 *     2. insert db
 *
 *     CDC(change data capture + outbox pattern)
 *      service
 *         |
 *        DB  ->  service(cdc service) -> message queue
 *
 *      1. begin tx
 *         insert data to db
 *         insert message into outbox table
 *         commit
 *      2. cdc service keep checking the outbox table
 *         read message from outbox table
 *         send to message queue
 *         change message status to processed
 *
 *  case2:
 *          service
 *            |
 *         coordinator
 *         /       \
 *        DB1      DB2
 *
 *      2PC
 *      1. coordinator -> DB1 and DB2
 *         check if query is valid / if DB1 and DB2 is working ..
 *         <- ok
 *      2. coordinator -> commit -> DB1 and DB2
 *
 *  case3:
 *
 *        service1      -    mq  -   service2
 *          |                           |
 *         DB1                          DB2
 *
 *         SAGA pattern
 *         1. service1 -1 in DB1
 *            commit -> success -> send message to mq
 *                   -> rollback
 *         2. service2 do something in DB2 -> success -> done -> send notification
 *                   -> rollback -> generate another message +1 send to mq -> service1
 *
 *
 *
 *
 *
 *
 *
 */