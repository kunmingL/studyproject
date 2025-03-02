# Java 8-17 主要新特性

## Java 8 (LTS)

### 1. Lambda 表达式
说明：
Lambda 表达式是一种匿名函数，可以将函数作为方法的参数传递。

新老版本对比：
老版本：
```java
// 匿名内部类方式
button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("按钮被点击");
    }
});
```

新版本：
```java
// Lambda表达式方式
button.addActionListener(e -> System.out.println("按钮被点击"));
```

使用场景：
- 函数式接口的实现
- 集合操作中的排序、过滤
- 事件处理
- 并行处理

优势：
- 代码更简洁
- 提高代码可读性
- 支持函数式编程
- 便于并行处理

### 2. Stream API
说明：
Stream API提供了一种新的数据处理方式，支持函数式操作。

新老版本对比：
老版本：
```java
List<String> filtered = new ArrayList<>();
for (String str : list) {
    if (str.length() > 3) {
        filtered.add(str.toUpperCase());
    }
}
```

新版本：
```java
List<String> filtered = list.stream()
    .filter(str -> str.length() > 3)
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

使用场景：
- 集合数据的过滤、转换
- 数据聚合计算
- 并行处理大数据集

优势：
- 链式操作更直观
- 支持并行处理
- 惰性计算提高效率
- 代码更简洁易读

拓展：

1.不支持链式调用的场景,链式调用是需要返回对象本身时，才支持调用，否则无法继续调用，只能分开调用：

- 方法的返回值不是对象本身，而是其他类型（如 boolean、int 等）。

- 方法的副作用需要被显式捕获或处理。

  2.Arrays.asList属性

  1.**`Arrays.asList` 生成的列表**：

  - 是固定大小的，**不支持结构性修改**（如 `add`、`remove`）。
  - **支持非结构性修改**（如 `set`、`sort`）。

  2.**完全不可变的列表**：

  - 使用 `Collections.unmodifiableList` 或 `List.of` 创建的列表是真正不可变的，**不支持任何修改**。*（java9+）*

### 3. Optional类
说明：
Optional类是一个可以为null的容器对象，用于避免空指针异常。

新老版本对比：
老版本：
```java
String name = null;
if (user != null) {
    if (user.getAddress() != null) {
        name = user.getAddress().getStreet();
    }
}
```

新版本：
```java
String name = Optional.ofNullable(user)
    .map(User::getAddress)
    .map(Address::getStreet)
    .orElse("默认地址");
```

使用场景：
- 方法返回值可能为空的情况
- 多层对象访问
- 空值处理

优势：
- 避免空指针异常
- 代码更加简洁优雅
- 强制开发者考虑空值情况

### 4. 新的日期时间API
说明：
Java 8引入了新的日期时间API，解决了旧版API的诸多问题。

新老版本对比：
老版本：
```java
Date date = new Date();
Calendar calendar = Calendar.getInstance();
calendar.setTime(date);
calendar.add(Calendar.DAY_OF_MONTH, 1);
```

新版本：
```java
LocalDate today = LocalDate.now();
LocalDate tomorrow = today.plusDays(1);
```

使用场景：
- 日期时间计算
- 时区处理
- 日期格式化

优势：
- 不可变对象，线程安全
- API设计更合理
- 功能更强大
- 使用更方便

### 5. 函数式接口
说明：
Java 8在`java.util.function`包中添加了大量函数式接口，用于支持Lambda表达式和函数式编程。主要包含以下几类：

#### 1) Consumer<T>
说明：接收一个参数，不返回结果
```java
// 老版本
void printString(String str) {
    System.out.println(str);
}

// 新版本
Consumer<String> printer = str -> System.out.println(str);
printer.accept("Hello World");
```

#### 2) Supplier<T>
说明：不接收参数，返回一个结果
```java
// 老版本
String getMessage() {
    return "Hello";
}

// 新版本
Supplier<String> messageSupplier = () -> "Hello";
String message = messageSupplier.get();
```

#### 3) Function<T,R>
说明：接收一个参数，返回一个结果
```java
// 老版本
Integer strLength(String str) {
    return str.length();
}

