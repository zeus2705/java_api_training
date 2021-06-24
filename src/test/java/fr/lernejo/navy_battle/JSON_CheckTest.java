package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSON_CheckTest {

    @Test
    void GoodStartRequest()
    {
        JSON_Check check = new JSON_Check();
        Assertions.assertEquals(true,check.ValidateStartRequest("{\"id\": \"2aca7611-0ae4-49f3-bf63-75bef4769028\",\"url\": \"http://localhost:9876\",\"message\": \"May the best code win\"}"),"A good request returned");
    }

    @Test
    void BadStartRequest()
    {
        JSON_Check check = new JSON_Check();
        try {
            Assertions.assertEquals(false,check.ValidateStartRequest("{\"id\": \"2aca7611-0ae4-49f3-bf63-75bef4769028\",\"url\": 90,\"message\": \"May the best code win\"}"),"A bad request returned");
            Assertions.assertEquals(1, 0, "Number of org.everit.json.schema.ValidationException");
        }
        catch (org.everit.json.schema.ValidationException e) {Assertions.assertEquals(1, 1, "Number of org.everit.json.schema.ValidationException"); }
        try {
            Assertions.assertEquals(false, check.ValidateStartRequest("{\"id\": \"2aca7611-0ae4-49f3-bf63-75bef4769028\",\"message\": \"May the best code win\"}"), "A good bad returned");
            Assertions.assertEquals(1, 0, "Number of org.everit.json.schema.ValidationException");
        }
        catch (org.everit.json.schema.ValidationException e){ Assertions.assertEquals(1, 1, "Number of org.everit.json.schema.ValidationException");}
    }

}
