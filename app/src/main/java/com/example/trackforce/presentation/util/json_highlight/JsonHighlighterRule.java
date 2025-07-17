package com.example.trackforce.presentation.util.json_highlight;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonHighlighterRule {
    private final Pattern pattern;
    private final int group;
    private final int color;

    public JsonHighlighterRule(Pattern pattern, int group, int color) {
        this.pattern = pattern;
        this.group = group;
        this.color = color;
    }

    public void apply(String json, SpannableStringBuilder builder) {
        Matcher matcher = pattern.matcher(json);
        while (matcher.find()) {
            builder.setSpan(
                    new ForegroundColorSpan(color),
                    matcher.start(group),
                    matcher.end(group),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        }
    }
}