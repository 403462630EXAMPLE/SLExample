#### 1. Custom Tabs
优点：

1. 自定义 UI: 包括 Toolbar 颜色，Action 按钮，自定义 menu 选项，自定义进/出动画

2. 预先加载网页内容，从而就快加载速度

3. 可以共享 Chrome 浏览器的 Cookie、数据压缩等许多功能

缺点：

1. Android SDK VERSION >= 4.1

2. 必须装Chrome浏览器，并且设置成默认浏览器，并且Chrome 版本45+

3. 无法进行native交互(比如JSBridge)

建议加载第三方的url用Custom Tabs， 自己的url还是用WebView

#### 2. BottomSheet

1. BottomSheetBehavior

2. BottomSheetDialog

3. BottomSheetDialogFragment

缺点:

1. BottomSheetDialog划下隐藏后, 调用show方法，页面只是变暗，BottomsheetDialog未显示

2. BottomSheetDialog和BottomSheetDialogFragment无法修改peekHeight高度

在FcBottomSheetDialog 修复以上缺点

#### 3. FragmentTransaction commitNow方法

相当于commit + executePendingTransactions, 但是不会放入Back stack中

#### 4. VectorDrawable 矢量图

优点：

1. 文件小， 文件大小与物体的大小无关

2. 矢量图形是与分辨率无关， 任意放大矢量图形不会失针

请查看[Vector Asset Studio的使用](http://www.jianshu.com/p/d6c39f2dd5e7)

#### Firebase JobDispatcher

Android系统提供的JobDispatcher要求API>=21, 如果要支持低版本就必须导入[Firebase JobDispatcher](https://github.com/firebase/firebase-jobdispatcher-android)

1、避免频繁的唤醒硬件模块，造成不必要的电量消耗。

2、避免在不合适的时间(例如低电量情况下、弱网络或者移动网络情况下的)执行过多的任务消耗电量；

JobScheduler特性：

1、支持在一个任务上组合多个条件；

2、内置条件：设备待机、设备充电和连接网络；

3、支持持续的job，这意味着设备重启后，之前被中断的job可以继续执行；

4、支持设置job的最后执行期限

5、根据你的配置，可以设置job在后台运行还是在主线程中运行


可以推迟的非面向用户的任务(如定期数据库数据更新)
当充电时才希望执行的工作(如备份数据)
需要访问网络或 Wi-Fi 连接的任务(如向服务器拉取内置数据)
希望作为一个批次定期运行的许多任务

https://www.zhihu.com/question/24360587

#### 5. battery historian

请查看[Android 性能测试工具的使用](https://www.kancloud.cn/digest/itfootballprefermanc/100905)


#### 6. webp
1. API>=14 开始支持，但是支持不是很好，API>=17 则基本完美支持，但是对透明的webp不支持，API>=18 则开始支持透明的webp
2. Android Studio 2.2 开始支持webp
3. Android 暂时不支持动态webp
3. 选择无损压缩时，“-lossless -q 100” 是最佳方案
4. 选择有损压缩时，“-q 75”是最佳方案（图片质量与体积大小达到均衡）