// 新版本
Function<String, Integer> lengthFunc = str -> str.length();
Integer length = lengthFunc.apply("Hello");
```

#### 4) Predicate<T>
说明：接收一个参数，返回boolean结果
```java
// 老版本
boolean isLongString(String str) {
    return str.length() > 5;
}

// 新版本
Predicate<String> isLong = str -> str.length() > 5;
boolean result = isLong.test("Hello World");
```

#### 5) BiFunction<T,U,R>
说明：接收两个参数，返回一个结果
```java
// 老版本
Integer sum(Integer a, Integer b) {
    return a + b;
}

// 新版本
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
Integer sum = add.apply(5, 3);
```

#### 6) UnaryOperator<T>
说明：接收一个参数，返回相同类型的结果
```java
// 老版本
String upperCase(String str) {
    return str.toUpperCase();
}

// 新版本
UnaryOperator<String> upper = str -> str.toUpperCase();
String result = upper.apply("hello");
```

#### 7) BinaryOperator<T>
说明：接收两个相同类型的参数，返回相同类型的结果
```java
// 老版本
Integer max(Integer a, Integer b) {
    return Math.max(a, b);
}

// 新版本
BinaryOperator<Integer> maxOperator = (a, b) -> Math.max(a, b);
Integer max = maxOperator.apply(10, 5);
```

#### 8) Comparator<T>
说明：用于定义对象的比较规则，Java 8中将Comparator改造成了函数式接口，并添加了许多默认方法

新老版本对比：
```java
// 老版本
Collections.sort(list, new Comparator<Person>() {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getAge().compareTo(p2.getAge());
    }
});

// 新版本 - Lambda表达式
Collections.sort(list, (p1, p2) -> p1.getAge().compareTo(p2.getAge()));

// 新版本 - 方法引用
list.sort(Comparator.comparing(Person::getAge));
```

增强功能：
```java
// 1. 多重排序
list.sort(Comparator.comparing(Person::getAge)         // 首先按年龄排序
                    .thenComparing(Person::getName));   // 年龄相同则按姓名排序

// 2. 倒序排序
list.sort(Comparator.comparing(Person::getAge).reversed());

// 3. 空值处理
list.sort(Comparator.nullsFirst(        // 将null值排在前面
         Comparator.comparing(Person::getName)));

// 4. 自定义比较器
list.sort(Comparator.comparing(
    Person::getAge,
    (a1, a2) -> Integer.compare(a1 % 10, a2 % 10)  // 按年龄的个位数排序
));
```

使用场景：
- 集合排序
- 优先队列排序规则
- TreeMap/TreeSet的排序规则
- 自定义排序逻辑

优势：
- 链式调用支持多重排序
- 内置空值处理
- 支持正序/倒序切换
- 支持方法引用
- 代码更简洁易读

使用场景：
- 集合操作（过滤、映射、归约等）
- 事件处理
- 回调函数
- 策略模式实现
- 数据验证
- 延迟计算

优势：
- 提供标准化的函数式接口
- 减少自定义接口的需求
- 支持方法引用
- 支持链式操作
- 提高代码复用性
- 增强API的灵活性

常见组合使用示例：
```java
// 组合多个函数式接口
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// 使用Predicate过滤
Predicate<String> startsWithA = name -> name.startsWith("A");

// 使用Function转换
Function<String, String> toUpper = String::toUpperCase;

// 使用Consumer输出
Consumer<String> printer = System.out::println;

names.stream()
    .filter(startsWithA)        // 使用Predicate
    .map(toUpper)              // 使用Function
    .forEach(printer);         // 使用Consumer
