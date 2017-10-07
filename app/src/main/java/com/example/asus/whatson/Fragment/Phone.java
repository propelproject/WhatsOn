package com.example.asus.whatson.Fragment;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.asus.whatson.R;
import com.example.asus.whatson.RegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Phone.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Phone#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Phone extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Button sendCodeButton;

    private View slidingView, initialView;

    public Phone() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Phone.
     */
    // TODO: Rename and change types and number of parameters
    public static Phone newInstance(String param1, String param2) {
        Phone fragment = new Phone();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_phone, container, false);

        sendCodeButton = (Button)view.findViewById(R.id.sendVerCode);
        slidingView = view.findViewById(R.id.checkVerCodeLayout);
        initialView = view.findViewById(R.id.sendVerCodeLayout);

        slidingView.setVisibility(View.INVISIBLE);

        sendCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sendVerCode :
                        sendCodeButton.setEnabled(false);

                        AnimationSet animationSet = new AnimationSet(false);

                        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,initialView.getHeight());
                        translateAnimation.setDuration(1000);

                        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down_anim);

                        animationSet.addAnimation(translateAnimation);
                        animationSet.addAnimation(animation);

                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                slidingView.setVisibility(View.VISIBLE);
                                RelativeLayout.LayoutParams paramsSlide = (RelativeLayout.LayoutParams)slidingView.getLayoutParams();
                                paramsSlide.topMargin += (initialView.getHeight() + 10);
                                slidingView.setLayoutParams(paramsSlide);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        slidingView.startAnimation(animationSet);
                }
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onPhoneFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onPhoneFragmentInteraction(Uri uri);
    }
}
