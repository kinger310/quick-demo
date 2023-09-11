package com.wd.demo.jvm.dump.memoryleak;

/**
 * @Author: wangd
 * @Date: 2023/5/16 19:58
 *
 * 8- 监听器和回调
 * 内存泄漏第三个常见来源是监听器和其他回调，如果客户端在你实现的API中注册回调，却没有显示的取消，那么就会积聚。
 * 需要确保回调立即被当作垃圾回收的最佳方法是只保存它的弱引用，例如将他们保存成为WeakHashMap中的键。
 *
 * Android例子
 *
 * 解决办法
 * 1.使用线程时，一定要确保线程在周期性对象(如Activity)销毁时能正常结束，
 * 如能正常结束，但是Activity销毁后还需执行一段时间，也可能造成泄露，
 * 此时可采用weakReference方法来解决，另外在使用Handler的时候，如存在Delay操作，也可以采用WeakReference;
 * 2。使用Handler + HandlerThread时，记住在周期性对象销毁时调用looper.quit()方法:
 */
public class Demo08_ListenerCallback {

    // public class TestActivity extends Activity {
    //     private static final Object key = new Object();
    //
    //     @Override
    //     protected void onCreate(Bundle savedInstanceState) {
    //         super.onCreate(savedInstanceState);
    //         setContentView(R.layout.activity_main);
    //         new Thread(() -> {
    //             synchronized (key) {
    //                 try {
    //                     key.wait();
    //                 } catch (InterruptedException e) {
    //                     e.printStackTrace();
    //                 }
    //             }
    //         }).start();
    //     }
    // }
}
