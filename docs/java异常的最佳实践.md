---

### **异常处理最佳实践总结**

---

#### **一、异常设计原则**
1. **区分异常类型**  
   - **检查型异常（Checked Exceptions）**：  
     表示调用方必须处理的业务错误（如文件未找到、数据库连接失败）。  
     - **使用场景**：可恢复错误，需强制调用方处理。  
     - **声明方式**：在方法签名中显式声明 `throws`。  
   - **非检查型异常（Unchecked Exceptions）**：  
     表示程序错误或不可恢复错误（如空指针、参数校验失败）。  
     - **使用场景**：不可恢复错误，无需强制调用方处理。  
     - **声明方式**：无需在方法签名中声明，但建议通过文档说明。

2. **自定义异常**  
   - **分层设计**：  
     - **DAO层**：抛出数据访问异常（如 `DataAccessException`）。  
     - **Service层**：抛出自定义业务异常（如 `OrderNotFoundException`）。  
     - **Controller层**：统一处理异常并转换为标准化响应。  
   - **携带上下文信息**：  
     - 在异常中添加错误码、时间戳、业务参数等字段，便于定位问题。  
     - 示例：  
       ```java
       public class PaymentFailedException extends RuntimeException {
           private String paymentId;
           private String errorCode;
           // 构造函数、Getter、上下文方法...
       }
       ```

---

#### **二、编码实践**
1. **精准捕获与抛出**  
   - **最小粒度捕获**：优先捕获具体异常类型，避免直接捕获 `Exception`。  
     ```java
     try {
         someOperation();
     } catch (FileNotFoundException e) {
         // 处理文件未找到
     } catch (IOException e) {
         // 处理其他IO异常
     }
     ```
   - **避免冗余声明**：仅在方法实际可能抛出异常时使用 `throws`。

2. **防御性编程**  
   - **空值检查**：  
     ```java
     public void process(String input) {
         if (input == null) {
             throw new InvalidParameterException("input不能为空");
         }
         // 安全操作
     }
     ```
   - **边界校验**：  
     ```java
     public void accessArray(int[] array, int index) {
         if (index < 0 || index >= array.length) {
             throw new IndexOutOfBoundsException("索引越界");
         }
         // 安全访问
     }
     ```

3. **全局异常处理**  
   - **Spring Boot 的 `@ControllerAdvice`**：  
     ```java
     @ControllerAdvice
     public class GlobalExceptionHandler {
         @ExceptionHandler(Exception.class)
         public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
             // 记录日志并返回标准化响应
             return ResponseEntity.status(500).body(new ErrorResponse(ex));
         }
     }
     ```
   - **日志记录**：确保异常堆栈和上下文信息完整输出。  
     ```java
     logger.error("业务处理失败 - 订单ID: {}, 错误: {}", orderId, ex.getMessage(), ex);
     ```

---

#### **三、工具链与配置**
1. **编译配置**  
   - 启用调试信息（`-g` 参数），确保异常堆栈包含行号。  
   - **Maven 配置示例**：  
     ```xml
     <plugin>
         <groupId>org.apache.maven.plugins</groupId>
         <artifactId>maven-compiler-plugin</artifactId>
         <configuration>
             <compilerArgs>
                 <arg>-g</arg> <!-- 生成行号信息 -->
             </compilerArgs>
         </configuration>
     </plugin>
     ```

2. **日志框架配置**  
   - **Logback 配置示例**（输出完整堆栈）：  
     ```xml
     <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n%ex{full}</pattern>
     ```

3. **APM 与监控工具**  
   - **Sentry**：集中化错误追踪，支持代码行号关联。  
   - **SkyWalking/New Relic**：全链路追踪，定位异常发生的服务节点。  
   - **ELK（Elasticsearch + Logstash + Kibana）**：日志聚合与分析。

---

#### **四、常见问题与解决**
1. **异常未显示行号**  
   - **检查编译配置**：确保 `-g` 参数启用。  
   - **避免代码混淆**：生产环境打包时保留行号信息。

2. **异常信息不完整**  
   - **增强日志上下文**：记录异常发生时的参数、环境变量等。  
   - **自定义异常**：通过 `addContext()` 方法附加业务数据。

3. **高并发下异常定位难**  
   - **请求ID贯穿链路**：在日志中关联请求ID，便于追踪完整调用链。  
   - **异步线程异常处理**：  
     ```java
     ExecutorService executor = Executors.newFixedThreadPool(10);
     executor.submit(() -> {
         try {
             someTask();
         } catch (Exception e) {
             logger.error("异步任务失败", e);
         }
     });
     ```

---

#### **五、示例：端到端异常处理**
```java
// 1. 自定义异常（携带上下文）
public class OrderException extends RuntimeException {
    private String orderId;
    private String errorCode;

    public OrderException(String errorCode, String orderId, String message) {
        super(message);
        this.errorCode = errorCode;
        this.orderId = orderId;
    }

    public String getContext() {
        return String.format("[%s] OrderID=%s, Error=%s", errorCode, orderId, getMessage());
    }
}

// 2. Service层抛出异常
public class OrderService {
    public void processOrder(String orderId) {
        if (orderId == null) {
            throw new OrderException("ORDER_INVALID", orderId, "订单ID为空");
        }
        // 业务逻辑...
    }
}

// 3. 全局异常处理（记录日志并返回JSON）
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderException.class)
    public ResponseEntity<?> handleOrderException(OrderException ex) {
        logger.error("订单处理失败: {}", ex.getContext(), ex);
        return ResponseEntity.badRequest().body(Map.of(
            "code", ex.getErrorCode(),
            "message", ex.getMessage(),
            "orderId", ex.getOrderId()
        ));
    }
}
```

---

### **总结**
通过 **精准的异常设计、防御性编程、全局处理机制和工具链支持**，可以显著提升系统的健壮性和可维护性。关键点包括：  
- **分层异常设计**：区分业务异常与系统错误。  
- **上下文信息丰富化**：异常携带业务参数、错误码等。  
- **日志与监控全覆盖**：确保异常可追踪、可分析。  
- **工具链集成**：APM、错误追踪系统助力快速定位问题。  

遵循这些最佳实践，团队可以高效协作，降低生产环境故障排查成本。