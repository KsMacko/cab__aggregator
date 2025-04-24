package com.internship.financeservice.config

import jakarta.jms.ConnectionFactory
import jakarta.jms.JMSException
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.jms.support.converter.MessageType

@Configuration
class ArtemisConfig {
    @Bean
    @Throws(JMSException::class)
    fun connectionFactory(): ConnectionFactory {
        val factory = ActiveMQConnectionFactory()
        factory.setBrokerURL("tcp://localhost:61616")
        factory.setUser("admin")
        factory.setPassword("admin")
        return factory
    }

    @Bean
    fun jmsTemplate(connectionFactory: ConnectionFactory?): JmsTemplate {
        val jmsTemplate = JmsTemplate(connectionFactory!!)
        jmsTemplate.messageConverter = jacksonMessageConverter()
        return jmsTemplate
    }

    @Bean
    fun jacksonMessageConverter(): MappingJackson2MessageConverter {
        val converter = MappingJackson2MessageConverter()
        converter.setTargetType(MessageType.TEXT)
        converter.setTypeIdPropertyName("_type")
        return converter
    }
}