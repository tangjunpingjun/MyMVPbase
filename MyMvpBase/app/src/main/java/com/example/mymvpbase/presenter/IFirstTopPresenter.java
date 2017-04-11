package com.example.mymvpbase.presenter;

/**
 * firsttopFragment 的操作层
 * 因为在first中view是一样的，所以有type来判断是那个pager
 */

public interface IFirstTopPresenter<E> {

    void loadData(E type,int pager); //开始更新数据，为哪个type，第几页



}
