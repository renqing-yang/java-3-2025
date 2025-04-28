package week5;

/**
 * // Scenario:
 * // You have a list of transactions from an e-commerce platform. Each transaction includes the following information:
 *
 * // Transaction ID
 * // User ID, String
 * // Item ID, Long
 * // Quantity, int
 * // Price per item, double
 *
 * // Your task is to process this list of transactions to answer several business questions. You must use the Java Stream API to accomplish the following:
 *
 * // 1.Calculate the total revenue generated.
 *     list.stream()
 *          .map(t -> t.getQuantity() * t.getItem())
 *          .reduce(0.0, (a, b) -> a + b)
 *
 * // 2.Find the top 2 users who spent the most money.
 *    list.stream()
 *          .sorted((t1, t2) -> t2.getQuantity() * t2.getItem() - t1.getQuantity() * t1.getItem())
 *          .limit(2)
 *          .collect(Collectors.toList())
 * // 3.Identify the most popular item (the item with the highest quantity sold).
 *    Map<Long, Integer> cnt = list.stream()
 *                                   .collect(Collectors.groupingBy(t -> t.getItemId(), Collectors.sumInt(t -> t.getQuantity())));
 *    Map<Long, Integer> cnt = list.stream()
 *                                   .collect(
 *                                      HashMap::new,
 *                                      (map, ele) -> map.merge(ele.getKey(), ele.getValue(), Integer::sum),
 *                                      (m1, m2) -> {
 *                                          //merge (m1, m2) into m1
 *                                      }
 *                                   );
 *    return cnt.entrySet().sorted((e1, e2) -> e2.getValue() - e1.getValue())
 *      .findFirst() //optional
 *      .get()
 *      .getKey()
 *
 *
 * class Transaction {
 *
 * }
 *
 */