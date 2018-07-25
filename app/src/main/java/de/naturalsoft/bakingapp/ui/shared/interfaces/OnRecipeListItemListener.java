package de.naturalsoft.bakingapp.ui.shared.interfaces;

import de.naturalsoft.bakingapp.data.dataObjects.Receipe;

public interface OnRecipeListItemListener {

    //Specific Item was clicked
    void onItemClicked(Receipe receipe);
}