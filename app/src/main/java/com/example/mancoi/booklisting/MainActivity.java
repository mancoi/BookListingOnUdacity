package com.example.mancoi.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Books>> {

    //The adapter to populate item to list
    private BooksAdapter mAdapter;

    //Define the ID of the Loader
    private final static int BOOK_RESULTS_LOADER_ID = 1;

    //This query is ready to build the complete HTTP request
    private final static String SEARCH_QUERY = "https://www.googleapis.com/books/v1/volumes?q=";

    //The HTTP request we will send after build it from the SEARCH_QUERY and the user input
    private static String searchBooksRequest = null;

    //Get the context for LoaderCallbacks because we can't get context in
    //Async task, which is the search onClickListener
    private LoaderManager.LoaderCallbacks mLoaderCallbacks = this;

    //Will point to the TextView with ID: search_content
    private TextView searchContent;

    //Will point to the ProgressBar with ID: loading_indicator
    private View loadingIndicator;

    //Will point to the TextView with ID: empty_state_tv
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the ListView with ID list
        //Then set it to bookListView
        ListView booksListView = (ListView) findViewById(R.id.list);

        //Find the TextView with ID empty_state_tv which will contain the empty state
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_state_tv);
        //Then set to the EmptyView of booksListView
        booksListView.setEmptyView(mEmptyStateTextView);

        //Create the Adapter to populate the data
        mAdapter = new BooksAdapter(this, new ArrayList<Books>());
        //Set the Adapter of the booksListView to mAdapter
        booksListView.setAdapter(mAdapter);

        //Find the TextView search_content and set it to searchContent
        searchContent = (TextView) findViewById(R.id.search_content);

        //Find the ProgressBar loading_indicator and set it to loadingIndicator
        loadingIndicator = findViewById(R.id.loading_indicator);

        //At the first time this Activity load, the searchBooksRequest is null, the user
        //didn't search anything yet so we don't initialize the Loader
        //If the searchBooksRequest is not null, it's mean the user has search something
        //so we initialize the Loader
        //We should always init the Loader every time after the user's searched something
        //because after the ListView has been populated then we lost the internet connection,
        //we still want to display that result on screen
        //It also because if we don't do this, sometime when the Loader is was not initialized,
        //and then user press the "Search" button, nothing will happen.
        if (searchBooksRequest != null) {
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            getLoaderManager().initLoader(BOOK_RESULTS_LOADER_ID, null, mLoaderCallbacks);

        }

        //After init the Loader, we check for the internet connection
        //If there's no internet connection, notify user about that
        //We should check this at the first time this Activity loaded because
        //we might not have the internet connection at the first time
        if (!isConnected()) {
            clearDataWhenNotConnected();
        } else {
            //Hide the loading indicator because we all set now
            loadingIndicator.setVisibility(View.GONE);
        }

        //Find the search Button with ID search_button
        Button search = (Button) findViewById(R.id.search_button);
        //Then listen if it clicked
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //When the button is clicked, we start to process so show up the loading indicator
                loadingIndicator.setVisibility(View.VISIBLE);

                //Check if we have the internet connection or not
                if (isConnected()) {

                    //Find the search box which should content the user input
                    //and set it to textToSearch
                    EditText textToSearch = (EditText) findViewById(R.id.search_bar);

                    //If nothing typed in the search box, tell user type something by using Toast
                    if (TextUtils.isEmpty(textToSearch.getText())) {

                        //Also hide the loading indicator because we don't need it anymore
                        loadingIndicator.setVisibility(View.GONE);
                        Toast.makeText((Context) mLoaderCallbacks, R.string.type_something, Toast.LENGTH_SHORT).show();


                    }
                    //If user typed something in the search box then
                    //build the HTTP request completely with that input and request its results
                    else {

                        //Hide the emptyStateTextView if is has been set before
                        mEmptyStateTextView.setText(null);

                        //Get the contetn user has typed in the search box and set it to
                        //TextView with id search_content to inform user what they has typed ASAP
                        String contentToSearch = textToSearch.getText().toString();
                        searchContent.setText(contentToSearch);

                        //Build request to this form:
                        //"https://www.googleapis.com/books/v1/volumes?q=search+terms"
                        searchBooksRequest = SEARCH_QUERY + contentToSearch.replaceAll(" ", "+").trim();

                        //Clear the search box, so user don't have to clear it manually
                        textToSearch.getText().clear();

                        // Restart the loader to show up the search results.
                        // Pass in the int ID constant defined above and pass in null for
                        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
                        // because this activity implements the LoaderCallbacks interface).
                        getLoaderManager().restartLoader(BOOK_RESULTS_LOADER_ID, null, mLoaderCallbacks);
                    }
                }

                //If we don't have the internet connection, the notify the user by using Toast
                else {

                    //Notify user that currently we don't have internet connection
                    loadingIndicator.setVisibility(View.GONE);
                    Toast.makeText((Context) mLoaderCallbacks, R.string.no_internet, Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    //Check if we have the internet connection or not
    private boolean isConnected() {

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();

    }

    //When no internet connection, set the empty state to inform the user
    private void clearDataWhenNotConnected() {

        // First, hide loading indicator so error message will be visible
        loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Update empty state with no connection error message
        mEmptyStateTextView.setText(R.string.no_internet);
    }

    @Override
    public Loader<List<Books>> onCreateLoader(int i, Bundle bundle) {

        return new BooksLoader(this, searchBooksRequest);
    }

    @Override
    public void onLoadFinished(Loader<List<Books>> loader, List<Books> data) {

        //Finished load data, so now we can hide the loading indicator
        loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mAdapter.clear();

        //Check if data is valid to add
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
        //If not, then inform the user
        else
        {
            mEmptyStateTextView.setText(R.string.error_retrieving_data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Books>> loader) {
        mAdapter.clear();
    }
}
