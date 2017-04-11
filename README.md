# MyMVPbase
这是个mvp项目， 主要是MVP模式和多个类型RV的写法。

这是我学习MVP 模式是写的一个简单的demo
里面的数据来源与网易新闻。

里面主要是进行了
1.Fragment切换是的另外一种写法
   public Fragment switchContent(int id) {
        //instantiateItem从FragmentManager中查找Fragment，找不到就getItem新建一个，
        Fragment fragment= (Fragment) mfactory.instantiateItem(mFragment,id);
        mfactory.setPrimaryItem(mFragment, 0, fragment);
        //最后finishUpdate提交事务。
        mfactory.finishUpdate(mFragment);
        return fragment;
    }

2.使用第三方库AdapterDeleger 来进行RecyclerView 的添加 加载中，没有数据 foot。

3.将Fragment的懒加载放在baseFragment 中。
