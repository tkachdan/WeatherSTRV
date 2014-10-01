package android.weather.tkachdan.com.weather.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by tkachdan on 10/1/2014.
 */
public class ErrorDialogFragment extends DialogFragment {
    // Global field to contain the error dialog
    private Dialog mDialog;

    // Default constructor. Sets the dialog field to null
    public ErrorDialogFragment() {
        super();
        mDialog = null;
    }

    // Set the dialog to display
    public void setDialog(Dialog dialog) {
        mDialog = dialog;
    }

    // Return a Dialog to the DialogFragment.
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return mDialog;
    }
}