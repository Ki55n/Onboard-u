package com.example.wifimodule.utils.picker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Build
import android.provider.MediaStore
import android.util.Log.e
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.wifimodule.R
import com.example.wifimodule.base.BaseApp
import java.io.*


/* Usage:

     1. declare provider in manifest

     <provider
        android:name="android.support.v4.content.FileProvider"
        android:authorities="${applicationId}.fileprovider"
        android:exported="false"
        android:grantUriPermissions="true">
        <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/image_path" />
        </provider>

     2. write permission and feature in manifest

     <uses-permission android:name="android.permission.CAMERA" />

     <uses-feature
        android:name="android.hardware.camera"
        android:required="false" />

     3. xml file for provider resource

     <paths>
        <files-path name="captured_image" path="Images/" />
     </paths>

     4. calling code

        ImageChooserUtil.openChooserDialog(coreFragment,"fileName");

     4. add in requesting activity

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode) {
                case ImageChooserUtil.PERMISSION_WRITE_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager
                        .PERMISSION_GRANTED) {
                    ImageChooserUtil.openChooserDialog(getCoreFragment(), getViewModel().cvPhotoName);
                }
                break;
            case ImageChooserUtil.REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager
                        .PERMISSION_GRANTED) {
                    ImageChooserUtil.startCameraIntent(getCoreFragment(), getViewModel().cvPhotoName);
                }
                break;
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

      5. Add onActivityResult

       @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            switch (requestCode) {
                case ImageChooserUtil.REQUEST_GALLERY:
                case ImageChooserUtil.REQUEST_CAMERA:
                    if (resultCode == RESULT_OK) {
                        new ImageChooserUtil.SaveImageTask(activity,
                                data,
                                requestCode,
                                String.valueOf(goodsFileName),
                                new ImageChooserUtil.SaveImageTask.FileSaveListener() {
                                    @Override
                                    public void fileSaved(File file) {

                                    }
                                }).execute();
                    }
                    break;
            }
            super.onActivityResult(requestCode, resultCode, data);
        }

      */

object ImageChooserUtil {

    val REQUEST_GALLERY = 1235
    val REQUEST_PDF = 1240
    val REQUEST_VIDEO_GALLERY = 1239
    val REQUEST_CAMERA = 1234
    val REQUEST_VIDEO_CAMERA = 1238
    val PERMISSION_CAMERA = 1236
    val PERMISSION_WRITE_STORAGE = 1237
    private val IMAGE_DIRECTORY = "Images"
    private val PDF_DIRECTORY = "PDF"
    private val VIDEO_DIRECTORY = "Videos"
    private val CAPTURE_IMAGE_FILE_PROVIDER = ".fileprovider"
    private val FILE_EXTENSION = ".jpg"
    private val VIDEO_FILE_EXTENSION = ".mp4"

    @RequiresApi(Build.VERSION_CODES.M)
            /**
             * @param fileName keep file name in field. this will be required when getting permission.
             */
    fun openChooserDialog(activity: Activity?, fileName: String) {
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle(R.string.choose_image)
            .setPositiveButton(R.string.option_camera) { dialog, id ->
                if (PermissionUtil.checkPermission(activity, PermissionUtil.Permissions.CAMERA)) {
                    startCameraIntent(activity, fileName)
                } else {
                    PermissionUtil.getPermission(
                        activity,
                        PermissionUtil.Permissions.CAMERA,
                        PERMISSION_CAMERA,
                        PermissionUtil.PermissionMessage.CAMERA,
                        ""
                    )
                }
            }
            .setNegativeButton(R.string.option_gallery) { dialog, id ->
                if (PermissionUtil.checkPermission(
                        activity,
                        PermissionUtil.Permissions.WRITE_EXTERNAL_STORAGE
                    )
                ) {
                    startGalleryIntent(activity)
                } else {
                    PermissionUtil.getPermission(
                        activity,
                        PermissionUtil.Permissions.WRITE_EXTERNAL_STORAGE,
                        PERMISSION_WRITE_STORAGE,
                        PermissionUtil.PermissionMessage.WRITE_EXTERNAL_STORAGE, ""
                    )
                }
            }
            .setNeutralButton(R.string.option_cancel) { dialog, which -> dialog.dismiss() }

        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        builder.show()
    }

