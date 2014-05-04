package com.loopj.android.http.interfaces;

import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IAsyncHttpClientOptions {

    @NotNull
    IAsyncHttpClientOptions setIsSynchronous(final boolean isSynchronous);

    public boolean isSynchronous();

    @NotNull
    IAsyncHttpClientOptions setSSLSocketFactory(@NotNull SSLSocketFactory sslSocketFactory);

    @Nullable
    public SSLSocketFactory getSSLSocketFactory();

    @NotNull
    IAsyncHttpClientOptions setURLEncodingEnabled(final boolean isUrlEncodingEnabled);

    public boolean isURLEncodingEnabled();

    @NotNull
    IAsyncHttpClientOptions setBasicAuth();

    @NotNull
    IAsyncHttpClientOptions setUserAgent(@NotNull final String userAgent);

    @Nullable
    public String getUserAgent();

    @NotNull
    IAsyncHttpClientOptions setIsHandlingRedirects(final boolean enableRedirects, final boolean enableRelativeRedirects, final boolean enableCircularRedirects);

    public boolean isHandlingRedirects();

    public boolean isHandlingCircularRedirects();

    public boolean isHandlingRelativeRedirects();

    @NotNull
    IAsyncHttpClientOptions setEnableContentCompression(final boolean enableContentCompression);

    public boolean isContentCompressionEnabled();

    @NotNull
    IAsyncHttpClientOptions setKeepConnectionsAlive(boolean keepAlive);

    public boolean areConnectionsKeptAlive();

    @NotNull
    IAsyncHttpClientOptions setConfigurationInterceptor(@NotNull final IConfigurationInterceptor configurationInterceptor);

    @Nullable
    public IConfigurationInterceptor getConfigurationInterceptor();

    @NotNull
    IAsyncHttpClientOptions setMaxParallelConnectionsTotal(final int maxParallelConnections);

    public int getMaxParallelConnectionsTotal();

    @NotNull
    IAsyncHttpClientOptions setMaxParallelConnectionsPerRoute(final int maxParallelConnectionsPerRoute);

    public int getMaxParallelConnectionsPerRoute();

    @NotNull
    IAsyncHttpClientOptions setDefaultCredentialsProvider(@NotNull CredentialsProvider credentialsProvider);

    @Nullable
    public CredentialsProvider getDefaultCredentialsProvider();

    @NotNull
    CloseableHttpClient buildHttpClient(@NotNull HttpClientBuilder httpClientBuilder);

}
