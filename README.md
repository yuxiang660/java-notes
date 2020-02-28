# 第一个Java程序
- 源文件：*.java
- 中间文件：*.class
- 源文件经`javac`编译成字节码文件`*.class`，然后用`java`执行类名；或直接`java`命令运行源文件`*.java`(java11新功能，只支持单个源文件，要求Main类要在最前面)。
- 一个Java源码只能定义一个`public`类型的class，并且class名称和文件名要完全一致。
- 三个基本概念
    - `public`类
    - `public`函数
    - `static`函数
- package包
    - 如果Main类属于某一个包，必须在根目录下执行`java+类名`，如：`java code.inherit.Main`

# Java基本数据类型
- [size-of-types](./code/type/DataType.java)
- 基本类型变量：
    - 整数类型：byte(1字节), short(2字节), int(4字节), long(8字节)
    - 浮点数类型：float(4字节), double(8字节)
    - 字符类型：char(2字节，支持Unicode)
    - 布尔类型：boolean(长度由机器决定)
- 常量：
    - `final`类似C++中的`const`，如`final double PI = 3.14;`
- 引用类型变量（class和interface，相当于C++的指针）：
    - [各种引用类型的测试](./code/reference/Main.java)
    - 强引用（最常见的，如：`Object obj = new Object()`）
    - 软引用（SoftReference, 会在内存不足时，被回收）
    - 弱引用（WeakReference, 比SoftReference更弱，每次垃圾回收时，都会被收回）
    - 虚引用（PhantomReference, 最弱，对象是否被收回，和引用完全无关）
    - `var`类似C++中的`auto`，如`var sb = new StringBuilder();`
- 引用类型可以赋值为`null`，但基本类型不能赋值为`null`。

# Class类
- 类成员的4种访问权限：
    - public：外部包可访问
    - protected：外部包子类可访问
    - private：只能本包本类访问
    - default：本包其他类可访问
- 构造方法
    - Java的构造方法，必须用`new`操作符调用
    - 创建类实例对象的顺序：
        - 初始化成员。如果指定了成员初始值，就用此值。如果没有，就用默认值。
        - 执行构造函数进行初始化。

# 数组类型
- 声明（不指定大小，因为数组是一种引用类型变量）：
    - `type[] arrayName;` （推荐）
    - `type arrayName[];` （不推荐）

# 继承
- `super`关键字
    - 用于引用父类字段，如：`super.fieldName`
    - 调用父类构造方法，类似C++构造函数中调用时，需要先调用父类的构造函数。
    - 子类可以通过`super`调用父类被覆写了的方法。
- override覆写
    - `@Override`只起到编译时期检查的作用，不写也不会影响运行时的功能。
    - `final`阻止此函数被覆写。

# abstract抽象类和interface接口的区别
- 接口是纯虚的，不能实现，可以定义`default`方法；abstract可以有默认实现。
- 接口中定义的变量是`final`的。
- 接口中的成员默认都是`public`的。
- 多个接口可以被一个类实现，abstract只能单继承。

# 静态字段和静态方法
- `static`字段共享空间
- `static`方法调用，不需要实例对象。如：`类名.静态方法名()`
- 接口的静态字段，接口的成员必须是`static final`的。
```java
public interface Person {
    public static final int MALE = 1; // 等同： int MALE = 1;
    public static final int FEMALE = 2; // 等同： int FEMALE = 1;
}
```

# 包
- Java虚拟机执行的时候，JVM只看完整类名。因此，只要包名不同，类就不同。
- default的作用域是包级的。
- 引用其他包中的类，方法：
    - 直接写出完整的类名：包名.类名
    - `import`导入类名：`import mr.jun.Arrays`，或`import mr.jun.*`
- JDK的核心类使用`java.lang`包，编译器会自动导入：`import java.lang.*`
- 编译器除了会帮我们导入核心包，还会导入当前`package`的其他`class`。

# class路径查找顺序
- 假设`classpath`是`.;C:\work\project1\bin;C:\shared`(默认是当前目录)，当JVM在加载`abc.xyz.Hello`这个类时，会依次查找：
    - <当前目录>\abc\xyz\Hello.class
    - C:\work\project1\bin\xyz\Hello.class
    - C:\shared\abc\xyz\Hello.class

