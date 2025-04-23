package week5;


import java.util.*;
import java.util.stream.Collectors;

/**
 *  * Java
 *  * 1. ThreadPool
 *  *      ThreadPoolExecutor: ScheduledThreadPool
 *  *      ForkJoinPool
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
 *  *    memory leak
 *  * 5. CompletableFuture
 *  *      -> multithreading request
 *  * 6. Dynamic Proxy
 *  *    customized annotation from reflection
 *  * 7. concurrent modification exception
 *  *  fail fast, fail safe
 *
 *
 *  new Thread
 */