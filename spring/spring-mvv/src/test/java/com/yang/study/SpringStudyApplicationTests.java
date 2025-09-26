package com.yang.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringStudyApplicationTests {
    
    @Test
    void contextLoads() {
        Gson gson = new Gson();
        JsonObject result = gson.fromJson(message, JsonObject.class).deepCopy();
        for (Map.Entry<String, JsonElement> entry : gson.fromJson(structureJson, JsonObject.class).entrySet()) {
            String key = entry.getKey();
            JsonElement value2 = entry.getValue();
            if (result.has(key)) {
                JsonElement value1 = result.get(key);
                if (value1.isJsonNull() && !value2.isJsonNull()) {
                    result.add(key, value2);
                }
            } else {
                result.add(key, value2);
            }
        }
        return gson.toJson(result);
    }
    
}