```

## Java 9

### 1. 模块系统(Jigsaw)
说明：
引入了模块化系统，允许创建模块化的JDK和应用。

使用场景：
```java
// module-info.java
module com.example.myapp {
    requires java.base;
    requires java.logging;
    exports com.example.myapp.api;
}
```

优势：
- 更好的封装性
- 显式依赖声明
- 更小的运行时映像
- 改进的性能

### 2. 集合工厂方法
说明：
提供了创建不可变集合的便捷方法。

新版本：
```java
List<String> list = List.of("a", "b", "c");
Set<String> set = Set.of("a", "b", "c");
Map<String, Integer> map = Map.of("a", 1, "b", 2);
```

使用场景：
- 创建小型不可变集合
- 测试代码
- 常量定义

优势：
- 代码更简洁
- 创建不可变集合更方便
- 性能优化

## Java 10

### 1. var局部变量类型推断
说明：
引入var关键字，支持局部变量的类型推断。

新老版本对比：
老版本：
```java
ArrayList<String> list = new ArrayList<String>();
HashMap<String, Integer> map = new HashMap<String, Integer>();
```

新版本：
```java
var list = new ArrayList<String>();
var map = new HashMap<String, Integer>();
```

使用场景：
- 局部变量声明
- 循环变量
- lambda表达式

优势：
- 减少类型声明的冗余
- 提高代码可读性
- 不影响类型安全

## Java 11 (LTS)

### 1. HTTP Client API
说明：
标准化的HTTP客户端API，支持HTTP/2。

新版本：
```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com"))
    .build();
HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());
```

使用场景：
- HTTP请求处理
- WebSocket通信
- 异步HTTP调用

优势：
- 支持HTTP/2
- 内置支持异步操作
- 替代HttpURLConnection

## Java 14-17

### 1. Switch 表达式（Java 14）
说明：
Java 12 引入了 Switch 表达式作为预览特性，并在 Java 14 中正式成为标准特性。它允许 Switch 语句直接返回值，并且语法更加简洁。

新老版本对比：
老版本（Java 8 及之前）：
```java
String dayType;
switch (day) {
    case "Monday":
    case "Tuesday":
    case "Wednesday":
    case "Thursday":
    case "Friday":
        dayType = "Weekday";
        break;
    case "Saturday":
    case "Sunday":
        dayType = "Weekend";
        break;
    default:
        throw new IllegalArgumentException("Invalid day: " + day);
}
```
新版本（Java 14 及之后）：
```java
String dayType = switch (day) {
    case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> "Weekday";
    case "Saturday", "Sunday" -> "Weekend";
    default -> throw new IllegalArgumentException("Invalid day: " + day);
};
```
使用场景：
需要根据某个变量的值返回不同结果的场景。

简化多重条件判断的代码。

优势：
简洁性：减少了冗余代码（如break语句）。

可读性：直接返回值，逻辑更清晰。

安全性：避免因遗漏break导致的逻辑错误。

### 2. Record类（Java 16）
说明：
Record是一种特殊的类，用于创建不可变的数据载体。

新版本：
```java
public record Person(String name, int age) {}
```

等价于传统类：
```java
public final class Person {
    private final String name;
    private final int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // 自动生成getter、equals、hashCode、toString
}
```

使用场景：
- DTO对象
- 数据传输
- 不可变数据结构

优势：
- 代码更简洁
- 自动实现常用方法
- 不可变性保证

### 3. 密封类（Java 17）
说明：
允许类限制哪些其他类可以继承它。

新版本：
```java
public sealed class Shape 
    permits Circle, Rectangle, Square {
    // 类定义
}

public final class Circle extends Shape {
    // 实现
}
```

使用场景：
- 领域模型设计
- 类型层次限制
- 模式匹配

优势：
- 更好的类型安全
- 明确的继承关系
- 支持模式匹配

### 4. 文本块（Java 15）
说明：
支持多行字符串文本。

新老版本对比：
老版本：
```java
String json = "{\n" +
              "  \"name\": \"John\",\n" +
              "  \"age\": 30\n" +
              "}";
```

新版本：
```java
String json = """
              {
                "name": "John",
                "age": 30
              }
              """;
```

使用场景：
- SQL语句
- JSON字符串
- HTML模板

优势：
- 更好的可读性
- 无需转义字符
- 保持原始格式

注：这里只列举了主要的新特性，还有许多其他改进，如GC优化、性能提升等。每个版本的完整特性列表可以参考Oracle官方文档。

