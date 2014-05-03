package com.loopj.android.http.sample;

import android.util.Log;

import com.loopj.android.http.handlers.FileAsyncHttpResponseHandler;
import com.loopj.android.http.interfaces.IAsyncHttpClient;
import com.loopj.android.http.interfaces.IRequestHandle;
import com.loopj.android.http.interfaces.IResponseHandler;
import com.loopj.android.http.sample.util.FileUtil;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

import java.io.File;

public class FileSample extends SampleParentActivity {
    private static final String LOG_TAG = "FileSample";

    @Override
    public int getSampleTitle() {
        return R.string.title_file_sample;
    }

    @Override
    public boolean isRequestBodyAllowed() {
        return false;
    }

    @Override
    public boolean isRequestHeadersAllowed() {
        return true;
    }

    @Override
    public String getDefaultURL() {
        return "https://httpbin.org/robots.txt";
    }

    @Override
    public IResponseHandler getResponseHandler() {
        return new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onStart() {
                clearOutputs();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File response) {
                debugHeaders(LOG_TAG, headers);
                debugStatusCode(LOG_TAG, statusCode);
                debugFile(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                debugHeaders(LOG_TAG, headers);
                debugStatusCode(LOG_TAG, statusCode);
                debugThrowable(LOG_TAG, throwable);
                debugFile(file);
            }

            private void debugFile(File file) {
                if (file == null || !file.exists()) {
                    debugResponse(LOG_TAG, "Response is null");
                    return;
                }
                try {
                    debugResponse(LOG_TAG, file.getAbsolutePath() + "\r\n\r\n" + FileUtil.getStringFromFile(file));
                } catch (Throwable t) {
                    Log.e(LOG_TAG, "Cannot debug file contents", t);
                }
                if (!deleteTargetFile()) {
                    Log.d(LOG_TAG, "Could not delete response file " + file.getAbsolutePath());
                }
            }
        };
    }

    @Override
    public IRequestHandle executeSample(IAsyncHttpClient client, String URL, Header[] headers, HttpEntity entity, IResponseHandler responseHandler) {
        return client.get(this, URL, headers, null, responseHandler);
    }
}
