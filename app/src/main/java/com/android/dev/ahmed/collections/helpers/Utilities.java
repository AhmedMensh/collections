package com.android.dev.ahmed.collections.helpers;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.dev.ahmed.collections.R;


public class Utilities {

    private static final String TAG = "Utilities";
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }

    public static void showToast(Context context, String m) {

        Toast.makeText(context, m, Toast.LENGTH_SHORT).show();
    }

    public static boolean isEmptyText(Context context, EditText editText) {

        if (TextUtils.isEmpty(editText.getText().toString())) {
            showToast(context, context.getResources().getString(R.string.all_failed_required));
            return false;
        }
        return true;
    }


    public static String getRealPathFromURIForCameraImage(Uri contentURI, Activity context) {
        String[] projection = {MediaStore.Images.Media.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = context.getContentResolver().query(contentURI, projection, null,
                null, null);
        if (cursor == null)
            return null;
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        if (cursor.moveToFirst()) {
            String s = cursor.getString(column_index);
            // cursor.close();
            return s;
        }
        // cursor.close();
        return null;
    }

    public static String getRealPathFromGalleryImage(Uri contentURI, Activity context) {
        // Intent data ;
        //    Log.e(TAG,"data "+data);


        String wholeID = DocumentsContract.getDocumentId(contentURI);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = {MediaStore.Images.Media.DATA};

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        @SuppressWarnings("deprecation")
        // MediaStore.Images.Media.EXTERNAL_CONTENT_URI

                Cursor cursor = context.getContentResolver().
                query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        column, sel, new String[]{id}, null);


        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            String filePath = cursor.getString(columnIndex);
            return filePath;
        }
        cursor.close();


        return null;
    }



}
