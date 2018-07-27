package de.naturalsoft.bakingapp.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;

import de.naturalsoft.bakingapp.R;
import de.naturalsoft.bakingapp.data.dataObjects.Ingredients;
import de.naturalsoft.bakingapp.utils.AppConfig;
import de.naturalsoft.bakingapp.utils.BakingHelper;

/**
 * BackingApp
 * Created by Thomas Schmidt on 26.07.2018.
 */
public class WidgetViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;

    private List<Ingredients> mIngredientsList;

    public WidgetViewsFactory(Context applicationContext) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

        SharedPreferences sharedPreferences = mContext.getSharedPreferences(AppConfig.APP_SHAREDPREF_FILE, Context.MODE_PRIVATE);
        mIngredientsList = BakingHelper.convertToIngredients(sharedPreferences.getString(AppConfig.RECIPEINCREDIENTSKEY, null));
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (mIngredientsList == null) return 0;
        return mIngredientsList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.lw_widget_item);

        Ingredients ingredient = mIngredientsList.get(position);
        remoteViews.setTextViewText(R.id.ingredientTitle, ingredient.getIngredient());
        remoteViews.setTextViewText(R.id.ingredientSubtitle, BakingHelper.buildStringForSubtitle(ingredient.getQuantity(), ingredient.getMeasure()));

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
