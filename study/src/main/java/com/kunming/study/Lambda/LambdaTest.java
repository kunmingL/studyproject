package com.kunming.study.Lambda;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
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
    }




}
