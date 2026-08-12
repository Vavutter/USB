package com.moritz.vescfix03;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

final class Ui {
    static final int BG = Color.rgb(14, 17, 22);
    static final int NAVY = Color.rgb(19, 23, 30);
    static final int CARD = Color.rgb(24, 29, 37);
    static final int CARD_ALT = Color.rgb(29, 35, 44);
    static final int TEXT = Color.rgb(241, 244, 248);
    static final int MUTED = Color.rgb(157, 166, 178);
    static final int BLUE = Color.rgb(72, 126, 214);
    static final int CYAN = Color.rgb(95, 174, 202);
    static final int GREEN = Color.rgb(73, 177, 126);
    static final int PINK = Color.rgb(210, 82, 104);
    static final int WARNING = Color.rgb(214, 157, 72);
    static final int BORDER = Color.rgb(48, 56, 68);

    static int dp(Context c, float value) { return (int)(value * c.getResources().getDisplayMetrics().density + 0.5f); }

    static TextView text(Context c, String value, float sp, int color, boolean bold) {
        TextView v = new TextView(c); v.setText(value); v.setTextSize(sp); v.setTextColor(color);
        v.setGravity(Gravity.CENTER_VERTICAL); v.setIncludeFontPadding(false);
        if (bold) v.setTypeface(Typeface.DEFAULT, Typeface.BOLD); return v;
    }

    static TextView title(Context c, String value) {
        TextView v = text(c, value, 27, TEXT, true); v.setPadding(0, dp(c, 6), 0, dp(c, 14)); return v;
    }

    static LinearLayout card(Context c) {
        LinearLayout box = new LinearLayout(c); box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(dp(c, 18), dp(c, 17), dp(c, 18), dp(c, 17));
        GradientDrawable bg = new GradientDrawable(); bg.setColor(CARD); bg.setCornerRadius(dp(c, 14)); bg.setStroke(dp(c, 1), BORDER);
        box.setBackground(bg);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, dp(c, 12)); box.setLayoutParams(lp); box.setElevation(dp(c, 1)); return box;
    }

    static LinearLayout tintedCard(Context c, int accent) {
        LinearLayout box = card(c); GradientDrawable bg = new GradientDrawable(); bg.setColor(CARD_ALT); bg.setCornerRadius(dp(c, 14));
        bg.setStroke(dp(c, 2), Color.argb(180, Color.red(accent), Color.green(accent), Color.blue(accent))); box.setBackground(bg); return box;
    }

    static Button primaryButton(Context c, String label) {
        Button b = new Button(c); b.setText(label); b.setTextSize(15); b.setTextColor(Color.WHITE); b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        b.setAllCaps(false); b.setPadding(dp(c, 16), 0, dp(c, 16), 0); GradientDrawable bg = new GradientDrawable(); bg.setColor(BLUE);
        bg.setCornerRadius(dp(c, 12)); b.setBackground(bg); b.setMinHeight(dp(c, 52)); b.setElevation(0); return b;
    }

    static Button secondaryButton(Context c, String label) {
        Button b = new Button(c); b.setText(label); b.setTextSize(15); b.setTextColor(TEXT); b.setTypeface(Typeface.DEFAULT, Typeface.BOLD); b.setAllCaps(false);
        GradientDrawable bg = new GradientDrawable(); bg.setColor(CARD_ALT); bg.setCornerRadius(dp(c, 12)); bg.setStroke(dp(c, 1), BORDER);
        b.setBackground(bg); b.setMinHeight(dp(c, 50)); b.setElevation(0); return b;
    }

    static EditText edit(Context c, String hint, String value, boolean decimal) {
        EditText e = new EditText(c); e.setHint(hint); e.setHintTextColor(Color.rgb(112, 121, 134)); e.setText(value); e.setTextColor(TEXT); e.setTextSize(16);
        e.setSingleLine(true); e.setPadding(dp(c, 14), 0, dp(c, 14), 0);
        e.setInputType(decimal ? InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        GradientDrawable bg = new GradientDrawable(); bg.setColor(Color.rgb(18, 22, 28)); bg.setCornerRadius(dp(c, 10)); bg.setStroke(dp(c, 1), BORDER); e.setBackground(bg);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(c, 54)); lp.setMargins(0, dp(c, 6), 0, dp(c, 14)); e.setLayoutParams(lp); return e;
    }

    static LinearLayout row(Context c) { LinearLayout row = new LinearLayout(c); row.setOrientation(LinearLayout.HORIZONTAL); row.setGravity(Gravity.CENTER_VERTICAL); return row; }
    static Space space(Context c, int dp) { Space s = new Space(c); s.setLayoutParams(new LinearLayout.LayoutParams(1, Ui.dp(c, dp))); return s; }
    static View divider(Context c) { View v = new View(c); v.setBackgroundColor(BORDER); LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(c, 1)); lp.setMargins(0, dp(c, 12), 0, dp(c, 12)); v.setLayoutParams(lp); return v; }
    static GradientDrawable pill(Context c, int color, boolean filled) { GradientDrawable bg = new GradientDrawable(); bg.setColor(filled ? Color.argb(38, Color.red(color), Color.green(color), Color.blue(color)) : Color.TRANSPARENT); bg.setCornerRadius(dp(c, 10)); bg.setStroke(dp(c, 1), color); return bg; }
    private Ui() {}
}
