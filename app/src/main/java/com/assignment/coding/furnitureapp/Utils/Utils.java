package com.assignment.coding.furnitureapp.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.assignment.coding.furnitureapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by victor.t.andales.iii on 5/2/2018.
 */

public class Utils {

    public static final String LOG_TAG = Utils.class.getSimpleName();


    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;


    /**
     * Create a File for saving an image
     */
    public static File getOutputMediaFile(int type, Context context) {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), context.getString(R.string.storageFileName));

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(context.getString(R.string.storageFileName), "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat(context.getString(R.string.pattern)).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }


    public static void saveFromGallery(Bitmap finalBitmap, File file) throws IOException {
        OutputStream output = null;
        try {
            output = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, output);

        } finally {
            if (null != output) {
                output.flush();
                output.close();
            }
        }
    }

    public static void saveFromCamera(byte[] bytes, File file) throws IOException {
        OutputStream output = null;
        try {
            output = new FileOutputStream(file);
            output.write(bytes);
        } finally {
            if (null != output) {
                output.close();
            }
        }
    }
}