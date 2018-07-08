package com.javaer.javaer.UI;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.Toolbar;

import com.javaer.javaer.Main;

/**
 * Created by javaer on 2018/7/1.
 */

public class BaseDialog extends LinearLayout{

    protected Context mContext;
    protected SharedPreferences prefs;
    protected SharedPreferences.Editor editor;

    private ScrollView scrollView;
    protected AlertDialog dialog;
    protected LinearLayout content;
    protected Toolbar toolbar;

    public BaseDialog(Context context, String title) {
        super(context);
        this.mContext = context;
        prefs = context.getSharedPreferences(Main.Javaer_PKG,0);
        editor = context.getSharedPreferences(Main.Javaer_PKG,0).edit();
        init(title);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void init(String title){

        LayoutParams contentParam = new LayoutParams
                (LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        scrollView = new ScrollView(mContext);
        scrollView.setLayoutParams(contentParam);

        content = new LinearLayout(mContext);
        content.setFocusable(true);
        content.setFocusableInTouchMode(true);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setLayoutParams(contentParam);

        toolbar = new Toolbar(mContext);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor(Color.parseColor("#01bf21"));
        toolbar.setTitle(title);
        toolbar.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dpi2px(60, mContext)));

        content.addView(toolbar);
    }

    //添加带key的文本框,文本框的key对应的value会自动存到SharedPreferences的xml文本里
    public void addEdit(final String key, String hint, final String defaultText){
        final EditText editText = new EditText(mContext);
        editText.setHint(hint);
        editText.setMaxLines(1);
        editText.setTextSize(dpi2px(6,mContext));
        editText.setText(prefs.getString(key, defaultText));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editText.getText().toString().equals("") && editText.getText() != null){
                    editor.putString(key,editText.getText().toString());
                    editor.apply();
                }else {
                    editor.putString(key, defaultText);
                    editor.apply();
                }
            }
        });

        LayoutParams wxidParams = new LayoutParams(LayoutParams.MATCH_PARENT, dpi2px(30,mContext));
        wxidParams.setMargins(dpi2px(18,mContext),dpi2px(3,mContext), dpi2px(18,mContext), 0);
        editText.setLayoutParams(wxidParams);

        content.addView(editText);
    }

    public void addSwitch(String text, final String key, boolean default_boolean){
        final Switch swh = new Switch(mContext);
        swh.setText(text);
        swh.setChecked(prefs.getBoolean(key, default_boolean));
        swh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(key,swh.isChecked());
                editor.apply();
            }
        });

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(dpi2px(18,mContext),dpi2px(4,mContext),dpi2px(18,mContext),dpi2px(1, mContext));
        swh.setLayoutParams(params);

        content.addView(swh);
    }

    public void show(boolean cancelOut, boolean cancelable, final Click positive, String sure, final Click negative){
        scrollView.addView(content);
        this.addView(scrollView);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(this);
        if (positive != null){
            builder.setPositiveButton(sure, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    positive.onClick();
                }
            });
        }
        if (negative != null){
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    negative.onClick();
                }
            });
        }
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(cancelOut);
        dialog.setCancelable(cancelable);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.show();
    }

    public static int dpi2px(float value, Context context){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    public interface Click{
        void onClick();
    }
}
