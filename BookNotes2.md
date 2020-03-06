---
《JAVA Web高级编程》读书笔记
---

# 第1章 介绍Java EE平台
- Java技术栈被分割为以下关键部分：
    - Java - 一门强类型语言
    - J2SE - Java应用基础，包括平台以及`java.lang`和`java.io`包中的类
    - JVM - Java虚拟机
    - JDK - Java开发工具包
    - JRE - Java运行时环境
- Web应用程序结构
    - Servlet - 用于接受和响应HTTP请求的Java类。
    - 过滤器 - 拦截发送给Servlet的请求，可用于数据格式化、返回数据压缩、认证或授权。
    - 监听器 - 监听器可以通知代码多种事情，例如应用程序启动、关闭、HTTP会话创建或销毁。
    - JSP - JavaServer Pages技术，创建动态的、基于HTML的图形用户界面。
- WEB-INF目录结构， WEB-INF是Java的WEB应用的安全目录。客户端无法访问，只有服务端可以访问。如果想在页面中直接访问其中的文件，必须通过`web.xml`文件对要访问的文件进行相应的映射才能访问。
    - `/WEB-INF/web.xml` - Web应用程序配置文件，描述了servlet和其他的应用组件配置及命名规则。
    - `/WEB-INF/classes/` - 包含了站点所有有用的class文件，包括servlet class和非servlet class，他们不能包含在.jar文件中。
    - `/WEB-INF/lib/` - 存放web应用需要的各种JAR文件，放置仅在这个应用中要求使用的jar文件，如数据库驱动jar文件。
    - `/WEB-INF/src/` - 源码目录，按照包名结构放置各个Java文件。
    - `/WEB-INF/database.properties` - 数据库配置文件
    - `/WEB-INF/tags/` - 存放了自定义标签文件，该目录并不一定为tags，可以根据自己的喜好和习惯为自己的标签文件库命名。
    - `/WEB-INF/jsp/` - jsp 1.2以下版本的文件放置位置。
    - `/WEB-INF/jsp2/` - 存放jsp 2.0以下版本的文件。
    - `META-INF` - 存放一些meta information相关的文件的这么一个文件夹。程序入口相关信息，每个jar包中都会有这个文件夹。

# 第3-4章 创建第一个Servlet和JSP
- 基于注解的配置的优缺点
    - 优势：避免了XML配置，并且注解非常直接和简洁。
    - 劣势：单个Servlet创建多个实例时，只能XML配置。
- JSP结构：
```
<%@ 这是一个指令 %>
<%! 这是一个声明 %>
<% 这是一个脚本 %>
<%= 这是一个表达式 %>
```
