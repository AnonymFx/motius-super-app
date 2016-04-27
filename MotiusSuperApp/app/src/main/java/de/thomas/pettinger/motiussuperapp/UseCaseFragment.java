package de.thomas.pettinger.motiussuperapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 */
public class UseCaseFragment extends Fragment implements UseCaseApi.ApiListener {


    private static final int PERMISSION_REQUEST_INTERNET = 42;

    private UseCaseApi mUseCaseApi;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UseCaseFragment() {
    }

    public static UseCaseFragment newInstance() {
        return new UseCaseFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUseCaseApi = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUseCaseApi = new UseCaseApi(getContext(), this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_REQUEST_INTERNET: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mUseCaseApi.loadUsecases();
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usecase, container, false);

        mProgressBar = (ProgressBar) view.findViewById(R.id.fragment_usecase_progress);

        // Set the adapter
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_usecase_list);
        Context context = view.getContext();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        //Request the internet permission
        getUsecasesFromApi();

        return view;
    }

    private void getUsecasesFromApi() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUEST_INTERNET);
        } else {
            mUseCaseApi.loadUsecases();
        }
    }


    @Override
    public void onResponse(List<UseCase> useCases) {
        UseCaseRecyclerViewAdapter newAdapter = new UseCaseRecyclerViewAdapter(useCases);
        mRecyclerView.setAdapter(newAdapter);
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), getString(R.string.api_error_message), Toast.LENGTH_SHORT).show();
    }
}
