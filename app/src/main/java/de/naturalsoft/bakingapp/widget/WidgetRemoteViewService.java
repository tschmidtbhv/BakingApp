package de.naturalsoft.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * BackingApp
 * Created by Thomas Schmidt on 26.07.2018.
 */
public class WidgetRemoteViewService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetViewsFactory(getApplicationContext());
    }
}
