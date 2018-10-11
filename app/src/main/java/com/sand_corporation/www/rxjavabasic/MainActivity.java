package com.sand_corporation.www.rxjavabasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private TextView txtObserver;
    private EditText edtObservable;
    private Button btnOnCLick;

    private Observable<String> observable;
    private Observer<String> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtObserver = findViewById(R.id.txtObserver);
        edtObservable = findViewById(R.id.edtObservable);
        btnOnCLick = findViewById(R.id.btnOnCLick);

        createObservable();
        btnOnCLick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observable.subscribe(observer);
            }
        });
    }

    private void createObservable() {
        observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(edtObservable.getText().toString());
                emitter.onComplete();
            }
        });

        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                txtObserver.setText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
