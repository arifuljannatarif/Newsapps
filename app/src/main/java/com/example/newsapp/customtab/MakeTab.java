package com.example.newsapp.customtab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;

import com.example.newsapp.R;

public class MakeTab {

    public static void launchurl(Context context,String url){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent intentCustomTabs = builder.build();
        builder.setToolbarColor(R.color.customtabtoolbar);
        builder.setStartAnimations(context,R.anim.left_to_right,R.anim.right_to_left);
        intentCustomTabs.intent.setPackage("com.android.chrome");
        intentCustomTabs.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentCustomTabs.launchUrl((Activity)context, Uri.parse(url));
    }
}
