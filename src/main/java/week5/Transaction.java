package week5;

/**
 * ACID
 *
 * 1. Read Uncommitted
 *  tx1    begin tx         insert 10 rows          commit
 *            |                 |                       |
 *  --------------------------------------------------------------->  timeline
 *              |          |                   |
 *  tx2     begin tx    select = 100 rows   select = 110 rows
 * 2. Read Committed
 *  tx1    begin tx         insert 10 rows          commit
 *            |                 |                       |
 *  --------------------------------------------------------------->  timeline
 *              |          |                   |                |
 *  tx2     begin tx    select = 100        select = 100    select = 110 rows
 *
 * 3. Repeatable Read
 *  tx1    begin tx         update/delete          commit
 *            |                 |                       |
 *  --------------------------------------------------------------->  timeline
 *              |          |                                |
 *  tx2     begin tx    select1       ====               select2
 *
 * 4. Serializable
 *      all select -> share lock by default
 *
 *
 *
 * ******  * ****** * ****** * ****** * ****** * ****** * ****** * ****** * ****** * ******
 * MVCC -> multi version concurrency control
 *         READ_VIEW(contains all committed tx id)
 *
 * id, name,   (row_id, tx_id, rollback_pointer)
 * 1,  Alex         xx     2        |
 *                                  |
 *                                id, name,   (row_id, tx_id, rollback_pointer)
 *                                1, Tom,       yy,      1      null
 *
 * * ****** * ****** * ****** * ****** * ****** * ****** * ****** * ****** * ****** * ******
 * Read     -> blocks all write request / lock
 *          -> read lock allows read lock
 * Write lock  -> blocks all read locks and write locks
 *
 *
 * Record lock ->  RUC, RC
 *              id
 *              1  ex lock
 *              2  ex lock
 *              5
 *            update..where id > 0 and id < 3
 * NextKey Lock(Record lock + gap lock) -> RR
 *              id
 *              1  ex lock
 *              2  ex lock
 *              (2, 5) gap lock
 *              5  ex lock
 *            update where id > 0 and id <= 5
 * Share Lock for all select -> Serializable
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 * Optimistic lock (prevent lost update)
 *
 *          user1                    user2
 *          read a=1               read a=1
 *          a++                     a++
 *         get ex lock
 *                                 get ex lock (blocked)
 *         db(a = 2)                blocked
 *         release lock             blocked
 *                                 get ex lock
 *                                 db(a = 2)
 *                                 release lock
 *
 *
 *
 *          user1                    user2
 *          read a=1,version=1      read a=1, version=1
 *       update a=2,v=2             update a=2,v=2 where v=1
 *       where v=1
 *         get ex lock
 *                                 get ex lock (blocked)
 *         db(a = 2,v=2)            blocked
 *         release lock             blocked
 *                                 get ex lock
 *                                 update a=2,v=2 where v=1-> throws exception
 *                                 release lock
 *
 *
 *
 *
 *
 */