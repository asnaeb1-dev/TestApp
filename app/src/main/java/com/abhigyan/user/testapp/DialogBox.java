package com.abhigyan.user.testapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogBox {
    Context context;
    final String[] options ={"Activity","Hot", "Votes"};
    private Dialog dialog;
    private int position  = 0;

    public DialogBox(Context context) {
        this.context = context;
    }

    public int startMakingDailog(String dailogTitle, final String positiveText, String negativeText)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(dailogTitle);
        builder.setSingleChoiceItems(options, new FilterOptionsClass(context).getOrderOptions(), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                switch(item)
                {
                    case 0:
                        position = item;
                        break;

                    case 1:
                        position = item;
                        break;

                    case 2:
                        position = item;
                        break;

                        default:
                            break;
                }
            }
        });

        builder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new FilterOptionsClass(context).setOrderOptions(position);
            }
        });

        builder.setNegativeButton(negativeText, null);

        dialog = builder.create();
        dialog.show();
        return -1;
    }
}
