package com.ad.ad_core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.ad.ad_core.app.LoadConfig;
import com.ad.ad_core.net.callback.ISuccess;
import com.ad.ad_core.utils.ADFileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;


/**
*@author 杜立茂
*@date 2019/1/21 10:13
*@description 向本地写入文件流的异步任务
*/
public class SaveFileAsyncTask extends AsyncTask<Object,Void, File> {

    private ISuccess mISuccess;

    public SaveFileAsyncTask(ISuccess iSuccess){
        this.mISuccess = iSuccess;
    }

    @Override
    protected File doInBackground(Object... params) {

        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        String name = (String) params[2];
        ResponseBody body = (ResponseBody) params[3];
        InputStream is = body.byteStream();

        if (downloadDir == null || downloadDir.equals("")){
            downloadDir = "download_dir";
        }

        if (extension == null || extension.equals("")){
            extension = "";
        }

        if (name == null || name.equals("")){
            return ADFileUtil.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);
        }else {
            return ADFileUtil.writeToDisk(is,downloadDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);

        if (mISuccess != null){
            mISuccess.onSuccess("文件下载 路径：" + file.getAbsolutePath());
        }
        install(file);
    }

    //如果是apk文件则自动安装
    private void install(File file){
        if (ADFileUtil.getExtension(file.getPath()).equals("apk")){
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            LoadConfig.getApplicationContext().startActivity(intent);
        }


    }
}