# jar包
- jar查找
    `jar`是`.class`的集合，可通过以下命令指定jar路径。
    ```
    java -cp ./hello.jar abc.xyz.Hello
    ```

# jmod模块
- 模块的作用是声明依赖关系
- 模块构建流程
```
javac -d bin src/module-info.java src/com/itranswarp/sample/*.java
jar --create --file hello.jar --main-class com.itranswarp.sample.Main -C bin .
jmod create --class-path hello.jar hello.jmod
```
- 运行模块
```
java --module-path hello.jar --module hello.world
```
- 打包JRE
```
jlink --module-path hello.jmod --add-modules java.base,java.xml,hello.world --output jre/
jre/bin/java --module hello.world
```

# String
- Java字符串**不可变**，对字符串的操作不改变原字符串，而是返回一个新字符串。
- 两个字符串比较，必须总是使用`equals()`方法。
- 和`char[]`相互转换
    ```java
    char[] cs = "Hello".toCharArray();
    String s = new String(cs);
    ```
## 字符编码
- `char`: 两个字节的`Unicode`
- `Unicode`转换成其他编码
    ```java
    byte[] b1 = "Hello".getBytes(); // 按系统默认编码转换，不推荐
    byte[] b2 = "Hello".getBytes("UTF-8"); // 按UTF-8编码转换
    byte[] b2 = "Hello".getBytes("GBK"); // 按GBK编码转换
    byte[] b3 = "Hello".getBytes(StandardCharsets.UTF_8); // 按UTF-8编码转换
    ```
- 把已知编码的`byte[]`转换为`String`
    ```java
    byte[] b = ...
    String s1 = new String(b, "GBK"); // 按GBK转换
    String s2 = new String(b, StandardCharsets.UTF_8); // 按UTF-8转换
    ```

# 包装类型
- 作用：基本类型转换成引用类型
- 所有包装类都是不变类
- 创建`Integer`实例方法：
    - `Integer n = new Integer(100);`
    - `Integer n = Integer.valueOf(100);`，此方法更好，因为可以被编译器优化，不用每次都创建一个新的实例。

# 异常
## Java的异常
- Error表示严重的错误，程序对此一般无能为力
    - OutOfMemoryError: 内存耗尽
    - NoClassDefFoundError: 无法加载某个Class
    - StackOverflowError: 栈溢出
- Exception是运行时错误，它可以被捕获处理
    - NumberFormatException: 数值类型的格式错误
    - FileNotFoundException: 未找到文件
    - SocketException: 读取网络失败
- Java规定，`RuntimeException`无需强制捕获，非`RuntimeException`（如`Checked Exception`）需要强制捕获，或者用`throws Exception`声明。
- Java标准库定义的常用异常包括：
```
Exception
│
├─ RuntimeException
│  │
│  ├─ NullPointerException
│  │
│  ├─ IndexOutOfBoundsException
│  │
│  ├─ SecurityException
│  │
│  └─ IllegalArgumentException
│     │
│     └─ NumberFormatException
│
├─ IOException
│  │
│  ├─ UnsupportedCharsetException
│  │
│  ├─ FileNotFoundException
│  │
│  └─ SocketException
│
├─ ParseException
│
├─ GeneralSecurityException
│
├─ SQLException
│
└─ TimeoutException
```
## assert断言
- 开启assert检查
```
java -ea Main.java
```

# 反射
反射就是`Reflection`，指程序在运行期间可以拿到一个对象的所有信息。
## 如何获取一个`class`的`Class`实例？
通过`Class`实例获取`class`信息的方法称为反射(Reflection)。
- 直接通过一个`class`的静态变量`class`获取：
```
Class cls = String.class
```
- 如果我们有一个实例变量，可以通过该实例变量提供的`getClass()`方法获取：
```
String s = "Hello";
Class cls = s.getClass();
```
- 如果知道一个`class`的完整类名，可通过静态方法`Class.forName()`获取：
```
Class cls = Class.forName("java.lang.String");
```
## `Class`实例和`instanceof`的差别
```java
Integer n = new Integer(123);

boolean b1 = n instanceof Integer; // true，因为n是Integer类型
boolean b2 = n instanceof Number; // true，因为n是Number类型的子类

boolean b3 = n.getClass() == Integer.class; // true，因为n.getClass()返回Integer.class
boolean b4 = n.getClass() == Number.class; // false，因为Integer.class!=Number.class
```

