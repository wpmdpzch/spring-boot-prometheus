运行：
1. 查看metrics运行情况：127.0.0.1:9090/metrics
2. 在prom-config目录下运行docker-compose up -d启动prometheus he grafana两个容器
   执行127.0.0.1:9090即可进入监控界面
   
3. 如果想修改监控server，修该prometheus.yml即可
   可参照文件中的job_name,修该完文件，记得重启docker

troubleshooting：
docker安装运行异常，处理方案：
ubuntu16.04 安装完docker后在docker-compose.yml文件所在目录执行：
$ docker-compose up -d
报错信息：
ERROR: Couldn’t connect to Docker daemon at http+docker://localunixsocket - is it running?
If it’s at a non-standard location, specify the URL with the DOCKER_HOST environment variable.

正确的是将当前用户加入docker组
sudo gpasswd -a ${USER} docker
然后退出当前用户比如切换为root，再次切换为之前用户。然后执行docker-compose up -d就ok了。