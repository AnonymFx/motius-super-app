package de.thomas.pettinger.motiussuperapp;


import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ParrotFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParrotFragment extends Fragment {

    public static final String TTS_UTTERANCE_ID = "parrot";

    private TextToSpeech mTts;

    public ParrotFragment() {
        // Required empty public constructor
    }

    @Override
    public void onPause() {
        super.onPause();
        mTts.shutdown();
    }

    @Override
    public void onResume() {
        super.onResume();
        mTts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });
        mTts.setLanguage(Locale.getDefault());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton parrotButton = (ImageButton) getView().findViewById(R.id.parrot);
        final EditText parrotEditText = (EditText) getView().findViewById(R.id.parrot_text);

        parrotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String parrotText = getString(R.string.parrot_default);
                String newText = parrotEditText.getText().toString();
                if (!"".equals(newText)) {
                    parrotText = newText;
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    mTts.speak(parrotText, TextToSpeech.QUEUE_FLUSH, null, TTS_UTTERANCE_ID);
                } else {
                    mTts.speak(parrotText, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton parrotButton = (ImageButton) getView().findViewById(R.id.parrot);
        final EditText parrotEditText = (EditText) getView().findViewById(R.id.parrot_text);

        parrotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String parrotText = getString(R.string.parrot_default);
                String newText = parrotEditText.getText().toString();
                if (!"".equals(newText)) {
                    parrotText = newText;
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    mTts.speak(parrotText, TextToSpeech.QUEUE_FLUSH, null, TTS_UTTERANCE_ID);
                } else {
                    mTts.speak(parrotText, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ParrotFragment.
     */
    public static ParrotFragment newInstance() {
        return new ParrotFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parrot, container, false);
    }


}
