## 官网资料
https://work.weixin.qq.com/api/doc/90000/90135/91360

## window环境
  将 **libs/win/** 下的文件复制到 **C:\Windows\System32** 目录下，即可加载调用 会话存档的动态库。
  
  
## linux 环境
  
  **关于 libs/下的文件说明：**
  
  1、若不在代码上加载动态库，可以将动态库复制到 Linux的 **/usr/lib** 目录，目前没有系统的操作权限，这个方案不行
  
  2、代码上加载，如 Finance 类所示
