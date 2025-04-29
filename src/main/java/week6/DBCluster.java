package week6;

/**
 * Single Leader Cluster
 *
 *      leader
 *     /    |   \
 *  Follower1 2 3
 *
 *  write -> leader node (async pass data to followers)
 *           ack = 1    user -> leader -> (background thread) -> sync all followers
 *           ack = 3    user -> leader
 *                                | | \
 *                            all followers
 *  read  -> leader node or follower node
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 * Multi Leader Cluster
 *   write          write
 *      |           |
 *   Leader1        Leader2         Leader3
 *  /   \           /     \         /   \
 * Follower         Follower        Follower
 *
 * write -> leader1 -> stu id=1 name from tom to alex
 * write -> leader2 -> stu id=1 name from tom to adam
 *
 * LWW -> last write win
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 * Leaderless = All Leader Cluster
 *
 * Consistent hashing
 *
 *            N1 (0)
 *
 *    N4(50k)         N2(10k)
 *
 *                   NX(15k)
 *          N3(20k)
 * hash(id)
 * virtual node
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   CAP
 *
 *   CP: MongoDB
 *   AP: Cassandra
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   Mongodb Cluster
 *                user
 *                 |
 *              mongos(Api Gagteway)   -  config (id - sharding mapping / location)
 *              /       |       \
 *          sharding1   2       3
 *          id 0 ~1k
 *          primary
 *         /    |
 *Secondary node
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   Cassandra Node
 *
 *    write ->mem table(cache) -> SSTable(sorted string table) immutable
 *            |
 *          commit log(disk)
 *
 *    read -> mem table(cache) -> blooming filter -> SST1, SST5
 *
 *
 *
 *    hashing1  [][][][][]
 *    hashing2  [][][][][]
 *    hashing3  [][][][][]
 *
 *  Cassandra Cluster
 *            N1 (0)
 *
 *    N4(50k)         N2(10k)
 *
 *                   NX(15k)
 *          N3(20k)
 *
 *
 *   write request -> N4 -> N2, NX, N3
 *   read request -> N4 -> 2 nodes
 *                        fastest two nodes
 *                        read data1 from fastest node
 *                        read hash(data2) from another node
 *                        if hash(data1) != hash(data2)
 *                              trigger read repair in these two nodes
 *
 *   Replica Factory = 3
 *   Write Consistency Level = 2
 *   Read Consistency Level = 2
 *
 *   Read C level + Write C level > Replica Factory
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   Redis Cluster
 *
 *   hash slot = ~24k
 *
 *   hash(id) % length(hash slot) = index
 *
 *          Leader1(0 ~10k)  Leader2(10k ~ max)
 *
 *  Cache
 *      1. read <- cache <- db
 *      2. write -> cache -> db
 *      3. cache aside
 *              user    -  db
 *               |
 *              cache
 *        a. read
 *             1. read from cache first -> if find -> return result
 *                                         if not  -> read from db , save to cache -> return result
 *        b. write
 *             1. remove that data from cache
 *             2. write data to db
 *
 *
 *
 *      eg.
 *          1. user2 remove id1 from cache
 *          2. user1 read id1 from cache
 *          3. user1 read from db, and save data to cache
 *          4. user2 write id1 to db
 *
 *
 *
 *
 *
 *
 */