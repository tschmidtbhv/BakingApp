package de.naturalsoft.bakingapp.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.List;

import de.naturalsoft.bakingapp.R;
import de.naturalsoft.bakingapp.data.dataObjects.Ingredients;

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

    public static List<Ingredients> convertToIngredients(String json) {

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Ingredients>>() {
        }.getType();
        return (List<Ingredients>) gson.fromJson(json, listType);
    }

    public static String buildStringForSubtitle(double first, String second) {
        StringBuilder builder = new StringBuilder();
        builder.append(first).append(" | ").append(second);
        return builder.toString();
    }
}
