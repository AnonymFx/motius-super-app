package de.thomas.pettinger.motiussuperapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UseCaseApi {

    public static final String API_URL = "https://www.motius.de/api/usecases/";
    private static final String USE_CASE_TITLE = "title";
    private static final String USE_CASE_BODY = "body";

    private RequestQueue mRequestQueue;
    private ApiListener mApiListener;
    private Context mContext;

    public UseCaseApi(Context context, ApiListener listener) {
        mContext = context;
        mRequestQueue = Volley.newRequestQueue(mContext);
        mApiListener = listener;
    }

    public void loadUsecases() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                List<UseCase> useCases = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    //Little hacky to prevent crashes
                    UseCase useCase = new UseCase("", "");
                    try {
                        useCase = parseUseCase(response.getJSONObject(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    useCases.add(useCase);
                }
                mApiListener.onResponse(useCases);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mApiListener.onError();
            }
        });
        mRequestQueue.add(jsonArrayRequest);
    }

    private UseCase parseUseCase(JSONObject json) {
        String title = "", body = "";
        try {
            title = json.getString(USE_CASE_TITLE);
            body = json.getString(USE_CASE_BODY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new UseCase(title, body);
    }

    public interface ApiListener {
        void onResponse(List<UseCase> useCases);

        void onError();
    }
}
