package android.tungpt.lesson7;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "https://api.github.com/users/google/repos";
    public static final String MESSAGE = "Please Wait...";
    public static final String RESPONSE_ID = "id";
    public static final String RESPONSE_NAME = "name";
    public static final String RESPONSE_URL = "html_url";
    private ProgressDialog mProgressDialog;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        new RepoLoader().execute(URL);
    }

    class RepoLoader extends AsyncTask<String, Repo, List<Repo>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setMessage(MESSAGE);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected List<Repo> doInBackground(String... urls) {
            return getData(urls[0]);
        }

        @Override
        protected void onPostExecute(List<Repo> repos) {
            super.onPostExecute(repos);
            if (mProgressDialog.isShowing())
                mProgressDialog.dismiss();
            RepoAdapter repoAdapter = new RepoAdapter(repos);
            mRecyclerView.setAdapter(repoAdapter);
        }

        private List<Repo> getData(String url) {
            List<Repo> repos = new ArrayList<>();
            HttpHandler httpHandler = new HttpHandler();
            String response = httpHandler.makeServiceCall(url);
            if (response != null) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString(RESPONSE_ID);
                        String name = jsonObject.getString(RESPONSE_NAME);
                        String htmlUrl = jsonObject.getString(RESPONSE_URL);
                        Repo repo = new Repo(id, name, htmlUrl);
                        repos.add(repo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return repos;
        }
    }
}
