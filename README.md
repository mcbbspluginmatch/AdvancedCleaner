# AdvancedCleaner

## 介绍
AdvancedCleaner 可以帮助你l高效解决服务器掉落物和实体多的难题    
你只需安装插件至服务器，使用默认配置文件，即可对服务器进行一定的优化  
## 原理介绍
本插件的掉落物清理和实体清理，均使用异步任务进行计时
  
掉落物清理会在清理时异步判断掉落物是否满足配置文件中的清理规则  
在判断后再进行清理  
本插件较为新颖的地方为，会根据附魔和nbt判断是否需要清理  
并且在临近清理时，玩家丢弃东西会进行一定的提示和阻止  

实体清理目前是定时检测每个区块中每个类型的实体的数量，并根据配置文件进行判断是否需要清理  
与一般的插件不同的是 本插件会将实体清理至上限数量，而不是全部清理  
## 配置文件
配置文件有详细的注释 一般服务器使用默认配置即可  
默认配置文件如下  
````yaml
item:
  enable: true
  blackList:
    - 'BEACON'
    - '@SHULKER_BOX'
    - 'DIAMOND'
    - 'DIAMOND_ORE'
    - 'GOLD_INGOT'
    - 'GOLD_ORE'
    - 'IRON_INGOT'
    - 'IRON_ORE'
    - '@SPAWN_EGG'
    - '@EMERALD'
    - 'PLAYER_HEAD'
    - '@SKULL'
    - 'NAUTILUS_SHELL'
    - 'HEART_OF_THE_SEA'
    - 'TRIDENT'
    - '@MUSIC_DISC'
    - 'ELYTRA'
    - 'TOTEM_OF_UNDYING'
    - 'CONDUIT'
  #不清理的物品列表 开头为@的意思为只要枚举名包括后面的文本，则不清理
  #例如默认配置文件中的@SHULKER_BOX可以代表所有颜色的潜影箱
  checkValue:
    enable: true
    #是否检查物品价值
    #规则：
    #如果物品被重命名，有Lore，有额外NBT信息，附魔数量超过3个 则不清理
  interval: 600
  #清理间隔 单位为秒
  broadcast:
    cleanDone: '§7地面掉落物清理完成，清理了 §e{0} §7个掉落物。'
    countDown: '§7将在 §e{0} §7秒内开始清理地面垃圾'
    #消息自定义
    notifications:
      - 60
      - 30
      - 10
    #距离清理多少秒的时候发送提示
  dropResist:
    enable: true
    #是否阻止玩家清理垃圾前丢东西
    consult: '§7服务器即将进行&e掉落物清理&7，你确定要扔东西吗？如果你确认，请§e再次丢弃§7，并且下次丢弃时不再显示该提示'
    #询问时的消息
    confirmed: '§7已经§e解除丢弃限制§7，如有物品丢失，请自行承担责任'
    #确认丢弃的消息
    value: 10
    #小于多少秒开始限制
entity:
  enable: true
  #是否开启实体清理
  interval: 300
  #检测间隔
  limit:
    ZOMBIE: 8
    SKELETON: 8
    VILLAGER: 10
    PIG_ZOMBIE: 6
  #每个区块中 各类型实体的最大数量，超过数量会清理超出限制的部分
  #实体类型为枚举名
````
## 下载
下载和版本历史请前往 [http://dl.zhanshi123.me/#AdvancedCleaner](http://dl.zhanshi123.me/#AdvancedCleaner) 进行下载  
只支持1.13以上的版本