package com.lidiwo.android.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lidiwo.android.R;
import com.lidiwo.android.base_module.base.BaseActivity;
import com.lidiwo.android.base_module.glide.ImageLoader;
import com.lidiwo.android.base_module.mvp.IView;
import com.lidiwo.android.log.AndroidLog;
import com.lidiwo.android.mvp.contract.MainContract;
import com.lidiwo.android.mvp.presenter.MainPresenter;

import java.util.HashMap;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {

    @BindView(R.id.tv_phone)
    TextView tv_phone;

    @BindView(R.id.iv_img)
    ImageView iv_img;


    private static final String DATA_URI =
            "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD//gA7Q1JFQVRPUjogZ2QtanBlZyB2MS4wICh1c2luZ"
                    + "yBJSkcgSlBFRyB2ODApLCBxdWFsaXR5ID0gNzUK/9sAQwAIBgYHBgUIBwcHCQkICgwUDQwLCwwZEhMPFB0aHx4"
                    + "dGhwcICQuJyAiLCMcHCg3KSwwMTQ0NB8nOT04MjwuMzQy/9sAQwEJCQkMCwwYDQ0YMiEcITIyMjIyMjIyMjIyM"
                    + "jIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIy/8AAEQgAZABkAwEiAAIRAQMRAf/EAB8AAAE"
                    + "FAQEBAQEBAAAAAAAAAAABAgMEBQYHCAkKC//EALUQAAIBAwMCBAMFBQQEAAABfQECAwAEEQUSITFBBhNRYQcic"
                    + "RQygZGhCCNCscEVUtHwJDNicoIJChYXGBkaJSYnKCkqNDU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN"
                    + "0dXZ3eHl6g4SFhoeIiYqSk5SVlpeYmZqio6Slpqeoqaqys7S1tre4ubrCw8TFxsfIycrS09TV1tfY2drh4uPk5"
                    + "ebn6Onq8fLz9PX29/j5+v/EAB8BAAMBAQEBAQEBAQEAAAAAAAABAgMEBQYHCAkKC//EALURAAIBAgQEAwQHBQQ"
                    + "EAAECdwABAgMRBAUhMQYSQVEHYXETIjKBCBRCkaGxwQkjM1LwFWJy0QoWJDThJfEXGBkaJicoKSo1Njc4OTpDR"
                    + "EVGR0hJSlNUVVZXWFlaY2RlZmdoaWpzdHV2d3h5eoKDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i"
                    + "5usLDxMXGx8jJytLT1NXW19jZ2uLj5OXm5+jp6vLz9PX29/j5+v/aAAwDAQACEQMRAD8A+f6KKKACiiigAoooo"
                    + "AKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAoorU8NabDrPirSNLuGkWC9vYbe"
                    + "RoyAwV3CkjIIzg+hoAy6K6HxJ4VvdH1bVxbWF++kWd/Pax3kkLFCEkKDLgBc8DPvWbpuh6trLMul6Xe3xT7wtb"
                    + "d5dv12g0AUKKmuLO6tLtrS5tpoblG2tDIhV1PoVPOauX3h7W9LtluNQ0fULSBjhZbi2eNSfYkAUAZtFX9O0LV9"
                    + "XSR9N0q+vUi/1jW1u8gT67QcVe8K+GJ/E3iNNK85bRFV5bqeYcQRIMuxHsB09cdKAMKiu9vtC8A3+h6jN4c13U"
                    + "ItRsI/N8nV/KjS7UH5hFjndjkKeT6dxj+B/B9z408RQ6dFIILYFTc3LfdiQsFHXqxJAUdyaAOaorU8S6bDo3ir"
                    + "V9Lt2kaCyvZreNpCCxVHKgnAAzgegrLoAKKKKACug8Cf8lD8Nf9hW1/8ARq1z9WLC+uNM1G2v7OTy7q1lSaF9o"
                    + "O11IKnB4OCB1oA96sNX8R6l8bPEGi6zJcv4cCXaXdq4IgjtdrFHx90E/L83U5PJrnPC0t7pnw/0i51nxjq2laT"
                    + "czzLp1losH76Rlf5y8igH72cKxPHTjiuJv/iR4w1PSbjS7vXbiSzuZHkmjAVd5dizDIAO0kn5c7e2MUzQPiD4r"
                    + "8L6e9ho2sS21q7FjFsRwCepG4Hb+GKAPT/Gt/Novxj8G6pDpF/qlwmkQO1rNFm6mb96pLKoP70DDHA4K1FfXv8"
                    + "Awk3hvxNN4e8aa8yxWbzXuka5AJgIx95VkOVRgemPm4/GvK9Q8Y+IdVu7C7vtVnmu9Pz9muTgSp82774G44PTJ"
                    + "OO1Xtb+JPjDxFpp07VNcnntGxviCJGHwc/NtUFufWgD1O41XQ/DfgPweJb7xjY2s2npKH0B4khknJzJvZuS+7s"
                    + "TjGMDrS6ZrNlqPxU1Oe10TUVur3wxLELXVrZYZb6cAHJVDgh0TqMZ5wBXk/h74heK/Ctk1no2szW1sxJ8ookig"
                    + "nqQHB2/hisqbXtWuNb/ALal1G5bU94kF0ZD5gYdCD2xQB6ZpXiLVfHPhTxXaeKbe3ksdL017i0mW0SE2lwpASN"
                    + "SoGM8jB54xWj4ObwYbTwhpen+MPsd59utby+s/wCzJne8uw6lY2l4UKpyo6jJLHNeb6/8QvFfiiwSx1jWZrm1Q"
                    + "7vK2IisexbaBu/HNYNhfXGmajbX9nJ5d1aypNC+0Ha6kFTg8HBA60AdR8ULTTrT4h6x/Z2qfb/Ou5pbj/R2i+z"
                    + "zGV90XP3tvHzDg5rj6sX99canqNzf3knmXV1K80z7QNzsSWOBwMknpVegAooooAKKKKACiiigAooooAKKKKACi"
                    + "iigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAoo"
                    + "ooA//2Q==";



    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidLog.e("MainActivity..............onCreate");
        ImageLoader.with(this).load("");
        Glide.with(this).load("");

    }

    @Override
    protected IView takeView() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        AndroidLog.e("MainActivity..............onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AndroidLog.e("MainActivity..............onDestroy");
    }


    @Override
    public void demo() {
        AndroidLog.e("MainActivity..............demo");

    }

    private int index = 1;

    public void pageOne(View view) throws Exception {
        AndroidLog.v("@@@:" + tv_phone + "@#");

        ImageLoader.with(this)
                .load(DATA_URI)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(iv_img);


        mPresenter.aa();

//        HashMap<String, Object> parms = new HashMap<>();
//        String url = "https://imtt.dd.qq.com/16891/FA37C9FD6CE4795E76144D0D81516DC1.apk?fsname=com.tencent.mobileqq_7.8.5_936.apk";
//        String path=getExternalCacheDir().getAbsolutePath();

//        RxRetrofitClient.create().download(url, parms, path, "QQ", "apl", new RequestDownloadProgress() {
//            @Override
//            public void downloadProgress(int progress, long networkSpeed, boolean downloadFinish) {
//                AndroidLog.i("@@::" + progress);
//                AndroidLog.i("@@::" + networkSpeed);
//                AndroidLog.i("@@::" + downloadFinish);
//
//                tv_content.setText("下载进度：" + progress);
//
//                if (downloadFinish) {
//                    Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_LONG).show();
//                    AndroidLog.i("##########################################################" );
//                }
//            }
//
//            @Override
//            public void saveProgress(int progress, boolean saveFinish) {
//                tv_save.setText("保存进度：" + progress);
//                AndroidLog.e("@@::" + progress);
//                AndroidLog.e("@@::" + saveFinish);
//
//                if (saveFinish) {
//                    Toast.makeText(MainActivity.this, "保存完成", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        }, new RequestResult() {
//            @Override
//            public void onSuccess(String response) {
//                AndroidLog.e("请求成功..............."+response);
//            }
//
//            @Override
//            public void onError(int code, String message) {
//                AndroidLog.e("请求错误...............  code："+code +"message:"+message);
//            }
//
//            @Override
//            public void onFailure() {
//                AndroidLog.e("请求失败...............");
//            }
//
//            @Override
//            public void onStart() {
//                AndroidLog.e("请求开始...............");
//            }
//
//            @Override
//            public void onStop() {
//                AndroidLog.e("请求结束...............");
//            }
//        });

//        RetrofitClient.create().download(url, parms, path, "QQ", "apk", new RequestDownloadProgress() {
//            @Override
//            public void downloadProgress(int progress, long networkSpeed, boolean downloadFinish) {
//                AndroidLog.i("@@::" + progress);
//                AndroidLog.i("@@::" + networkSpeed);
//                AndroidLog.i("@@::" + downloadFinish);
//
//                tv_content.setText("下载进度："+progress);
//
//                if (downloadFinish) {
//                    Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void saveProgress(int progress, boolean saveFinish) {
//                tv_save.setText("保存进度："+progress);
//                AndroidLog.e("@@::" + progress);
//                AndroidLog.e("@@::" + saveFinish);
//
//                if (saveFinish) {
//                    Toast.makeText(MainActivity.this, "保存完成", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        }, new RequestResult<String>() {
//            @Override
//            public void onSuccess(String response) {
//                AndroidLog.e("请求成功..............."+response);
//            }
//
//            @Override
//            public void onError(int code, String message) {
//                AndroidLog.e("请求错误...............  code："+code +"message:"+message);
//            }
//
//            @Override
//            public void onFailure() {
//                AndroidLog.e("请求失败...............");
//            }
//
//            @Override
//            public void onStart() {
//                AndroidLog.e("请求开始...............");
//            }
//
//            @Override
//            public void onStop() {
//                AndroidLog.e("请求结束...............");
//            }
//        });

//        RxRetrofitClient.create().download(url, null, null, new RequestDownloadProgress() {
//            @Override
//            public void downloadProgress(int progress, long networkSpeed, boolean downloadFinish) {
//                AndroidLog.i("@@::" + progress);
//                AndroidLog.i("@@::" + networkSpeed);
//                AndroidLog.i("@@::" + downloadFinish);
//
//                tv_content.setText("@@"+progress);
//
//                if (downloadFinish) {
//                    Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        }, new RequestResult() {
//            @Override
//            public void onSuccess(String response) {
//
//            }
//        });

//        RetrofitManager.getService().download(url,parms).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                AndroidLog.i("请求成功"+response.isSuccessful());
//                AndroidLog.i("请求成功"+response.message());
//                ResponseBody responseBody=  response.body();
//
//
//                AndroidLog.i("请求成功"+responseBody.contentType().toString());
//                AndroidLog.i("请求成功"+response.code());
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                AndroidLog.i("请求失败"+t.getMessage());
//            }
//        });

        String url = "https://shopapi.io.mi.com/app/shop/gpipe";
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("data", "{\"HomepageBuildHome\":{\"model\":\"Homepage\",\"action\":\"BuildHome\",\"parameters\":{\"id\":77}}}");

//        RxRetrofitClient.create().post(url,parms).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        AndroidLog.v("11111111111111111");
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        AndroidLog.v("222222222222222"+s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        AndroidLog.v("3333333333333"+e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        AndroidLog.v("44444444444");
//                    }
//                });

//        RetrofitManager.getService().post(url, parms).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                AndroidLog.i("#@@::"+response.toString());
//                AndroidLog.i("#@@::"+response.message());
//                AndroidLog.json("@@@",response.body());
//
//                Toast.makeText(MainActivity.this,"@@@@@"+response.toString(),Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });

//        RetrofitClient.create().setUrl(url).setParams(parms).setRequestObserver(new RequestObserver() {
//            @Override
//            public void onStart() {
//                AndroidLog.e("请求开始...............");
//            }
//
//            @Override
//            public void onStop() {
//                AndroidLog.e("请求结束...............");
//            }
//        }).setRequestSuccess(new RequestSuccess() {
//            @Override
//            public void onSuccess(String response) {
//                AndroidLog.e("请求成功..............."+response);
//            }
//        }).setRequestFailure(new RequestFailure() {
//            @Override
//            public void onFailure() {
//                AndroidLog.e("请求失败...............");
//            }
//        }).setRequestError(new RequestError() {
//            @Override
//            public void onError(int code, String message) {
//                AndroidLog.e("请求错误...............  code："+code +"message:"+message);
//            }
//        }).build().post();


//        RxRetrofitClient.create().post(url, parms, new RequestResult() {
//            @Override
//            public void onSuccess(String response) {
//                AndroidLog.e("请求成功..............."+response);
//            }
//
//            @Override
//            public void onError(int code, String message) {
//                AndroidLog.e("请求错误...............  code："+code +"message:"+message);
//            }
//
//            @Override
//            public void onFailure() {
//                AndroidLog.e("请求失败...............");
//            }
//
//            @Override
//            public void onStart() {
//                AndroidLog.e("请求开始...............");
//            }
//
//            @Override
//            public void onStop() {
//                AndroidLog.e("请求结束...............");
//            }
//        });
    }


}
