
# 对于版本 nacos-1.0.0-rc3

## Properties
- 优先读取applicationname profile
- 如果不存在，那么读取application配置

## Group
- 如果没有指定GROUP则读DEFAULT_GROUP中的
- 指定了Group则读取配置的相应Group中的配置；如果配置中心没有这个Group对应的配置文件，不会取DEFAULT_GROUP中的
- shared-dataids, ext-config
- profiles.active 参数对ext-config中的配置不会生效

## Namespace
- config client中配置的时候需要配置namespace id 而不是名称
- 不同Namespace之间的配置是隔离的