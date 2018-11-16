package com.lidiwo.android.base_module.http.callback;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/22 18:55
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class RequestCallback implements Callback<String> {
    private final RequestObserver REQUEST;
    private final RequestResult RESULT;
    private final RequestSuccess SUCCESS;
    private final RequestFailure FAILURE;
    private final RequestError ERROR;

    public RequestCallback(RequestObserver REQUEST, RequestResult RESULT, RequestSuccess SUCCESS, RequestFailure FAILURE, RequestError ERROR) {
        this.REQUEST = REQUEST;
        this.RESULT = RESULT;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(SUCCESS!=null){
                    SUCCESS.onSuccess(response.body());
                }

                if(RESULT!=null){
                    RESULT.onSuccess(response.body());
                }
            }
        }else{
            if(ERROR!=null){
                ERROR.onError(response.code(),response.message());
            }

            if(RESULT!=null){
                RESULT.onError(response.code(),response.message());
            }
        }

        if (REQUEST != null) {
            REQUEST.onStop();
        }

        if(RESULT!=null){
            RESULT.onStop();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (RESULT != null) {
            RESULT.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onStop();
        }

        if(RESULT!=null){
            RESULT.onStop();
        }
    }
}
