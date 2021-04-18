package com.bin23.servicebestpractice.utils;

/**
 * 回调接口
 */
public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
