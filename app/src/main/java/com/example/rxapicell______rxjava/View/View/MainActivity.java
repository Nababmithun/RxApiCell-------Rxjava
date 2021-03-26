package com.example.rxapicell______rxjava.View.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.rxapicell______rxjava.R;
import com.example.rxapicell______rxjava.View.View.Model.ResponsePojo;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @BindView(R.id.tvBlog)
    TextView tvBlog;

    @BindView(R.id.btnLoad)
    Button btnLoad;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading.........");


    }
    @OnClick(R.id.btnLoad)
    public void loadStudentList(View view) {
        progressDialog.show();
        getDataFromAPI();
    }

    private void getDataFromAPI() {

        ApiService apiService = NetworkManager.createRetrofit().create(ApiService.class);
        apiService.getDataFromAPI()

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponsePojo>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponsePojo data) {
                        progressDialog.dismiss();
                        tvName.setText("Name : " + data.getName());
                        tvEmail.setText("Email : " + data.getEmail());
                        tvBlog.setText("Blog : " + data.getBlog());
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtil.printLogMessage("error response", t.getMessage());
                        progressDialog.dismiss();
                        tvName.setText("error :   " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }

                });

    }

}