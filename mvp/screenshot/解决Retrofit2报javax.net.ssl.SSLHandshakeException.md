### 前言
最近在网上找到了一个新闻API接口，这里也推荐下[News API](https://newsapi.org/)，可以根据请求参数获取相关新闻，支持国家区域定制，得到的新闻json数据有多种语言，个人使用免费，在调用获取新闻后，报出一下错误：
javax.net.ssl.SSLHandshakeException: java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.

### 解决方案
在网上搜索一番，发现这两篇对我对有用，它们分别是
[解决OKHttp3 报OKHTTP javax.net.ssl.SSLHandshakeException错误](https://blog.csdn.net/quincyjiang/article/details/76273446)

[retrofit2中ssl的Trust anchor for certification path not found问题](https://www.cnblogs.com/maomishen/p/5403301.html)，最后发现只有这个忽略SSL检测方案可以解决这个问题，不过还是不建议这么做，因为在[News API](https://newsapi.org/)没有找到相关的证书说明文档，索性就忽略吧。代码如下，思路就是利用反射，全部工程文件可以参考- [鲤鱼日语](https://github.com/54wall/LiYuJapanese)

```java
package pri.weiqiang.liyujapanese.network.newsapi;

import android.util.Log;

import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import pri.weiqaing.common.config.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsApiNetworks {

    private static final int DEFAULT_TIMEOUT = 5000;//5
    private static Retrofit retrofit;
    private static NewsApiNetworks mNetworks;
    private NewsApiCommon newsApiCommon;
    private String TAG = NewsApiNetworks.class.getSimpleName();

    private OkHttpClient sClient = new OkHttpClient.Builder().connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();

    public static NewsApiNetworks getInstance() {
        if (mNetworks == null) {
            mNetworks = new NewsApiNetworks();
        }
        return mNetworks;
    }

    public NewsApiCommon getCommonApi() {
        Log.e(TAG, "newsApiCommon==null " + (newsApiCommon == null));
        return newsApiCommon == null ? configRetrofit(NewsApiCommon.class) : newsApiCommon;
    }

    private void ignoreSSLCheck() {
        Log.e(TAG, "ignoreSSLCheck()");
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {

                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {

                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }

        HostnameVerifier hv1 = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        String workerClassName = "okhttp3.OkHttpClient";
        try {
            Class workerClass = Class.forName(workerClassName);
            Field hostnameVerifier = workerClass.getDeclaredField("hostnameVerifier");
            hostnameVerifier.setAccessible(true);
            hostnameVerifier.set(sClient, hv1);

            Field sslSocketFactory = workerClass.getDeclaredField("sslSocketFactory");
            sslSocketFactory.setAccessible(true);
            sslSocketFactory.set(sClient, sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*使用范型可以返回ThemeApi 或者 CommonApi不用每个都写一个方法*/
    private <T> T configRetrofit(Class<T> service) {
        ignoreSSLCheck();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEWS_API_BASE_URL)
                .client(sClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Log.e(TAG, "retrofit.create(service)==null " + (retrofit.create(service) == null));
        return retrofit.create(service);
    }
}

```

### 参考

[解决OKHttp3 报OKHTTP javax.net.ssl.SSLHandshakeException错误](https://blog.csdn.net/quincyjiang/article/details/76273446)

[retrofit2中ssl的Trust anchor for certification path not found问题](https://www.cnblogs.com/maomishen/p/5403301.html)