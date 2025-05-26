package persistence;

import org.json.JSONObject;

// Represent a JSON object
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
