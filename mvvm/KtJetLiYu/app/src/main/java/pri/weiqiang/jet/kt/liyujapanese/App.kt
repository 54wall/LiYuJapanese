package pri.weiqiang.jet.kt.liyujapanese

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    //单例化的第一种方式：声明一个简单的Application属性
    companion object {
        //情况一：声明可空的属性
        private var instance: App? = null
        fun instance() = instance!!
        //情况二：声明延迟初始化属性
//        private lateinit var instance:App
//        fun instance() = instance

    }
}