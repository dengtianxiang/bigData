package com.xyg.streaming

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * Author: Mr.Deng
  * Date: 2018/10/19
  * Desc:
  */
object SocketWindowWordCountScala {
  def main(args: Array[String]) : Unit = {
    // 定义一个数据类型保存单词出现的次数
    case class WordWithCount(word: String, count: Long)

    // port 表示需要连接的端口
    val port: Int = try {
      ParameterTool.fromArgs(args).getInt("port")
    } catch {
      case e: Exception => {
        System.err.println("No port specified. Please run 'SocketWindowWordCount --port <port>'")
        return
      }
    }

    // 获取运行环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    // 连接此socket获取输入数据
    val text = env.socketTextStream("node21", port, '\n')

    //需要加上这一行隐式转换 否则在调用flatmap方法的时候会报错
    import org.apache.flink.api.scala._

    // 解析数据, 分组, 窗口化, 并且聚合求SUM
    val windowCounts = text
      .flatMap { w => w.split("\\s") }
      .map { w => WordWithCount(w, 1) }
      .keyBy("word")
      .timeWindow(Time.seconds(5), Time.seconds(1))
      .sum("count")

    // 打印输出并设置使用一个并行度
    windowCounts.print().setParallelism(1)

    env.execute("Socket Window WordCount")
  }

}


