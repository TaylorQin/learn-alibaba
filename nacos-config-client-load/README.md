
# 版本 nacos-1.0.0-rc3

### Properties
- 优先读取applicationname profile
- 如果不存在，那么读取application配置

### Group
- 如果没有指定GROUP则读DEFAULT_GROUP中的
- 指定了Group则读取配置的相应Group中的配置；如果配置中心没有这个Group对应的配置文件，不会取DEFAULT_GROUP中的
- shared-dataids, ext-config
- profiles.active 参数对ext-config中的配置不会生效

### Namespace
- config client中配置的时候需要配置namespace id 而不是名称
- 不同Namespace之间的配置是隔离的



# nacos集群
- 修改nacos的启动脚本startup.sh, 添加-p参数，将其改为支持命令行传入端口方式
```
# 添加命令行参数p来接受端口号
export PORT="8488"
while getopts ":m:f:p:" opt
do
    case $opt in
        m)
            MODE=$OPTARG;;
        f)
            FUNCTION_MODE=$OPTARG;;
        p)
            PORT=$OPTARG;;
        ?)
        echo "Unknown parameter"
        exit 1;;
    esac
done

······

if [[ "${MODE}" == "standalone" ]]; then
    echo "nacos is starting"
    $JAVA ${JAVA_OPT} nacos.nacos
else
    if [ ! -f "${BASE_DIR}/logs/start.out" ]; then
        touch "${BASE_DIR}/logs/start.out"
    fi

    echo "$JAVA ${JAVA_OPT}" > ${BASE_DIR}/logs/start.out 2>&1 &
    # 启动参数传入端口号
    nohup $JAVA -Dserver.port=${PORT} ${JAVA_OPT} nacos.nacos >> ${BASE_DIR}/logs/start.out 2>&1 &
    echo "nacos is starting，you can check the ${BASE_DIR}/logs/start.out"
fi
```

- 启动多个nacos，例如端口分别指定为9501，9502，9503
- 使用nginx来做负载均衡
- nginx添加规则，内容如配置文件nacos.conf中内容,如下
```
upstream nacoscluster {
    server 127.0.0.1:9501;
    server 127.0.0.1:9502;
    server 127.0.0.1:9503;
}

server {
    listen 9500;
    server_name localhost;

    location / {
        proxy_pass http://nacoscluster;
    }
}
```