# @注解
- 注解的分类
    - `SOURCE`类型，编译器使用的注解, 编译后就被扔掉了
        - `@Override`
        - `@SuppressWarnings`
    - `CLASS`类型，工具处理`.class`文件使用的注解
    - `RUNTIME`类型，运行期能读取的注解

# 泛型
- `<T>`不能是基本类型，例如`int`，因为实际类型是`Object`，`Object`类型无法持有基本类型。
- 所有泛型实例，无论`T`的类型是什么，`getClass()`返回同一个`Class`实例，因为编译后它们全部都是`Pair<Object>`。
- 无法判断带泛型的`Class`
```java
Pair<Integer> p = new Pair<>(123, 456);
// Compile error，并不存在Pair<String>.class，而是唯一的Pair.class
if (p instanceof Pair<String>.class) {
}
```
- 不能实例化`T`类型
```java
public class Pair<T> {
    private T first;
    private T last;
    public Pair() {
        // Compile error:
        first = new T();
        last = new T();
    }
}
```

## 对比extends和super通配符
- `<? extends T>`类型和`<? super T>`类型的区别在于：
    - `<? extends T>`允许调用读方法`T get()`获取`T`的引用，但不允许调用写方法`set(T`)传入`T`的引用（传入null除外）；
    - `<? super T>`允许调用写方法`set(T)`传入`T`的引用，但不允许调用读方法`T get()`获取`T`的引用（获取Object除外）。
- PECS原则： Producer Extends Consumer Super
- `<?>`无限定通配符, 因为`<?>`通配符既没有`extends`，也没有`super`。既不能读，也不能写。因此：
    - 不允许调用set(T)方法并传入引用（null除外）；
    - 不允许调用T get()方法并获取T引用（只能获取Object引用）。
- 无限定通配符`<?>`很少使用，可以用`<T>`替换，同时它是所有`<T>`类型的超类。

# 集合
- 数组的限制：
    - 数组初始化后大小不可变；
    - 数组只能按索引顺序存取。
- `java.util`包提供了以下三种类型的集合：
    - `List`接口：一种有序列表的集合
    - `Set`接口：一种保证没有重复元素的集合
    - `Map`接口：一种映射集合
- `Collection`是除`Map`外所有其他集合类的根接口。

# Maven
- 依赖关系Scope
    - compile - 编译时需要用到该jar包（默认）
    - test - 编译Test时需要用到该jar包
    - runtime - 编译时不需要，但运行时需要用到
    - provided - 编译时需要用到，但运行时由JDK或某个服务器提供
- 常用命令
    - mvn clean
    - mvn clean compile
    - mvn clean test
    - mvn clean package
- Maven内置插件
    - clean : for clean
    - compiler : for compile
    - surefire : for test
    - jar : for package
- package打包类型
    - pom ：pom层级结构
    - jar : 默认类型
    - war ：需要部署的项目打包成war，如果只是内部调用或者时作为服务使用，则推荐打包成jar类型。
## 如何创建Maven自定义插件
使用`maven-shade-plugin`创建一个可执行的jar，要使用这个插件，需要在`pom.xml`中声明它：
- 插件声明
    ```xml
    <project>
        ...
        <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-shade-plugin</artifactId>
                    <version>3.2.1</version>
                    <executions>
                        <execution>
                            <phase>package</phase>
                            <goals>
                                <goal>shade</goal>
                            </goals>
                            <configuration>
                                ...
                            </configuration>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </build>
    </project>
    ```
- 配置插件
```xml
<configuration>
    <transformers>
        <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
            <mainClass>com.itranswarp.learnjava.Main</mainClass>
        </transformer>
    </transformers>
</configuration>
```
