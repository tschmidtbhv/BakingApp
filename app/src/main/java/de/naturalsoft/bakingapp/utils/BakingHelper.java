package de.naturalsoft.bakingapp.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import de.naturalsoft.bakingapp.R;

/**
 * BackingApp
 * Created by Thomas Schmidt on 22.07.2018.
 */
public class BakingHelper {

    public static void loadImageIntoImageView(Context context, String title, String url, ImageView imageView) {

        if (url.isEmpty()) {

            int resourceId;
            if ((resourceId = hasResource(context, title)) != 0) {
                imageView.setVisibility(View.VISIBLE);
                Picasso.get().load(resourceId).into(imageView);
            } else {
                imageView.setVisibility(View.GONE);
            }
            return;
        }

        imageView.setVisibility(View.VISIBLE);
        Picasso.get().load(url).into(imageView);
    }

    private static int hasResource(Context context, String title) {
        title = title.toLowerCase().replaceAll(" ", "_");
        int resourceId = context.getResources().getIdentifier(title, "drawable", context.getPackageName());

        return resourceId;
    }


    /**
     * Calculate the possible Columns
     * at runtime for the device
     *
     * @param context Act. Context
     * @return number Of Columns
     */
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 400;

        return (int) (dpWidth / scalingFactor);
    }


    public static boolean isTablet(Context context) {
        return context.getResources().getBoolean(R.bool.is_tablet);
    }

    public static boolean isLandscapeMode(Context context) {
        return context.getResources().getBoolean(R.bool.is_landscape);
    }
}
