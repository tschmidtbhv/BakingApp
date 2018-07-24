package de.naturalsoft.bakingapp.ui.ingredients;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.naturalsoft.bakingapp.R;
import de.naturalsoft.bakingapp.data.dataObjects.Ingredients;

/**
 * BackingApp
 * Created by Thomas Schmidt on 23.07.2018.
 */
class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder>{

    List<Ingredients> ingredients;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Ingredients ingredient = ingredients.get(position);
        holder.title.setText(ingredient.getIngredient());

        StringBuilder builder = new StringBuilder();
        builder.append(ingredient.getQuantity()).append(" | ").append(ingredient.getMeasure());
        holder.subTitle.setText(builder.toString());
    }

    public void swapList(List<Ingredients> list) {
        if (list != null) {
            ingredients = list;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {

        if(ingredients == null) return 0;
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView subTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            subTitle  = itemView.findViewById(R.id.subtitle);
        }
    }
}
