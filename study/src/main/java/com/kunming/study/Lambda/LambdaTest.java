package com.kunming.study.Lambda;


import java.math.BigDecimal;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import static java.util.Collections.sort;

public class LambdaTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        //    * **将列表中的字符串转换为大写**
        //     * - 给定一个字符串列表 `List<String>`，使用 Lambda 表达式将每个字符串转换为大写。
        List<String> list = Arrays.asList("Hello", "World", "Lambda");
        Consumer<String> consumer = s -> System.out.println(s.toUpperCase());
        list.forEach(consumer);


        //**使用 Lambda 实现 Runnable**
        //- 使用 Lambda 表达式创建一个 `Runnable` 任务，并打印 `"Hello, Lambda!"`。
        Runnable runnable = () -> {
            System.out.println("Hello, Lambda!");
        };
        runnable.run();

        //**过滤出列表中的偶数**
        //- 给定一个整数列表 `List<Integer>`，使用 Lambda 表达式过滤出所有偶数。
        //解法1.
        Predicate<Integer> predicate = number -> number % 2 == 0;
        List<Integer> collect = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream().filter(predicate).collect(Collectors.toList());
        collect.forEach(s-> System.out.println(s));
        //解法2.
        // 创建一个整数列表
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        // 使用 removeIf 和 Lambda 表达式过滤出偶数
        numbers.removeIf(n -> n % 2 != 0); // 移除所有奇数
        // 输出结果
        System.out.println(numbers); // 输出 [2, 4, 6, 8, 10]
        //解法3.
        Function<Integer, Integer> lengthFunc = integer -> {
            if (integer % 2 == 0) {
                return integer;
            }else {
                return null;
            }
        };
        new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).forEach(num -> {
            Integer apply = lengthFunc.apply(num);
            if (apply!=null){
                System.out.println(apply);
            }
        });

        //- 给定一个字符串列表 `List<String>`，使用 Lambda 表达式按字母顺序排序。
        //- 提示：使用 `sort` 方法。
        //解法1.
        List<String> list1 = new ArrayList<>(Arrays.asList("Charlie", "Alice", "Bob", "Eve", "David"));
        list1.sort(Comparator.comparing(String::toString));
        System.out.println(list1);
        //解法2.
        list1.sort(String::compareTo);
        System.out.println(list1);
        //解法2.倒序
        list1.sort(Comparator.reverseOrder());
        System.out.println(list1);

        //解法3.
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        list1.sort(comparator);
        System.out.println(list1);


        //**将两个数相加**
        //- 使用 Lambda 表达式实现一个 `BiFunction`，接受两个整数并返回它们的和。
        BiFunction<Integer, Integer, Integer> biFunction = (num1, num2) -> num1 + num2;
        Integer apply = biFunction.apply(5, 3);
        System.out.println(apply);


        //**自定义函数式接口**
        //- 定义一个函数式接口 `Calculator`，包含一个抽象方法 `int calculate(int a, int b)`。
        //- 使用 Lambda 表达式实现加法、减法和乘法。
        Calculator calculator = new Calculator() {
            @Override
            public int calcudate(int a, int b) {
                return a+b;
            }
        };

        System.out.println(calculator.calcudate(5,2));
        Calculator calculator1 = (num1, num2) -> num1 - num2;
        Calculator calculator2 = (num1,num2) -> num1 * num2;
        System.out.println(calculator1.calcudate(5,2));
        System.out.println(calculator2.calcudate(5,2));

        //**条件过滤**
        //- 给定一个 `Person` 对象列表，使用 Lambda 表达式过滤出年龄大于 18 岁的人。
        //- 提示：`Person` 类包含 `name` 和 `age` 属性。
        //解法1.
        Function<Person,Person> function = person -> {
          if (person.getAge()>18){
              return person;
          }else {
              return null;
          }
        };

        List<Person> list2 = new ArrayList<>(Arrays.asList(new Person("km", 20), new Person("zl", 17), new Person("ca", 6)));
        list2.forEach(p -> {
            Person apply1 = function.apply(p);
            if (apply1!=null){
                System.out.println(apply1.toString());
            }
        });

        //解法2.
        list2.forEach(p -> {
            if (p.getAge() > 18) {
                System.out.println(p);
            }
        });

        //**链式 Lambda**
        //- 使用 Lambda 表达式实现一个链式操作：先过滤出正数，然后将每个数乘以 2，最后求和。
        //- 提示：结合 `filter`、`map` 和 `reduce`。
        //解法1.
        BigDecimal reduce = Arrays.asList(new BigDecimal(-1.2), new BigDecimal(2.1), new BigDecimal(-5), new BigDecimal(3))
                .stream()
                .filter(num -> num.compareTo(BigDecimal.ZERO) > 0)
                .map(num -> num.multiply(new BigDecimal(2)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(reduce);


        //**异常处理**
        //- 使用 Lambda 表达式处理可能抛出异常的操作，例如将字符串转换为整数。
        //- 提示：使用 `try-catch` 块。
        // 创建一个字符串列表
        List<String> strings = Arrays.asList("1", "2", "abc", "4", "5", "def");

        // 使用 Stream API 和 flatMap 处理可能抛出异常的操作
        strings.stream()
                .flatMap(s -> {
                    try {
                        return java.util.stream.Stream.of(Integer.parseInt(s));
                    } catch (NumberFormatException e) {
                        return java.util.stream.Stream.empty(); // 忽略转换失败的字符串
                    }
                })
                .forEach(System.out::println);


        //**过滤出列表中的正数**
        //- 给定一个整数列表 `List<Integer>`，使用 Stream API 过滤出所有正数。
        //- 提示：使用 `filter`。
        Arrays.asList(1,-1,5,-6,7,-9).stream().filter(x -> x>0).forEach(System.out::println);

        //**将字符串列表转换为大写**
        //- 给定一个字符串列表 `List<String>`，使用 Stream API 将每个字符串转换为大写。
        //- 提示：使用 `map`。
        System.out.println(Arrays.asList("kunming","zilin","changan").stream().map(x -> x.toUpperCase()).collect(Collectors.toList()));

        //**计算列表中所有数的和**
        //- 给定一个整数列表 `List<Integer>`，使用 Stream API 计算所有数的和。
        //- 提示：使用 `reduce` 或 `sum`。
        System.out.println(Arrays.asList(5,7,8).stream().reduce(0,Integer::sum));

        //**去重**
        //- 给定一个整数列表 `List<Integer>`，使用 Stream API 去重。
        //- 提示：使用 `distinct`。
        System.out.println(Arrays.asList(1,1,2,3,5,5).stream().distinct().collect(Collectors.toList()));

        //**排序**
        //- 给定一个字符串列表 `List<String>`，使用 Stream API 按字母顺序排序。
        //- 提示：使用 `sorted`。
        ArrayList<String> strings1 = new ArrayList<>(Arrays.asList("zilin","kunming"));
        strings1.sort(String::compareTo);
        System.out.println(strings1);


        //**分组**
        //- 给定一个 `Person` 对象列表，使用 Stream API 按年龄分组。
        //- 提示：使用 `Collectors.groupingBy`。
        List<Person> list3 = Arrays.asList(new Person("k", 18), new Person("z", 18), new Person("a", 6), new Person("l", 7));
        Map<Integer, List<Person>> collect1 = list3.stream().collect(Collectors.groupingBy(p -> p.getAge()));
        System.out.println(collect1);


        //**统计**
        //- 给定一个整数列表 `List<Integer>`，使用 Stream API 统计最大值、最小值和平均值。
        //- 提示：使用 `Collectors.summarizingInt`。
        ToIntFunction<? super Integer> mapper = new ToIntFunction<Integer>() {
            @Override
            public int applyAsInt(Integer value) {
                return value;
            }
        };

        IntSummaryStatistics collect2 = Arrays.asList(1, 10, 21, 52, 80).stream().collect(Collectors.summarizingInt(mapper));
        System.out.println(collect2);


        //**扁平化**
        //- 给定一个嵌套列表 `List<List<Integer>>`，使用 Stream API 将其扁平化为一个单层列表。
        //- 提示：使用 `flatMap`。
        List<Integer> list4 = Arrays.asList(1, 5, 7, 8, 9, 10);
        List<List<Integer>> list5 = new ArrayList<>();
        list5.add(list4);

        System.out.println(list5.stream().flatMap(x -> x.stream()).collect(Collectors.toList()));

        //**并行流**
        //- 给定一个整数列表 `List<Integer>`，使用并行流计算所有数的和。
        //- 提示：使用 `parallelStream`。
        System.out.println(list4.parallelStream().reduce(0,Integer::sum));

        //**条件过滤**
        //- 给定一个 `Person` 对象列表，使用 Stream API 过滤出年龄大于 18 岁且名字以 "A" 开头的人。
        //- 提示：结合 `filter` 和多个条件。
        System.out.println(Arrays.asList(new Person("akli",16),new Person("ALICE",19),new Person("kun",26))
                .stream()
                .filter(person -> {
                 if (person.getName().startsWith("A")&& person.getAge() >=18){
                     return true;
                 }
                 return false;
                })
                .collect(Collectors.toList()));
        //优化
        System.out.println(Arrays.asList(new Person("akli",16),new Person("ALICE",19),new Person("kun",26))
                .stream()
                .filter(person -> person.getName().startsWith("A")&& person.getAge() >=18)
                .collect(Collectors.toList()));
    }







}
