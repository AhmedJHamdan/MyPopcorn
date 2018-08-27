
package com.udacity.ahmed.mypopcorn.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;


public class TvWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new TvWidgetFactory(getApplicationContext(), intent);
    }
}
