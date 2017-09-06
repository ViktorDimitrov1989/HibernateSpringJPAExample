package app.utils;

public interface Serializer {

    <T> void serialize(T obj, String fileName);

    <T> T deserialize(Class<T> clazz, String fileName);

}
