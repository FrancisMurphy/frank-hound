# Frank-Hound
分布式请求链追踪框架
## 简述
Hound(小猎犬)高兼容、无侵入理念设计的分布式业务链追踪插件，相比于市面上主流的中心化追踪系统（如Pinpoint、Skywalking），特点是轻量、去中心化、接入简单、可拓展性强并且保有了不影响到原有业务代码的特点，功能上支持Spring MVC、Spring Boot等主流容器框架。
![](https://i.loli.net/2020/03/07/G7b6ukZBptXosnP.png)
系统功能上整体分为追踪信息收集、追踪信息分析，除追踪功能外附加的会提供平滑的TraceContext功能，即任何模块任何节点都可以获取同样的上下文，这将给业务实现带来便利，有效提高开发效率；
## 架构设计
### 整体设计
Hound的追踪核心在于如何提供一个强大的追踪上下文系统结合现有的UI分析工具，来提供强大的追踪功能，整体追踪阶段分为三个部分：  
![image.png](https://i.loli.net/2020/03/07/mNDGs9RW2oU4YSf.png)
- Accepting Tracking  
响应追踪
- Process Tracking  
过程追踪
- Request Tracking  
请求追踪
 
由于分布式追踪系统所面对的复杂追踪场景，这必然导致了可拓展性是贯穿整体设计的关键点。在Hound的架构设计中，模块化是首要需要实现的，包含：
  1. 追踪上下文传递规则模块
  2. 传输协议wrap模块
  3. 请求工具拦截器模块
  4. web容器过滤器模块
  5. 过程追踪监视器模块
  6. 追踪信息表现模块
每个模块都可以通过配置进行动态加载，以此达成高拓展;
### 追踪模型
  为了较完整描述业务调用链的信息，追踪上下文模型使用Google Dapper Trace Tree（论文请参考https://storage.googleapis.com/pub-tools-public-publication-data/pdf/36356.pdf）
- TraceContext：追踪上下文，追踪信息与自定义上下文参数的持有者；
- Frontend:前端，即业务请求发起的方
- Backend:后端，可以理解为微服务
- Trace:踪迹，对一次业务请求的整体业务链追踪周期
- Span:跨度，一次业务请求在单个系统的追踪周期
- Parent:父Span，用于描述调用依赖  
![image.png](https://i.loli.net/2020/03/07/SAoBaG61k4VEwIp.png)
### 实现流程
#### 响应追踪
- Unpacker 拆箱员，负责将请求反序列化为TraceContext,供后续使用;
- Sorter 分拣员，负责将TraceContext分发给订阅的模块使用，比如日志展示等;
![image.png](https://i.loli.net/2020/03/07/HhyUcM4RNv6ouSF.png)
#### 过程追踪
- Monitor 监视者，监视并提取关注监控的信息至TraceContext;
![image.png](https://i.loli.net/2020/03/07/6edZYOcB5xK4V3y.png)
#### 请求追踪
- Packer 装箱员，负责将TraceContext包装进行对外请求中;
![image.png](https://i.loli.net/2020/03/07/dAO9fYuFT4UERBy.png)
