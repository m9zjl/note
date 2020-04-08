# 标签库存在的问题

## 1.查询逻辑混乱，不符合单一职责原则 使用责任链模式，每个参数只由其相应的类处理参数

使用责任链模式，每种参数仅处理一类参数，如标签组合，复杂搜索，模型导入，用户参数，用户导入，将参数与查询解耦

## 2. 项目过于依赖单一的存储中间件ES，没有设计相应的接口层来实现业务逻辑的抽象

设计相应的接口支持es等中间件

## 3. 业务逻辑没有拆分，标签库项目中除标签库外还包含存量项目相关代码

拆分非查询的代码

## 4. 没有提供统一的对外查询接口

提供统一的对外查询接口

## 5. 原始代码中使用的es client版本为2.1， 迁移es服务器后项目中es client升级难度大，同时新老版本中match_phrase等查询生成的json语句格式不兼容，导致项目必须升级es client或者修改es client中的源码，不利于后期项目维护

提供一个缓存层方法，存放返回column的详情等

## 6. 查询过程中逻辑混乱，参数复杂，不方便调试和排查问题

与 1.相同， 参数只处理本类中所能处理的参数

# 查询流程伪代码

```java

// 参数处理类 处理导出或者
class PreParametersProcess(){
   // 转换前端输入的字符串
   ProcessQueryParameters();
}
// 查询的控制器，包含查询的线程池 主流程
class QueryHandlerController(){
   // 查询的线程池
   QueryComsumerPerThreadPool comsumePool = new QueryComsumerPerThreadPool();
   QueryResult comsumeQuery(){
      //获取查询线程并上锁
      final QueryComsumerPerThread qcpt = consumePoll.obtainAndLock();
      //查询主逻辑，待优化
      try {
         //执行查询
         //可以简化直接在processQuery中返回结果
         qcpt.processQuery(params);
         return qcpt.result();
      } finally {
         //释放锁
         comsumePool.relese(qcpt);
      }
   }
}
// 查询线程
class QueryComsumerPerThread{
   // 策略模式
   Map<String, QueryNode> queryNodes = new HashMap<String, QueryNode>();
   //具体的消费类
   Comsumer comsumer;
   processQuery(Map<String, Object> params,LoginUser loginUser){
      //利用Ordered接口进行排序
      for( Map.Entry<String,Object> entry : params){
         String key = entry.getKey();
         QueryNode node=null;
         if((node = queryNodes.get(key))!=nul){
            comsumer.queryChain.add(node);
         }
      }
      // 排序？
      Arrays.sort(qeuryChain.queryNodes);
      comsumer.process();
   }
}

// 查询用的comsumer 实现ES查询和Hbase查询等逻辑
abstract Comsumer{
   QueryChain queryChain = new QueryChain();
   process(){}
}


class ESConsumer extends Comsuer{
   process(){
      List<QueryNode> queryNodes = queryChain.queryNodes;
      for(QueryNode node : queryNodes){
         // 处理参数
         node.preHandler(params);
      }
      QueryResult queryResult = query();
      for(QueryNode node : queryNodes){
         // 处理返回值
         node.postHandler(queryResult, params);
      }
   }
};
class HBaseComsumer extends Comsumer{};

//查询责任链
class QueryChain{
   List<QueryNode> queryNodes = new ArrayList<>();
   public boolean addLast(QueryNode node){
      checkValid(node);
      return queryNodes.add(node);
   }
   public boolean addFirst(QueyrNode node){
      checkValid(node);
      return queryNode.add(node,0);
   }
   public boolean add(QueryNode node){
      addLast(node);
   }
}

// queryNode
interface QueryNode{
   // 前置处理器
   default preHandler(){
      generateQueryParameters();
   }
   // 生成查询条件
   generateQueryParameters();
   postHandle();
   afterCompletion();
}

// query
//标签组合查询节点
class TagGroupQueryNode extends QueryNodes, Ordered{
   preHandle(){
      //处理查询逻辑，生产查询用的参数，如导入的要执行查询然后生产新的查询参数
      geenrateQueryParameters();
   }
   postHandle(){
      //处理查询后的逻辑，如查询多月后再查询单月数据用来补充
   }
   afterCompletion(){
      //查询完成后的事，暂时还没想好干什么，先留着
   }
   @OverRide
   public int getOrder(){
      return 10;
   }
}
```
