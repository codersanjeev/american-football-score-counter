package com.example.sanjeev.americanfootballscorecounter;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

/**
 * Created by sanjeev on 8/3/18.
 */

public class MyDialog extends DialogFragment implements View.OnClickListener {
    Button plusOne, plusTwo;
    Communicator communicator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogview, null);
        plusOne = view.findViewById(R.id.plusone);
        plusTwo = view.findViewById(R.id.plustwo);
        plusOne.setOnClickListener(this);
        plusTwo.setOnClickListener(this);
        MyDialog.this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.plusone){
            communicator.onDialogMessage("plus one");
            dismiss();
        }
        else{
            communicator.onDialogMessage("plus two");
            dismiss();
        }
    }

    interface Communicator{
        public void onDialogMessage(String message);
    }
}
