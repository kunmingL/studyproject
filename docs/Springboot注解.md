Springboot注解

---

## 1. 注解表格

| 注解名称                             | 功能描述                                                     | 使用场景                                   | 代码示例链接                |
| ------------------------------------ | ------------------------------------------------------------ | ------------------------------------------ | --------------------------- |
| **`@SpringBootApplication`**         | 标记主启动类，相当于 `@Configuration`、`@EnableAutoConfiguration` 和 `@ComponentScan` 的组合。 | 用于 Spring Boot 应用的入口类。            | [代码示例 1](#代码示例-1)   |
| **`@ConfigurationProperties`**       | 将配置文件中的属性绑定到 Java 对象中。                       | 用于读取配置文件中的属性并注入到 Bean 中。 | [代码示例 2](#代码示例-2)   |
| **`@EnableConfigurationProperties`** | 启用 `@ConfigurationProperties` 注解的类。                   | 用于启用配置属性类。                       | [代码示例 3](#代码示例-3)   |
| **`@RestController`**                | 组合了 `@Controller` 和 `@ResponseBody`，用于创建 RESTful Web 服务。 | 用于定义 RESTful API 的控制器。            | [代码示例 4](#代码示例-4)   |
| **`@RequestMapping`**                | 映射 HTTP 请求到控制器方法。                                 | 用于定义请求路径和请求方法。               | [代码示例 5](#代码示例-5)   |
| **`@GetMapping`**                    | 映射 HTTP GET 请求到控制器方法。                             | 用于定义 HTTP GET 方法的请求路径。         | [代码示例 6](#代码示例-6)   |
| **`@PostMapping`**                   | 映射 HTTP POST 请求到控制器方法。                            | 用于定义 HTTP POST 方法的请求路径。        | [代码示例 7](#代码示例-7)   |
| **`@RequestParam`**                  | 将 HTTP 请求参数绑定到方法参数。                             | 用于获取请求参数。                         | [代码示例 8](#代码示例-8)   |
| **`@PathVariable`**                  | 将 URL 路径中的变量绑定到方法参数。                          | 用于获取 URL 路径中的变量。                | [代码示例 9](#代码示例-9)   |
| **`@RequestBody`**                   | 将 HTTP 请求体绑定到方法参数。                               | 用于获取请求体中的数据。                   | [代码示例 10](#代码示例-10) |
| **`@Entity`**                        | 标记类为 JPA 实体。                                          | 用于定义数据库表对应的实体类。             | [代码示例 11](#代码示例-11) |
| **`@Repository`**                    | 标记类为数据访问层组件。                                     | 用于定义数据访问层（DAO）的类。            | [代码示例 12](#代码示例-12) |
| **`@Transactional`**                 | 声明方法或类需要事务管理。                                   | 用于需要事务管理的方法或类。               | [代码示例 13](#代码示例-13) |
| **`@SpringBootTest`**                | 标记测试类为 Spring Boot 集成测试类。                        | 用于编写集成测试。                         | [代码示例 14](#代码示例-14) |
| **`@MockBean`**                      | 在测试中注入 Mock 对象。                                     | 用于模拟依赖组件的行为。                   | [代码示例 15](#代码示例-15) |
| **`@Component`**                     | 标记类为 Spring 组件。                                       | 用于定义通用的 Spring 组件。               | [代码示例 16](#代码示例-16) |
| **`@Service`**                       | 标记类为服务层组件。                                         | 用于定义业务逻辑层的类。                   | [代码示例 17](#代码示例-17) |
| **`@Autowired`**                     | 自动注入依赖。                                               | 用于注入 Spring 管理的 Bean。              | [代码示例 18](#代码示例-18) |

---

## 2. 代码示例

### 代码示例 1: `@SpringBootApplication`

```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

### 代码示例 2: `@ConfigurationProperties`

```java
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String name;
    private String version;

    // Getters and Setters
}
```

### 代码示例 3: `@EnableConfigurationProperties`

```java
@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

### 代码示例 4: `@RestController`

```java
@RestController
public class MyController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
```

### 代码示例 5: `@RequestMapping`

```java
@RestController
@RequestMapping("/api")
public class MyController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
```

### 代码示例 6: `@GetMapping`

```java
@RestController
public class MyController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
```

### 代码示例 7: `@PostMapping`

```java
@RestController
public class MyController {
    @PostMapping("/hello")
    public String postHello() {
        return "Hello, POST!";
    }
}
```

### 代码示例 8: `@RequestParam`

```java
@RestController
public class MyController {
    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Hello, " + name + "!";
    }
}
```

### 代码示例 9: `@PathVariable`

```java
@RestController
public class MyController {
    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return "Hello, " + name + "!";
    }
}
```

### 代码示例 10: `@RequestBody`

```java
@RestController
public class MyController {
    @PostMapping("/greet")
    public String greet(@RequestBody User user) {
        return "Hello, " + user.getName() + "!";
    }
}
```

### 代码示例 11: `@Entity`

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Getters and Setters
}
```

### 代码示例 12: `@Repository`

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
```

### 代码示例 13: `@Transactional`

```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
```

### 代码示例 14: `@SpringBootTest`

```java
@SpringBootTest
public class MyIntegrationTest {
    @Autowired
    private MyService myService;

    @Test
    public void testService() {
        assertNotNull(myService);
    }
}
```

### 代码示例 15: `@MockBean`

```java
@SpringBootTest
public class MyIntegrationTest {
    @MockBean
    private MyRepository myRepository;

    @Test
    public void testService() {
        when(myRepository.findAll()).thenReturn(Arrays.asList(new MyEntity()));
        assertNotNull(myService.findAll());
    }
}
```

### 代码示例 16: `@Component`

```java
@Component
public class MyComponent {
    public void doSomething() {
        System.out.println("Doing something...");
    }
}
```

### 代码示例 17: `@Service`

```java
@Service
public class MyService {
    public void doSomething() {
        System.out.println("Doing something...");
    }
}
```

### 代码示例 18: `@Autowired`

```java
@Service
public class MyService {
    @Autowired
    private MyRepository myRepository;

    public void doSomething() {
        myRepository.findAll();
    }
}
```

---

通过以上表格和代码示例，可以快速查阅 Spring Boot 中常用注解的功能、使用场景和示例代码。如果你有更多问题，欢迎继续交流！