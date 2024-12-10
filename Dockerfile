# 使用官方的OpenJDK镜像作为基础镜像
FROM openjdk:22-jdk

# 设置环境变量
ENV APP_HOME=/usr/app/

# 创建应用目录
WORKDIR $APP_HOME

# 将构建的Spring Boot JAR包复制到Docker镜像中
COPY target/train-ticket-system-0.0.1-SNAPSHOT.jar app.jar

# 暴露应用的端口
EXPOSE 8080

# 运行Spring Boot应用程序
ENTRYPOINT ["java", "-jar", "app.jar"]