    /**
     * @param fileName keep file name in field. this will be required when getting permission.
     */
    fun openChooserDialog(fragment: Fragment, fileName: String) {
        if (PermissionUtil.checkPermission(
                fragment.context,
                PermissionUtil.Permissions.WRITE_EXTERNAL_STORAGE
            )
        ) {
            val builder = AlertDialog.Builder(fragment.requireContext())

            builder.setTitle(R.string.choose_image)
                .setPositiveButton(R.string.option_camera) { dialog, id ->
                    if (PermissionUtil.checkPermission(
                            fragment.context,
                            PermissionUtil.Permissions.CAMERA
                        )
                    ) {
                        startCameraIntent(fragment, fileName)
                    } else {
                        PermissionUtil.getPermission(
                            fragment,
                            PermissionUtil.Permissions.CAMERA,
                            PERMISSION_CAMERA,
                            PermissionUtil.PermissionMessage.CAMERA, ""
                        )
                    }
                }
                .setNegativeButton(R.string.option_gallery) { dialog, id ->
                    startGalleryIntent(
                        fragment
                    )
                }
                .setNeutralButton(R.string.option_cancel) { dialog, which -> dialog.dismiss() }

            val dialog = builder.create()

            dialog.setCanceledOnTouchOutside(true)
            dialog.setCancelable(true)
            builder.show()
        } else {
            PermissionUtil.getPermission(
                fragment,
                PermissionUtil.Permissions.WRITE_EXTERNAL_STORAGE,
                PERMISSION_WRITE_STORAGE,
                PermissionUtil.PermissionMessage.WRITE_EXTERNAL_STORAGE, ""
            )
        }
    }

    /**
     * @param fileName keep file name in field. this will be required when getting permission.
     */
    fun openCameraDialog(fragment: Fragment, fileName: String, requestCode: Int) {
        if (PermissionUtil.checkPermission(
                fragment.context,
                PermissionUtil.Permissions.WRITE_EXTERNAL_STORAGE
            )
        ) {
            if (PermissionUtil.checkPermission(
                    fragment.context,
                    PermissionUtil.Permissions.CAMERA
                )
            ) {
                startCameraIntent(fragment, fileName, requestCode)
            } else {
                PermissionUtil.getPermission(
                    fragment,
                    PermissionUtil.Permissions.CAMERA,
                    PERMISSION_CAMERA,
                    PermissionUtil.PermissionMessage.CAMERA, ""
                )
            }
        } else {
            PermissionUtil.getPermission(
                fragment,
                PermissionUtil.Permissions.WRITE_EXTERNAL_STORAGE,
                PERMISSION_WRITE_STORAGE,
                PermissionUtil.PermissionMessage.WRITE_EXTERNAL_STORAGE, ""
            )
        }
    }

