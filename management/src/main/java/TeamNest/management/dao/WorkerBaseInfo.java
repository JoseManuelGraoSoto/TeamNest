package TeamNest.management.dao;

/*
    A record is a new way in Java since Java 16 of declare public final classes meaning inmutable classes,
    this classes can contain data, this class has AllArgsConstructor and also getters for all the properties
 */

public record WorkerBaseInfo(String dni, String name, String phoneNumber, String type) {}
