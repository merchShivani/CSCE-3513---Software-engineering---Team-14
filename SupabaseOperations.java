import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;   




public class SupabaseOperations {
    private static final String SUPABASE_URL = "https://jwrerfjonxuewavojuol.supabase.co";
    private static final String SUPABASE_API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3cmVyZmpvbnh1ZXdhdm9qdW9sIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTM5Mjk3MTksImV4cCI6MjAwOTUwNTcxOX0.SjFvVcdGI3xMjtEsj7SaJlebubfDr65WVVoI1jI9Pqg";
    private static final String TABLE_NAME = "player";

    private static final HttpClient client = HttpClient.newHttpClient();

    public SupabaseOperations() {}

    public static void create(Player playerIn) {
        String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME;

        JSONObject data = parseJsonOut(playerIn);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("apikey", SUPABASE_API_KEY)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(data.toString()))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 201) {
                System.out.println("Record created successfully.");
            } else {
                System.err.println("Failed to create record. Status Code: " + response.statusCode());
                System.out.println("Response: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Player read(String column, String value) {
        Player playerIn = new Player();
        String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME + "?select=*&" + column + "=eq." + value;
        //System.out.println(url);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("apikey", SUPABASE_API_KEY)
            .GET()
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseString = response.body();
                //System.out.println("Response: " + responseString);
                playerIn = parseJsonIn(responseString);
            } else {
                System.err.println("Failed to read records. Status Code: " + response.statusCode());
                System.out.println("Response: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playerIn;
    }

    public static void update(int id, Player playerIn) {
        String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME + "?select=*&" + "id" + "=eq." + id;

        JSONObject data = parseJsonOut(playerIn);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("apikey", SUPABASE_API_KEY)
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(data.toString()))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Record updated successfully.");
            } else {
                System.err.println("Failed to update record. Status Code: " + response.statusCode());
                System.out.println("Response: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deletePlayer(String recordId) {
        String url = SUPABASE_URL + "/rest/v1/" + TABLE_NAME + "/" + recordId;

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("apikey", SUPABASE_API_KEY)
            .DELETE()
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 204) {
                System.out.println("Record deleted successfully.");
            } else {
                System.err.println("Failed to delete record. Status Code: " + response.statusCode());
                System.out.println("Response: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject parseJsonOut(Player playerIn) {
        JSONObject json = new JSONObject();
        json.put("id", playerIn.getID());
        json.put("codename", playerIn.getCodeName());
        json.put("first_name", playerIn.getPlayerFirstName());
        json.put("last_name", playerIn.getPlayerLastName());
        json.put("high_score", playerIn.getHighScore());

        return json;
    }

    public static Player parseJsonIn(String jsonString) {
        Player playerIn = new Player();

        try {
            // Parse the JSON string into a JSONArray
            JSONArray jsonArray = new JSONArray(jsonString);

            // Assuming the JSON structure is an array with one object
            if (jsonArray.length() == 1) {
                JSONObject data = jsonArray.getJSONObject(0);

                // Extract individual data members
                int id = data.getInt("id");
                String codename = data.getString("codename");
                String firstName = data.getString("first_name");
                String lastName = data.getString("last_name");
                int highScore = data.getInt("high_score");

                playerIn.SetID(id);
                playerIn.SetCodeName(codename);
                playerIn.SetPlayerFirstName(firstName);
                playerIn.SetPlayerLastName(lastName);
                playerIn.SetHighScore(highScore);
            } else {
                int id = 0;
                String codename = "";
                String firstName = "";
                String lastName = "";
                int highScore = 0;

                playerIn.SetID(id);
                playerIn.SetCodeName(codename);
                playerIn.SetPlayerFirstName(firstName);
                playerIn.SetPlayerLastName(lastName);
                playerIn.SetHighScore(highScore);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return playerIn;
    }

}
