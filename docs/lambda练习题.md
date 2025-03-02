## **Lambda 表达式练习题**

### **初级难度**
1. **将列表中的字符串转换为大写**
    - 给定一个字符串列表 `List<String>`，使用 Lambda 表达式将每个字符串转换为大写。
    - 提示：使用 `forEach` 或 `replaceAll`。

2. **使用 Lambda 实现 Runnable**
    - 使用 Lambda 表达式创建一个 `Runnable` 任务，并打印 `"Hello, Lambda!"`。

3. **过滤出列表中的偶数**
    - 给定一个整数列表 `List<Integer>`，使用 Lambda 表达式过滤出所有偶数。
    - 提示：使用 `removeIf`。

4. **排序字符串列表**
    - 给定一个字符串列表 `List<String>`，使用 Lambda 表达式按字母顺序排序。
    - 提示：使用 `sort` 方法。

5. **将两个数相加**
    - 使用 Lambda 表达式实现一个 `BiFunction`，接受两个整数并返回它们的和。

---

### **中级难度**
6. **自定义函数式接口**
    - 定义一个函数式接口 `Calculator`，包含一个抽象方法 `int calculate(int a, int b)`。
    - 使用 Lambda 表达式实现加法、减法和乘法。

7. **条件过滤**
    - 给定一个 `Person` 对象列表，使用 Lambda 表达式过滤出年龄大于 18 岁的人。
    - 提示：`Person` 类包含 `name` 和 `age` 属性。

8. **链式 Lambda**
    - 使用 Lambda 表达式实现一个链式操作：先过滤出正数，然后将每个数乘以 2，最后求和。
    - 提示：结合 `filter`、`map` 和 `reduce`。

9. **异常处理**
    - 使用 Lambda 表达式处理可能抛出异常的操作，例如将字符串转换为整数。
    - 提示：使用 `try-catch` 块。

10. **方法引用**
    - 使用方法引用简化以下 Lambda 表达式：`(s) -> System.out.println(s)`。

---

## **Stream API 练习题**

### **初级难度**
1. **过滤出列表中的正数**
    - 给定一个整数列表 `List<Integer>`，使用 Stream API 过滤出所有正数。
    - 提示：使用 `filter`。

2. **将字符串列表转换为大写**
    - 给定一个字符串列表 `List<String>`，使用 Stream API 将每个字符串转换为大写。
    - 提示：使用 `map`。

3. **计算列表中所有数的和**
    - 给定一个整数列表 `List<Integer>`，使用 Stream API 计算所有数的和。
    - 提示：使用 `reduce` 或 `sum`。

4. **去重**
    - 给定一个整数列表 `List<Integer>`，使用 Stream API 去重。
    - 提示：使用 `distinct`。

5. **排序**
    - 给定一个字符串列表 `List<String>`，使用 Stream API 按字母顺序排序。
    - 提示：使用 `sorted`。

---

### **中级难度**
6. **分组**
    - 给定一个 `Person` 对象列表，使用 Stream API 按年龄分组。
    - 提示：使用 `Collectors.groupingBy`。

7. **统计**
    - 给定一个整数列表 `List<Integer>`，使用 Stream API 统计最大值、最小值和平均值。
    - 提示：使用 `Collectors.summarizingInt`。

8. **扁平化**
    - 给定一个嵌套列表 `List<List<Integer>>`，使用 Stream API 将其扁平化为一个单层列表。
    - 提示：使用 `flatMap`。

9. **并行流**
    - 给定一个整数列表 `List<Integer>`，使用并行流计算所有数的和。
    - 提示：使用 `parallelStream`。

10. **条件过滤**
    - 给定一个 `Person` 对象列表，使用 Stream API 过滤出年龄大于 18 岁且名字以 "A" 开头的人。
    - 提示：结合 `filter` 和多个条件。

---

### **高级难度**
11. **复杂对象转换**
    - 给定一个 `Person` 对象列表，使用 Stream API 将其转换为 `Map<String, Integer>`，其中键是名字，值是年龄。
    - 提示：使用 `Collectors.toMap`。

12. **自定义收集器**
    - 实现一个自定义收集器，将整数列表 `List<Integer>` 中的所有偶数收集到一个新的列表中。
    - 提示：使用 `Collector.of`。

13. **流的分页**
    - 给定一个整数列表 `List<Integer>`，使用 Stream API 实现分页功能（每页 10 条数据）。
    - 提示：结合 `skip` 和 `limit`。

14. **流的短路操作**
    - 给定一个整数列表 `List<Integer>`，使用 Stream API 找到第一个大于 100 的数。
    - 提示：使用 `findFirst`。

15. **流的性能优化**
    - 给定一个大型整数列表 `List<Integer>`，使用 Stream API 计算所有数的和，并比较串行流和并行流的性能。
    - 提示：使用 `System.currentTimeMillis` 计时。

---

## **练习题答案示例**

### **Lambda 表达式示例**
#### 1. 将列表中的字符串转换为大写
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.replaceAll(String::toUpperCase);
System.out.println(names); // 输出 [ALICE, BOB, CHARLIE]
```

#### 2. 使用 Lambda 实现 Runnable
```java
Runnable task = () -> System.out.println("Hello, Lambda!");
new Thread(task).start();
```

---

### **Stream API 示例**
#### 1. 过滤出列表中的正数
```java
List<Integer> numbers = Arrays.asList(-1, 2, -3, 4, -5);
List<Integer> positives = numbers.stream()
                                 .filter(n -> n > 0)
                                 .collect(Collectors.toList());
System.out.println(positives); // 输出 [2, 4]
```

#### 2. 将字符串列表转换为大写
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
List<String> upperCaseNames = names.stream()
                                   .map(String::toUpperCase)
                                   .collect(Collectors.toList());
System.out.println(upperCaseNames); // 输出 [ALICE, BOB, CHARLIE]
```

---

## **总结**
- **Lambda 表达式** 练习题涵盖基本语法、函数式接口、条件过滤等。
- **Stream API** 练习题涵盖过滤、映射、排序、分组、并行流等。
- 通过练习这些题目，你可以逐步掌握 Lambda 和 Stream API 的核心用法。

补充：

### **Lambda 表达式**

1. 使用 Lambda 表达式实现一个自定义的函数式接口。
2. 在 Lambda 表达式中处理异常，例如将字符串转换为整数时捕获 `NumberFormatException`。

### **Stream API**

1. 使用 `Collectors.groupingBy` 对对象列表按某个属性分组。
2. 使用 `flatMap` 处理嵌套集合（如 `List<List<Integer>>`）。
3. 使用 `parallelStream` 实现并行计算，并比较性能。

### **BigDecimal**

1. 使用 `MathContext` 控制 `BigDecimal` 的精度和舍入模式。
2. 实现一个复杂的货币计算场景，例如计算税费、折扣等。