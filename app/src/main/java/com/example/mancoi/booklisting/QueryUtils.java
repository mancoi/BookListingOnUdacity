package com.example.mancoi.booklisting;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mancoi on 03/08/2017.
 */

public class QueryUtils {

    //Tag for the log message
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();



    private QueryUtils() {

    }

    static List<Books> fetchBooksResults(String inputUrl)
    {
        String jsonResponse = "";
        URL url = createUrl(inputUrl);


        try {
            jsonResponse = makeHTTPRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        return extractBooksFromJSON(jsonResponse);
    }

    //Create a new URL from input String and return it
    private static URL createUrl(String inputURL)
    {
        URL url = null;

        try {
            url = new URL(inputURL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error when creating URL", e);
        }
        return url;
    }

    //Perform HTTP request and return a String as a response
    private static String makeHTTPRequest(URL url) throws IOException {
        String jsonResponse = "";

        //If the url == null, return early
        if(url == null)
        {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200)
            {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else
            {

                Log.e(LOG_TAG, "Error response code: " + responseCode);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error when trying to retrive the search results", e);
        }
        finally {
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
            if (inputStream != null)
            {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link java.io.InputStream} into a String which contains
     * the whole JSON response from the server
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if(inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("Utf-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null)
            {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }

        return output.toString();
    }

    /**
     * Return a list of {@link Books} objects that has been built up from
     * pasting a JSON response
     */
    private static List<Books> extractBooksFromJSON(String booksJSON)
    {
        //If the JSON string is empty or null, return early
        if (TextUtils.isEmpty(booksJSON))
        {
            return null;
        }

        //Create an empty ArrayList to store extracted books
        List<Books> books = new ArrayList<Books>();

        try {
            JSONObject root = new JSONObject(booksJSON);

            JSONArray items = root.getJSONArray("items");

            /**
             * For each book in the items Array, create an {@link Books} object
             */
            for (int i = 0; i < items.length(); i++)
            {
                // Get a single book at position i within the list of earthquakes
                JSONObject currentBook = items.getJSONObject(i);

                //Get current book info
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");

                //Get the title of the book
                String title = volumeInfo.getString("title");

                //Get the author array
                JSONArray authorsArray = volumeInfo.optJSONArray("authors");

                String authors = null;
                if (authorsArray != null)
                {
                    authors = authorsArray.join(", ").replaceAll("\"", "");
                }


                books.add(new Books(title, authors));

            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem when parsing the JSON", e);
        }

        return books;
    }
}
