package com.example.trackforce.presentation.util.json_highlight;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import androidx.core.content.ContextCompat;

import com.example.trackforce.R;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class JsonHighlighterUtils {

    public static SpannableStringBuilder highlightJson(String json, Context context) {
        SpannableStringBuilder builder = new SpannableStringBuilder(json);

        int bracketColor = ContextCompat.getColor(context, R.color.colorAccent);
        int keyColor = ContextCompat.getColor(context, R.color.colorPrimary);
        int stringColor = ContextCompat.getColor(context, R.color.warningOrange);
        int numberColor = ContextCompat.getColor(context, R.color.successGreen);
        int booleanColor = ContextCompat.getColor(context, R.color.errorRed);

        List<JsonHighlighterRule> rules = Arrays.asList(
                new JsonHighlighterRule(Pattern.compile("\"(.*?)\"\\s*:"), 1, keyColor),             // Keys
                new JsonHighlighterRule(Pattern.compile(":\\s*\"(.*?)\""), 1, stringColor),          // Strings
                new JsonHighlighterRule(Pattern.compile(":\\s*(\\d+(\\.\\d+)?)"), 1, numberColor),   // Numbers
                new JsonHighlighterRule(Pattern.compile(":\\s*(true|false)"), 1, booleanColor)       // Booleans
        );

        for (JsonHighlighterRule rule : rules) {
            rule.apply(json, builder);
        }

        // Brackets
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if ("{}[]".indexOf(c) != -1) {
                builder.setSpan(
                        new ForegroundColorSpan(bracketColor),
                        i,
                        i + 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
            }
        }

        return builder;
    }
}
