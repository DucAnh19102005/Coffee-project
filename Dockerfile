# ===== Build Stage =====
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
# "." bên phải COPY = thư mục hiện tại bên trong container
#vì trước đó ta có WORKDIR /app nên COPY pom.xml . = COPY pom.xml /app
#và cũng như vậy COPY src ./src = COPY src /app/src
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

#Docker biết phân stage nhờ vào FROM, stage đầu là build tạm sau đó xoá đi
#sau khi build xong thì dùng --from=builder để lấy target/.jar từ stage đầu (builder)
#việc này giúp giảm nhẹ image

# ===== Run Stage =====
FROM eclipse-temurin:21-jdk

WORKDIR /app

# "*" là ký tự đại diện (wildcard) = bất kỳ ký tự nào
COPY --from=builder /app/target/*.jar coffee-project.jar

EXPOSE 8080

#CMD ["java", "-jar", "coffee-project.jar"] giống ENTRYPOINT nhưng có thể bị ghi đè bằng lệnh
ENTRYPOINT ["java", "-jar", "coffee-project.jar"]