# 使用 JDK 17 的基础镜像
FROM openjdk:17

# 设置工作目录
WORKDIR /app

# 将本地的 JAR 文件复制到容器中
COPY target/dataframe4j-fake-*.jar /app/dataframe4j.jar

# 在容器内执行 JAR 文件
CMD ["java", "-jar", "dataframe4j.jar"]

