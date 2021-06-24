package fr.lernejo.navy_battle;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSON_Check {
    final JSONObject rawstartrequest = new JSONObject(new JSONTokener("" +
        "{\"$schema\": \"http://json-schema.org/draft-07/schema\",\"type\": \"object\",\"properties\": {\"id\": {\"type\": \"string\"},\"url\": {\"type\": \"string\"},\"message\": {\"type\": \"string\"}},\"required\": [\"id\", \"url\",\"message\"]}"));
    final Schema startrequestschema = SchemaLoader.load(rawstartrequest);

    public Boolean ValidateStartRequest(String Request)
    {
        startrequestschema.validate(new JSONObject(Request));
        return true;
    }
}
