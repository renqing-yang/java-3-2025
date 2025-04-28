package week5;

/**
 * Java
 * 1. ThreadPool
 *      ThreadPoolExecutor
 *      ScheduledThreadPool
 *      ForkJoinPool
 * 2. ReentrantLock -> Blocking Queue
 * 3. Stream api
 *      collect(supplier, accumulator, combiner)
 * 4. Out of memory error
 *    memory leak
 * 5. CompletableFuture
 *      -> multithreading request
 * 6. Dynamic Proxy
 *    customized annotation from reflection
 * 7. concurrent modification exception
 *  fail fast, fail safe
 *
 *
 *
 * RDBMS
 * 1. ACID
 *     isolation level ?
 * 2. execution plan + hint
 *      select * from hr.departments
// * select /*+ index(e) */
// *from hr.employees e
//        *select /*+ full(e) */ employee_id from hr.employees e
//        *select*from hr.departments d join hr.employees e on d.department_id=e.department_id
//        *select /*+ parallel(10) use_merge(d e)*/ *from hr.departments d,hr.employees e where d.department_id=e.department_id
//        *
//        *1. Full Table Scan
//        *2. Index Access Path
//        *a.index unique scan
//        *b.index range scan
//        *c.index full scan
//        *3. join:merge join,nested loop join
//        *
//        *hint:full(table),index(table),use_nl(),leading(),use_hash(),use_merge()
//        *
//        *
//        *
//        *merge join=merge sort
 /*** 3. bitmap index, clustered index
  *
  *    32 appointments
  *
  *    00000000..(16 digit binary)
  *
  *    if((app & 011100000) == 0) {
  *        app |^= 011100000
  *    }
  *
  *
  *   state  rowid          rowid ,  NJ,  NY,  VA
  *     NJ                           1    0     0
  *     NY                           0    1     0
  *     VA                           0    0     1
  *     VA                           0    0     1
  *
  *     NJ : 1000
  *     NY : 0100
  *     VA : 0011
  *
  *     NJ or NY => 1100
  *
  *     100000000000 -> [1]1[11]0
  *
  *
 * 3. db architecture
 * 4. optimistic lock ?
 * 5. table design many to many ,  1 to many ?
  *
  *   1. RDBMS design, basic
  *         student m - 1 teacher
  *
  *         teacher(id(pk), name)
  *         student(id(pk, name, t_id(fk))
  *
  *         student m - m teacher
  *         student (id(pk), name)
  *         student_teacher(id(pk), s_id(fk), t_id(fk))
  *         teacher (id(pk), name)
  *   2. RDBMS -> nosql design
  *         a. col1, col2, col3...........col20
  *         b. shared table
  *            sub table1
  *            sub table2
  *            sub table3
  *         c. id, name, json column
  *         d. entity attribute value design
  *             id, column_name, column_type, value , document_id(fk)
  *             1 ,   "name"        "string"  "doc name1",  a101
  *             2,    "age"         "int"     "5"        ,  a101
  *             3,    "color"       "string"  "black"   ,   a102
  *   3. Dynamodb
  *        student table: partition key(hash key), sorted key(range key), values
  *        teacher table: partition key(hash key), sorted key(range key), values (50% student info)
  *
  *
  *
  *
 *
 * MongoDB + Cassandra Cluster ?
 *  1. cluster/architecture
  * 2. consistent hashing
  *    read consistency level
  *    write consistency level
  * 3. cache
  *     redis cluster
  *     cache aside
  *     read through
  *     write through
  *     ..
 *
 * Rest Api Security
 * 1. Spring Security internal flow
  *     UsernamePasswordAuthenticationFilter (get user info from request form)
  *             |
  *     AuthenticationManager.authenticate()
  *            |
  *     DAOAuthenticationProvider
  *          |
  *     load user by user name (UserDetailsService)
  *         |
  *      verify it
  *        |
  *       generate jwt
  *
  *
  *      Authorization Flow
  *      JWT Filter
  *         |
  *     get jwt from header "Authorization Bearer"
  *         |
  *     jwt utility decode + verify
  *         |
  *     save userinfo in SecurityContext (ThreadLocal)
  *        |
  *     @PreAuthorize("hasRole['Admin']")
  *
  *
 * 2. OAuth2. 0, open ID
 *     -> id token(email, phone) : authentication
 *     -> access token : authorization
  *
  *        browser  -  google
  *         |
  *       app server (client id , client secret)
  *
 * 3. CORS
  *     client    ->   server
  *         -> preflight request option method
  *         <- access allow origin
  *            access allow header
  *            access allow http method
  *
  *         -> real request with access headers
  *         <- return result with access allow origin header in response header
  *
 * 4. CSRF
  *
  *
 *
 *
 * Microservice
 * .. MongoDB + Cassandra Cluster ? => cluster/architecture
 * 1. Home work
 * 2. generate global unique id
 * 3. message queue ? kafka , sqs, rabbit mq ?
 * 4. Global transaction: SAGA, 2pc commit, CDC + outbox pattern
 * 5, AWS
 *      frontend deployment: Cloudfront + S3
 *      Backend : Api Gateway -> Lambda  -> Dynamodb -> lambda -> sqs ...
 *                      |
 *                    Cognito
 *
 *
 * Daily Work: Agile Scrum
 * CI/CD pipeline
 * Git branches
 *
 *
 *
 */