    fun startGalleryIntent(activity: Activity) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        activity.startActivityForResult(
            Intent.createChooser(intent, "Select File"),
            REQUEST_GALLERY
        )
    }


    fun startGalleryIntent(fragment: Fragment) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        fragment.startActivityForResult(
            Intent.createChooser(intent, "Select File"),
            REQUEST_GALLERY
        )
    }

    fun startVideoGalleryIntent(fragment: Fragment) {
        val intent = Intent()
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        fragment.startActivityForResult(
            Intent.createChooser(intent, "Select File"),
            REQUEST_VIDEO_GALLERY
        )
    }


    fun startCameraIntent(activity: Activity, fileName: String) {
        val path = File(StorageUtils.createInternalDirectory(), IMAGE_DIRECTORY)

        if (!path.exists()) path.mkdirs()

        val image = File(path, fileName + FILE_EXTENSION)

        val imageUri = FileProvider.getUriForFile(
            activity,
            activity.packageName + CAPTURE_IMAGE_FILE_PROVIDER,
            image
        )

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val clip = ClipData.newUri(activity.contentResolver, "A photo", imageUri)

            intent.clipData = clip
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        } else {
            val resInfoList = activity.packageManager
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)

            for (resolveInfo in resInfoList) {
                val packageName = resolveInfo.activityInfo.packageName
                activity.grantUriPermission(
                    packageName, imageUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            }
        }

        activity.startActivityForResult(intent, REQUEST_CAMERA)
    }

    @SuppressLint("ObsoleteSdkInt")
    fun startCameraIntent(fragment: Fragment, fileName: String, requestCode: Int = REQUEST_CAMERA) {
        val path = File(StorageUtils.createInternalDirectory(), IMAGE_DIRECTORY)

        if (!path.exists()) path.mkdirs()

        val image = File(path, fileName + FILE_EXTENSION)

        val imageUri = FileProvider.getUriForFile(
            fragment.requireContext(),
            fragment.requireContext().packageName + CAPTURE_IMAGE_FILE_PROVIDER,
            image
        )

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val clip = ClipData.newUri(fragment.requireContext().contentResolver, "A photo", imageUri)

            intent.clipData = clip
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        } else {
            val resInfoList = fragment.requireContext().packageManager
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)

            for (resolveInfo in resInfoList) {
                val packageName = resolveInfo.activityInfo.packageName
                fragment.requireContext().grantUriPermission(
                    packageName, imageUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            }
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        fragment.startActivityForResult(intent, requestCode)
    }

    @SuppressLint("ObsoleteSdkInt")
    fun startVideoCameraIntent(
        fragment: Fragment,
        fileName: String,
        requestCode: Int = REQUEST_VIDEO_CAMERA
    ) {
        val path = File(StorageUtils.createInternalDirectory(), VIDEO_DIRECTORY)

        if (!path.exists()) path.mkdirs()

        val image = File(path, fileName + VIDEO_FILE_EXTENSION)

        val imageUri = FileProvider.getUriForFile(
            fragment.requireContext(),
            fragment.requireContext().packageName + CAPTURE_IMAGE_FILE_PROVIDER,
            image
        )

        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val clip = ClipData.newUri(fragment.requireContext().contentResolver, "A video", imageUri)

            intent.clipData = clip
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        } else {
            val resInfoList = fragment.requireContext().packageManager
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)

            for (resolveInfo in resInfoList) {
                val packageName = resolveInfo.activityInfo.packageName
                fragment.requireContext().grantUriPermission(
                    packageName, imageUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            }
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        fragment.startActivityForResult(intent, requestCode)
    }

    fun getGalleryImageFile(data: Intent?, resolver: ContentResolver, fileName: String): File? {
        return if (data != null) {
            try {
                saveImageToStorage(
                    MediaStore.Images.Media.getBitmap(resolver, data.data),
                    IMAGE_DIRECTORY,
                    fileName
                )
            } catch (exp: IOException) {
                e("ImageChooserUtil " + "getGalleryImageFile: ", exp.toString())
                null
            }

        } else {
            null
        }
    }


    fun getGalleryVideoFile(data: Intent?, resolver: ContentResolver, fileName: String): File? {
        return if (data != null) {
            try {
                val uri = data.data
                val filePath = RealPathUtil.getRealPath(BaseApp.INSTANCE, uri)
                return saveVideoToInternalStorage(filePath, VIDEO_DIRECTORY, fileName)
            } catch (exp: IOException) {
                e("ImageChooserUtil " + "getGalleryImageFile: ", exp.toString())
                null
            }

        } else {
            null
        }
    }

    private fun saveVideoToInternalStorage(
        filePath: String,
        videoDirectory: String,
        fileName: String
    ): File {

        var file: File? = null

        try {

            val createfile = File(filePath)
            val root = StorageUtils.createInternalDirectory()
            val myDir = File("$root/$videoDirectory")
            if (!myDir.exists()) {
                myDir.mkdirs()
            }
            val file = File(myDir, fileName + VIDEO_FILE_EXTENSION)
            if (file.exists()) {
                file.delete()
            }


            return createfile.copyTo(file, false, 1024)

/*
            try {
                val inStream = FileInputStream(createfile)
                val out = FileOutputStream(file)

                // Copy the bits from instream to outstream
                val buf = ByteArray(1024)
                var len: Int = 0

                var read = inStream.read(buf)
*/
/*
                while (read != -1) {
                    out.write(buf, 0, len);
                    read = inStream.read(buf)
                }
*//*


                while (true) {
                    val byteCount = inStream.read(buf)
                    if (byteCount < 0) break
                    out.write(buf, 0, byteCount)
                }
                inStream.close()
                out.close()
                Log.v("vii", "Video file saved successfully.")
                return file
            } catch (exp: Exception) {
                e("ImageChooserUtil " + "saveImageToStorage: ", exp.toString())
                return file
            }
*/


        } catch (e: Exception) {
            e.printStackTrace()
        }
        return file!!
    }

    fun getCameraImageFile(fileName: String): File {
        val path = File(StorageUtils.createInternalDirectory(), IMAGE_DIRECTORY)
        if (!path.exists()) path.mkdirs()
        val file = File(path, fileName + FILE_EXTENSION)
        if (!file.exists()){
            file.createNewFile()
        }
//        return file
//        COMPRESS Image
        return decodeImageFile(file, fileName)
    }


    fun getCameraVideoFile(fileName: String): File {
        val path = File(StorageUtils.createInternalDirectory(), VIDEO_DIRECTORY)
        if (!path.exists()) path.mkdirs()
        return File(path, fileName + VIDEO_FILE_EXTENSION)
    }


    private fun saveImageToStorage(finalBitmap: Bitmap, path: String, imageName: String): File {
        val root = StorageUtils.createInternalDirectory()
        val myDir = File("$root/$path")
        if (!myDir.exists()) {
            myDir.mkdirs()
        }
        val file = File(myDir, imageName + FILE_EXTENSION)
        if (file.exists()) {
            file.delete()
        }
        try {
            val out = FileOutputStream(file)
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 40, out)
            out.flush()
            out.close()
//            return file
//            COMPRESS IMAGE
            return decodeImageFile(file, imageName)
        } catch (exp: Exception) {
            e("ImageChooserUtil " + "saveImageToStorage: ", exp.toString())
            return file
        }

    }


    class SaveImageTask(
        private val data: Intent?,
        private val requestCode: Int,
        private val fileName: String,
        private val listener: FileSaveListener?
    ) : AsyncTask<Void, Void, File>() {

        override fun doInBackground(vararg pObjects: Void): File? {
            var file: File? = null

            if (requestCode == REQUEST_GALLERY) {
                file = getGalleryImageFile(
                    data,
                    BaseApp.INSTANCE.contentResolver, fileName
                )
            } else if (requestCode == REQUEST_CAMERA) {
                file = getCameraImageFile(fileName)
            }

            return file
        }

        override fun onPostExecute(file: File) {
            listener?.fileSaved(file)
            super.onPostExecute(file)
        }

        interface FileSaveListener {
            fun fileSaved(file: File)
        }
    }


    class SaveVideoTask(
        private val data: Intent?,
        private val requestCode: Int,
        private val fileName: String,
        private val listener: FileSaveListener?
    ) : AsyncTask<Void, Void, File>() {

        override fun doInBackground(vararg pObjects: Void): File? {
            var file: File? = null

            if (requestCode == REQUEST_VIDEO_GALLERY) {
                file = getGalleryVideoFile(
                    data,
                    BaseApp.INSTANCE.contentResolver, fileName
                )
            } else if (requestCode == REQUEST_VIDEO_CAMERA) {
                val uri = data?.data
                file = getCameraVideoFile(fileName)

            }

            return file
        }

        override fun onPostExecute(file: File) {
            listener?.fileSaved(file)
            super.onPostExecute(file)
        }

        interface FileSaveListener {
            fun fileSaved(file: File)
        }
    }


    private fun decodeImageFile(filetopupload: File, fileName: String): File {
        var b: Bitmap? = null
        var destFile: File

        //Decode image size
        val o = BitmapFactory.Options()
        o.inJustDecodeBounds = true

        var fis: FileInputStream? = null
        try {
            fis = FileInputStream(filetopupload)
            BitmapFactory.decodeStream(fis, null, o)
            fis.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val IMAGE_MAX_SIZE = 2048
        var scale = 1
        if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
            scale = Math.pow(
                2.0,
                Math.ceil(
                    Math.log(
                        IMAGE_MAX_SIZE / Math.max(
                            o.outHeight,
                            o.outWidth
                        ).toDouble()
                    ) / Math.log(0.5)
                ).toInt().toDouble()
            ).toInt()
        }

        //Decode with inSampleSize
        val o2 = BitmapFactory.Options()
        o2.inSampleSize = scale
        try {
            fis = FileInputStream(filetopupload)
            b = BitmapFactory.decodeStream(fis, null, o2)
            fis.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val path = File(StorageUtils.createInternalDirectory(), IMAGE_DIRECTORY)
        if (!path.exists()) path.mkdirs()
        destFile = File(path, fileName + FILE_EXTENSION)
        try {
            val out = FileOutputStream(destFile)
            b?.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return destFile
    }

}
