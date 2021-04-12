## 什么Docker
Docker是一个容器化平台，它以容器的形式将您的应用程序及其所有依赖项打包在一起，以确保您的应用程序在任何环境中无缝运行。

## Docker与虚拟机有何不同
Docker不是虚拟化方法。它依赖于实际实现基于容器的虚拟化或操作系统级虚拟化的其他工具。为此，Docker最初使用LXC驱动程序，然后移动到libcontainer现在重命名为runc。Docker主要专注于在应用程序容器内自动部署应用程序。应用程序容器旨在打包和运行单个服务，而系统容器则设计为运行多个进程，如虚拟机。因此，Docker被视为容器化系统上的容器管理或应用程序部署工具。
* A 容器不需要引导操作系统内核，因此可以在不到一秒的时间内创建容器。此功能使基于容器的虚拟化比其他虚拟化方法更加独特和可取。
* B 由于基于容器的虚拟化为主机增加了很少或没有开销，因此基于容器的虚拟化具有接近本机的性能。
* C 对于基于容器的虚拟化，与其他虚拟化不同，不需要其他软件。
* D 主机上的所有容器共享主机的调度程序，从而节省了额外资源的需求。
* E 与虚拟机映像相比，容器状态（Docker或LXC映像）的大小很小，因此容器映像很容易分发。
* F 容器中的资源管理是通过cgroup实现的。Cgroups不允许容器消耗比分配给它们更多的资源。虽然主机的所有资源都在虚拟机中可见，但无法使用。这可以通过在容器和主机上同时运行top或htop来实现。所有环境的输出看起来都很相似。

## 什么是Docker镜像
Docker镜像是Docker容器的源代码，Docker镜像用于创建容器。使用build命令创建镜像。

## 什么是Docker容器
Docker容器包括应用程序及其所有依赖项，作为操作系统的独立进程运行。

## Docker容器有几种状态
四种状态：运行、已暂停、重新启动、已退出。

## Dockerfile中最常见的指令是什么
* FROM：指定基础镜像
* LABEL：功能是为镜像指定标签
* RUN：运行指定的命令
* CMD：容器启动时要运行的命令

## Dockerfile中的命令COPY和ADD命令有什么区别
COPY与ADD的区别COPY的SRC只能是本地文件，其他用法一致

那是因为它比ADD更易懂。COPY仅支持将本地文件复制到容器中，而ADD具有一些功能（如仅限本地的tar提取和远程URL支持），这些功能并不是很明显。因此，ADD的最佳用途是将本地tar文件自动提取到镜像中，如ADD rootfs.tar.xz /。
## docker常用命令
    docker pull 拉取或者更新指定镜像
    docker push 将镜像推送至远程仓库
    docker rm 删除容器
    docker rmi 删除镜像
    docker images 列出所有镜像
    docker ps 列出所有容器

## 容器与主机之间的数据拷贝命令
docker cp 命令用于容器与主机之间的数据拷贝

    主机到容器：
    docker cp /www 96f7f14e99ab:/www/
    容器到主机：
    docker cp 96f7f14e99ab:/www /tmp/

## 启动nginx容器（随机端口映射），并挂载本地文件目录到容器html的命令
docker run -d -P --name nginx2 -v /home/nginx:/usr/share/nginx/html nginx

## 解释一下dockerfile的ONBUILD指令
当镜像用作另一个镜像构建的基础时，ONBUILD指令向镜像添加将在稍后执行的触发指令。如果要构建将用作构建其他镜像的基础的镜像（例如，可以使用特定于用户的配置自定义的应用程序构建环境或守护程序），这将非常有用。

## 什么是Docker Swarm
Docker Swarm是Docker的本机群集。它将Docker主机池转变为单个虚拟Docker主机。Docker Swarm提供标准的Docker API，任何已经与Docker守护进程通信的工具都可以使用Swarm透明地扩展到多个主机。

## 如何在生产中监控Docker
* Docker提供docker stats和docker事件等工具来监控生产中的Docker。我们可以使用这些命令获取重要统计数据的报告。
* Docker统计数据：当我们使用容器ID调用docker stats时，我们获得容器的CPU，内存使用情况等。它类似于Linux中的top命令。
* Docker事件：Docker事件是一个命令，用于查看Docker守护程序中正在进行的活动流。

一些常见的Docker事件是：attach，commit，die，detach，rename，destroy等。我们还可以使用各种选项来限制或过滤我们感兴趣的事件

## Docker如何在非Linux系统中运行容器
通过添加到Linux内核版本2.6.24的名称空间功能，可以实现容器的概念。容器将其ID添加到每个进程，并向每个系统调用添加新的访问控制检查。它由clone（）系统调用访问，
该调用允许创建先前全局命名空间的单独实例。

如果由于Linux内核中可用的功能而可以使用容器，那么显而易见的问题是非Linux系统如何运行容器。Docker for Mac和Windows都使用Linux VM来运行容器。
Docker Toolbox用于在Virtual Box VM中运行容器。但是，最新的Docker在Windows中使用Hyper-V，在Mac中使用Hypervisor.framework。

## 什么是Docker Hub？
 Docker hub是一个基于云的注册表服务，允许您链接到代码存储库，构建映像并测试它们，存储手动推送的镜像以及指向Docker云的链接，以便您可以将镜像部署到主机。
 它为整个开发流程中的容器发现，分发和变更管理，用户和团队协作以及工作流自动化提供了集中资源。
 
## 解释基本的Docker使用工作流程
  * 一切都从Dockerfile开始。Dockerfile是镜像的源代码。
  
  * 创建Dockerfile后，您可以构建它以创建容器的镜像。图像只是“源代码”的“编译版本”，即Dockerfile。
  
  * 获得容器的镜像后，应使用注册表重新分发容器。注册表就像一个git存储库 - 你可以推送和拉取镜像。
  
  * 接下来，您可以使用该图像来运行容器。在许多方面，正在运行的容器与虚拟机（但没有虚拟机管理程序）非常相似。
  
  
     +------------+  docker build   +--------------+  docker run -dt   +-----------+  docker exec -it   +------+
        | Dockerfile | --------------> |    Image     | --------------->  | Container | -----------------> | Bash |
        +------------+                 +--------------+                   +-----------+                    +------+
                                         ^
                                         | docker pull
                                         |
                                       +--------------+
                                       |   Registry   |
                                       +--------------+

## 什么是管理程序？
 管理程序处理创建用户虚拟机运行的虚拟环境。它监督用户系统，并确保在必要时为客户分配资源。虚拟机管理程序位于物理机和虚拟机之间，并为虚拟机提供虚拟化服务。为了实现它，它拦截虚拟机上的客户操作系统操作，并模拟主机操作系统上的操作。
 
 虚拟化技术的快速发展（主要是在云端），通过允许在一个物理服务器上创建多个虚拟服务器，借助于管理程序（如Xen、VMware Player、KVM等），以及在商品处理器（如Intel VT AN）中集成硬件支持，进一步推动了虚拟化的使用。例如Intel VT和AMD-V。 
 
 
 https://www.cnblogs.com/xiaoyangjia/p/11388806.html
 
 https://blog.csdn.net/lixiao0320/article/details/94348042