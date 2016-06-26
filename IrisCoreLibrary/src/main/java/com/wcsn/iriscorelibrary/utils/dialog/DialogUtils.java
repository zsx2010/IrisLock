package com.wcsn.iriscorelibrary.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.ImaginationUnlimited.library.R;
import com.ImaginationUnlimited.library.utils.app.StringUtils;

/**
 * dialog utils
 *
 * @author wangheng on 2016-04-01 10:36
 */
public class DialogUtils {
    /**
     * showWaitingDialog: 弹出等待的对话框. <br/>
     *
     * @param context
     * @param msg
     * @return
     */

    public static Dialog showWaitingDialog(Context context, String msg) {
        Dialog dialog = new Dialog(context,
                android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_waiting);
        TextView tvMsg = (TextView) dialog.findViewById(R.id.tvMsg);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.iv_pic);
        final AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
        if (StringUtils.isNullOrEmpty(msg)) {
            tvMsg.setText("");
        }else {
            tvMsg.setText(msg);
        }
        dialog.setCancelable(false);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if(anim != null && anim.isRunning()){
                    anim.stop();
                }
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(anim != null && anim.isRunning()){
                    anim.stop();
                }
            }
        });
        dialog.show();
        anim.start();
        return dialog;
    }
}
