package com.example.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.LayoutInflater;

public class MoreInfoDialog extends DialogFragment {

    String URL= "http://www.moma.org";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflate = getActivity().getLayoutInflater();
        builder.setView(inflate.inflate(R.layout.dialog,null)).setPositiveButton(
                R.string.dialog_visit, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                        startActivity(intent);
                    }
                }).setNegativeButton(R.string.dialog_notNow, null);
        return builder.create();
    }
}