package com.loopj.android.http.abstracts;

import com.loopj.android.http.interfaces.IAsyncHttpClient;
import com.loopj.android.http.interfaces.IAsyncHttpClientOptions;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContextHC4;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractAsyncHttpClient implements IAsyncHttpClient {

    @NotNull
    protected CloseableHttpClient mHttpClient;

    @NotNull
    protected ExecutorService mExecutorService = Executors.newCachedThreadPool();

    @NotNull
    protected IAsyncHttpClientOptions mOptions;

    @NotNull
    private HttpContext mHttpContext = new SyncBasicHttpContext(new BasicHttpContextHC4());

    public AbstractAsyncHttpClient(@NotNull final IAsyncHttpClientOptions asyncHttpClientOptions) {
        HttpClientBuilder mBuilder = HttpClientBuilder.create();
        mHttpClient = asyncHttpClientOptions.buildHttpClient(mBuilder);
        mOptions = asyncHttpClientOptions;
    }

    @NotNull
    @Override
    public CloseableHttpClient getHttpClient() {
        return mHttpClient;
    }

    @NotNull
    @Override
    public ExecutorService getThreadPool() {
        return mExecutorService;
    }

    @Override
    public void setThreadPool(@NotNull ExecutorService executorService) {
        this.mExecutorService = executorService;
    }

    @Override
    public boolean isSynchronous() {
        return mOptions.isSynchronous();
    }

    @NotNull
    @Override
    public IAsyncHttpClientOptions getConfigurationOptions() {
        return mOptions;
    }

    @NotNull
    @Override
    public HttpContext getHttpContext() {
        return mHttpContext;
    }

    @Override
    public void setHttpContext(@NotNull HttpContext httpContext) {
        this.mHttpContext = httpContext;
    }

    @Nullable
    @Override
    public CredentialsProvider getCredentialsProvider() {
        return mOptions.getDefaultCredentialsProvider();
    }

    @Override
    public void addCredentials(@NotNull AuthScope scope, @NotNull UsernamePasswordCredentials usernamePasswordCredentials) {
        if (getCredentialsProvider() != null) {
            getCredentialsProvider().setCredentials(scope, usernamePasswordCredentials);
        }
    }
}
