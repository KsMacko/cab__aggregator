package com.internship.financeservice.config

import jakarta.jms.ConnectionFactory
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.jms.support.converter.MessageType

@Configuration
class ArtemisConfig {
    @Value("\${spring.artemis.broker-url}")
    private lateinit var brokerUrl: String

    @Value("\${spring.artemis.user}")
    private lateinit var artemisUser: String

    @Value("\${spring.artemis.password}")
    private lateinit var artemisPassword: String

    @Bean
    fun connectionFactory(): ConnectionFactory {
        return ActiveMQConnectionFactory().apply {
            setBrokerURL(brokerUrl)
            user = artemisUser
            password = artemisPassword
        }
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