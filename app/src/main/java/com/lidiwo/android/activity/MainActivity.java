package com.lidiwo.android.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;


import com.lidiwo.android.R;
import com.lidiwo.android.base_module.base.BaseActivity;
import com.lidiwo.android.base_module.mvp.IView;
import com.lidiwo.android.log.AndroidLog;
import com.lidiwo.android.mvp.contract.MainContract;
import com.lidiwo.android.mvp.presenter.MainPresenter;

import java.util.HashMap;

import butterknife.BindView;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {

    @BindView(R.id.tv_phone)
    TextView tv_phone;

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidLog.e("MainActivity..............onCreate");
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
        AndroidLog.v("@@@:"+tv_phone+"@#");

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
