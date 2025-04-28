package week5;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *  * Java
 *  * 1. ThreadPool
 *  *      ThreadPoolExecutor: ScheduledThreadPool
 *  *      ForkJoinPool
 *         Thread pool thread number
 *          1. 100% cpu task job -> core + 1
 *          2. task : 20% time on cpu, 80% time on io
 *              (1 / 20%) * core number + 1
 *
 *
 *  * 2. ReentrantLock -> Blocking Queue
 *          fair lock
 *          try lock
 *          lock multiple times
 *          lock in one method -> unlock in another method
 *          ..
 *
 *        cpu1: CompareAndSet(ref of the obj, expect value = 5, 10) -> boolean
 *
 *        cpu2: CompareAndSet(ref of the obj, expect value = 5, 8) -> boolean
 *
 *   2.1 cpu1: CompareAndSet(ref of the obj, expect value = 5, 10) -> boolean
 *  *    cpu2: CompareAndSet(ref of the obj, expect value = 5, 8) -> boolean
 *   2.2 volatile
 *
 *          cpu1            cpu2
 *         read  5           read 5
 *         update +1         update +1
 *         write             write
 *
 *                    a = 5
 *
 *
 *
 *  * 3. Stream api
 *  *      collect(supplier, accumulator, combiner)
 *       function : 1 input -> 1 output
 *       consumer: 1 input no output
 *       supplier : no input 1 output
 *       predicate : 1 input boolean output
 *
 *      list.stream().map(x -> x).collect();
 *     iterator
 *     ReferencePipeline(() -> iterator) <-> ReferencePipeline(map)
 *     once we meet collect/terminal operation
 *     Sink1(for obj in obj_list: sink2.accept(current obj)) -> Sink2 -> Sink3
 *
 *
 *     collect(Collectors)
 *     collect(supplier, accumulator, combiner)
 */
class StreamTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
//        var res = list.stream().collect(Collectors.groupingBy(x -> x, Collectors.toList()));
        Map<Integer, List<Integer>> res = list.stream().collect(
                HashMap::new,
                (mapRes, ele) -> {
                    mapRes.putIfAbsent(ele, new ArrayList<>());
                    mapRes.get(ele).add(ele);
                },
                (m1, m2) -> {}
        );
        System.out.println(res);
    }
}

/**
 *  * 4. Out of memory error
 *       a. jvm parameters
 *       b. Reference Type
 *              SoftReference
 *              WeakReference
 *              PhantomReference + ReferenceQueue
 *       c. memory leak
 *              check gc chart / history
 *              heap dump : JProfiler, JMC , Memory Analyzer
 *  * 5. CompletableFuture
 *  *      -> multithreading request
 */

class CompletableFutureExample {
    private static final Random rand = new Random();
    private static final ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        List<CompletableFuture<Integer>> list = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            list.add(CompletableFuture.supplyAsync(() -> request(), pool));
        }
        //solution1
//        int res = 0;
//        for(CompletableFuture<Integer> cf: list) {
//            res += cf.join();
//        }

        //solution2
        CompletableFuture<Void> allOfCF = CompletableFuture.allOf(list.toArray(new CompletableFuture[0]));
        int res = allOfCF.orTimeout(500, TimeUnit.MILLISECONDS).thenApply(Nothing -> list.stream().map(x -> x.join()).reduce(0, (a, b) -> a + b)).join();


        System.out.println(res);
    }

    public static int request() {
        System.out.println(Thread.currentThread());
        try {
            Thread.sleep(rand.nextInt(1001));
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }
        return 1;
    }
}


 /**
 *  * 6. Dynamic Proxy
 *  *    customized annotation from reflection
  */
 interface StudentInterface {
     Integer getAge();
     String getName();
     Boolean getXX();
 }
 class StuImpl implements StudentInterface {
     @Override
     public Integer getAge() {
         return 5;
     }

     @Override
     public String getName() {
         return "Tom";
     }

     @Override
     public Boolean getXX() {
         return false;
     }
 }

 class DynamicProxyTest {
     public static void main(String[] args) {
         StudentInterface stu = (StudentInterface) Proxy.newProxyInstance(
                 DynamicProxyTest.class.getClassLoader(),
                 new Class[]{StudentInterface.class},
                 new MyInvocationHandler(new StuImpl())
         );
         System.out.println("res:" + stu.getAge());
         System.out.println("res:" + stu.getName());
         System.out.println("res:" + stu.getXX());
     }
 }

 class MyInvocationHandler implements InvocationHandler {
     private final Object obj;

     public MyInvocationHandler(Object obj) {
         this.obj = obj;
     }

     @Override
     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         System.out.println("before");
         Object res = method.invoke(obj, args);
         System.out.println(res);
         System.out.println("after");
         return res;
     }
 }


 /**
 *  * 7. concurrent modification exception
 *  *  fail fast, fail safe
  *
*/
 class ConcurrentModificationExample {
     public static void main(String[] args) {
         List<Integer> list = new ArrayList<>();
         list.add(1);
         list.add(2);
         for(int i = 0; i < 2; i++) {
             list.add(5);
         }
     }
 }

/**
 *
 *  new Thread
 */