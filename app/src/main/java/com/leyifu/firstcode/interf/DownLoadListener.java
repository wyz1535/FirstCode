package com.leyifu.firstcode.interf;

/**
 * Created by xingxing on 2017/6/3.
 */
public interface DownLoadListener {

    void onSuccess();

    void onFailed();

    void onProgress(int progress);

    void onPaused();

    void onCancaled();
}
