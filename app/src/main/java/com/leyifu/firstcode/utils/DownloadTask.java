package com.leyifu.firstcode.utils;

import android.os.AsyncTask;
import android.os.Environment;

import com.leyifu.firstcode.interf.DownLoadListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xingxing on 2017/6/28.
 */
public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    private static final int TYPE_SUCCEED = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_PAUSED = 2;
    private static final int TYPE_CANCELED = 3;
    private DownLoadListener listener;

    private boolean isCanceled = false;
    private boolean isPaused = false;

    private int lastProgress;

    public DownloadTask(DownLoadListener listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        InputStream inputStream = null;
        File file = null;
        RandomAccessFile randomAccessFile = null;
        try {
            long downloadLenght = 0;
            String downloadUrl = params[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            if (file.exists()) {
                downloadLenght = file.length();
            }
            long contentLenght = getContentLenght(downloadUrl);
            if (contentLenght == 0) {
                return TYPE_FAILED;
            } else if (contentLenght == downloadLenght) {
                //已下载字节跟总字节相等，则下载成功
                return TYPE_SUCCEED;
            }
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .get()
                    .url(downloadUrl)
                    //断点下载，指定位置开始下载
                    .addHeader("RANGE", "bytes=" + downloadLenght + "-")
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response != null) {
                inputStream = response.body().byteStream();
                randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(downloadLenght);  //跳过已经下载的部分
                byte[] bytes = new byte[1024];
                int total = 0;
                int len;
                while ((len = inputStream.read(bytes)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        randomAccessFile.write(bytes, 0, len);
                        int progress = (int) (((total + downloadLenght) * 100 / contentLenght));
                        publishProgress(progress);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                if (isCancelled() && file != null) {
                    file.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return TYPE_FAILED;
    }

    /**
     * 获取文件的总字节
     *
     * @param downloadUrl
     * @return
     */
    private long getContentLenght(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.body().close();
            return contentLength;
        }
        return 0;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int progress = values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }

    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        switch (integer) {
            case TYPE_SUCCEED:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_CANCELED:
                listener.onCancaled();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
        }
    }

    public void pauseDoanload() {
        isPaused = true;
    }

    public void cancelDoanload() {
        isCanceled = true;
    }


}
