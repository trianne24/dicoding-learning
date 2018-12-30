package trianne.dicoding.mystackwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

class StackRemoteViewsFactory implements
        RemoteViewsService.RemoteViewsFactory {

    //panggil dlm tipe bitmap
    private List<Bitmap> mWidgetItems = new ArrayList<>();
    private Context mContext;
    private int mAppWidgetId;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    //Proses load di sini tidak boleh lebih dari 20 detik, karena jika melebihi dari 20 detik
    //akan terjadi ANR (Application Not Responding)
    public void onCreate() {
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.starwars1));
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.starwars2));
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.starwars3));
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.starwars4));
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.starwars5));
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        rv.setImageViewBitmap(R.id.imageView,mWidgetItems.get(position));

        Bundle extras = new Bundle();
        extras.putInt(ImageBannerWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
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
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
