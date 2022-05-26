package pri.weiqaing.common.base.mvp;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


public interface BaseModel<T> {


    //增加对RxJava2的生命周期管理，避免The result of subscribe is not used less... (Ctrl+F1)
    //Inspection info:Some methods have no side effects, an calling them without doing something without the result is suspicious.  Issue id: CheckResult
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    void unsubscribe();

    List<T> getData();



}
