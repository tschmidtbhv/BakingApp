package de.naturalsoft.bakingapp.ui.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.naturalsoft.bakingapp.R;
import de.naturalsoft.bakingapp.data.dataObjects.Receipe;
import de.naturalsoft.bakingapp.data.dataObjects.Steps;
import de.naturalsoft.bakingapp.ui.shared.interfaces.OnRecipeListItemListener;
import de.naturalsoft.bakingapp.ui.shared.interfaces.OnStepListItemListener;
import de.naturalsoft.bakingapp.utils.AppConfig;
import de.naturalsoft.bakingapp.utils.BakingHelper;

/**
 * BackingApp
 * Created by Thomas Schmidt on 10.07.2018.
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<?> mList;
    private int mMode;

    public RecipeAdapter(int mode) {
        mMode = mode;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.recipe_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String title;
        Object item = mList.get(position);

        if(mMode == AppConfig.ADAPTER_MODE_RECIPES) {

            Receipe receipe = (Receipe) item;
            holder.itemView.setOnClickListener((View view) -> {
                ((OnRecipeListItemListener) holder.itemView.getContext()).onItemClicked(receipe);
            });
            title = receipe.getName();
            BakingHelper.loadImageIntoImageView(holder.itemView.getContext(), title, receipe.getImage(), holder.recipeImage);

        }else {

            Steps step = (Steps)item;
            title = step.getShortDescription();
            holder.itemView.setOnClickListener((View view) -> {
                ((OnStepListItemListener) holder.itemView.getContext()).onItemClicked(step);
            });
        }

        holder.title.setText(title);
    }

    @Override
    public int getItemCount() {

        if (mList == null) return 0;

        return mList.size();
    }

    public void swapList(List<?> list) {
        if (list != null) {
            mList = list;
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView recipeImage;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            recipeImage = itemView.findViewById(R.id.recipe_image);
        }
    }
}
