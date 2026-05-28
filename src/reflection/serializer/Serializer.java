package reflection.serializer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Serializer {

    public String correctValue(String value) {
        return value.replace("%", "%25").replace(":", "%3a").replace("|", "%7c");
   }

    public String deCorrectValue(String value) {
        return value.replace("%25", "%").replace("%3a", ":").replace("%7c", "|");
    }

    public String serialize(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();

        List<String> pairs = new ArrayList<>();
        for (Field field : fields) {
            try {
                if (!field.isAnnotationPresent(Stored.class)) {
                    continue;
                }
                field.setAccessible(true);

                Stored stored = field.getAnnotation(Stored.class);

                String key = (stored == null || stored.value().isEmpty())
                        ? field.getName()
                        : stored.value();
                Object value = field.get(instance);

                pairs.add("%s:%s".formatted(key, correctValue(String.valueOf(value))));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return String.join("|", pairs);
    }

    public <T> T deserialize(String inputString,
                            Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();

            for (String field : inputString.split("\\|")) {
                String[] name_value = field.split(":");

                Field f = clazz.getDeclaredField(name_value[0]);
                f.setAccessible(true);

                Object value = deCorrectValue(name_value[1]);

                try {
                    if (f.getType() == int.class) {
                        value = Integer.parseInt((String) value);
                    }
                } catch (Exception ignored) {}
                f.set(instance, value);
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
