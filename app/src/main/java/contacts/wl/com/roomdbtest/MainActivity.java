package contacts.wl.com.roomdbtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import contacts.wl.com.roomdbtest.roomdb.Medal;
import contacts.wl.com.roomdbtest.roomdb.RoomDB;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView tv_size;
    private Button bt_migration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_size = findViewById(R.id.tv_size);
        bt_migration = findViewById(R.id.bt_tomigration);



        final List<Medal> data = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Medal tmp = new Medal();
            tmp.uid = "10000" + i;
            tmp.name = "name" + i;
            tmp.img = System.currentTimeMillis() + "";
            data.add(tmp);
        }


//        Flowable.fromCallable(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//
//                RoomDB.getInstance()
//                        .getMedalDao()
//                        .insertAll(data);
//                return "";
//            }
//        })
//                .compose(transform())
//                .subscribe();


        RoomDB.getInstance()
                .getMedalDao()
                .getAll()
                .compose(transform())
                .subscribe(new Subscriber<List<Medal>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE

                        );

                    }

                    @Override
                    public void onNext(List<Medal> medals) {
                        tv_size.setText("size is " + medals.size());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private FlowableTransformer transform() {
        return new FlowableTransformer() {
            @Override
            public Publisher apply(Flowable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    private ObservableTransformer transform2() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

    }
}
