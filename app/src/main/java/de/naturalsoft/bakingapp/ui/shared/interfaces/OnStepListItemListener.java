package de.naturalsoft.bakingapp.ui.shared.interfaces;

import de.naturalsoft.bakingapp.data.dataObjects.Steps;

/**
 * BackingApp
 * Created by Thomas Schmidt on 14.07.2018.
 */
public interface OnStepListItemListener {

    //specific step item was clicked
    void onItemClicked(Steps step);
}
