package com.coding;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@SpringBootApplication()
@MapperScan(value = {"com.coding.user.dao","com.coding.attend.dao","com.coding.workflow.dao"})
@EnableScheduling
public class WorkSystemSpringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(WorkSystemSpringbootApplication.class, args);
	}

	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters(){
		//1. 需要定义一个converter转换消息的对象
		FastJsonHttpMessageConverter fasHttpMessageConverter = new FastJsonHttpMessageConverter();

		//2. 添加fastjson的配置信息，比如:是否需要格式化返回的json的数据
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

		//3. 在converter中添加配置信息
		fasHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fasHttpMessageConverter;
		return new HttpMessageConverters(converter);
	}

//	//rabbitMQ
//
//	@Bean
//	public Queue helloQueue() {
//		return new Queue("hello");
//	}
//
//	@Bean
//	public Queue userQueue() {
//		return new Queue("user");
//	}
//
//	//===============以下是验证topic Exchange的队列==========
//	@Bean
//	public Queue queueMessage() {
//		return new Queue("topic.message");
//	}
//
//	@Bean
//	public Queue queueMessages() {
//		return new Queue("topic.messages");
//	}
//	//===============以上是验证topic Exchange的队列==========
//
//
//	//===============以下是验证Fanout Exchange的队列==========
//	@Bean
//	public Queue AMessage() {
//		return new Queue("fanout.A");
//	}
//
//	@Bean
//	public Queue BMessage() {
//		return new Queue("fanout.B");
//	}
//
//	@Bean
//	public Queue CMessage() {
//		return new Queue("fanout.C");
//	}
//	//===============以上是验证Fanout Exchange的队列==========
//
//	@Bean
//	TopicExchange exchange() {
//		return new TopicExchange("exchange");
//	}
//	@Bean
//	FanoutExchange fanoutExchange() {
//		return new FanoutExchange("fanoutExchange");
//	}
//
//	/**
//	 * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
//	 * @param queueMessage
//	 * @param exchange
//	 * @return
//	 */
//	@Bean
//	Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
//		return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
//	}
//
//
//	/**
//	 * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
//	 * @param queueMessage
//	 * @param exchange
//	 * @return
//	 */
//	@Bean
//	Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
//		return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
//	}
//
//	@Bean
//	Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
//		return BindingBuilder.bind(AMessage).to(fanoutExchange);
//	}
//
//	@Bean
//	Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
//		return BindingBuilder.bind(BMessage).to(fanoutExchange);
//	}
//
//	@Bean
//	Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
//		return BindingBuilder.bind(CMessage).to(fanoutExchange);
//	}
}
