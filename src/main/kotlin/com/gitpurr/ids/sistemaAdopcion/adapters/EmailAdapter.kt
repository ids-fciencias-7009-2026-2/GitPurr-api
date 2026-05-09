package com.gitpurr.ids.sistemaAdopcion.adapters

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailAdapter(
    private val mailSender: JavaMailSender
) {
    @Value("\${spring.mail.from}")
    lateinit var fromEmail: String

    private val logger = LoggerFactory.getLogger(EmailAdapter::class.java)

    fun sendHtmlEmail(to: String, subject: String, htmlBody: String): Result<Any> {
        val mimeMessage = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(mimeMessage, true, "UTF-8")
        return try {
            helper.setFrom(fromEmail)
            helper.setTo(to)
            helper.setSubject(subject)
            helper.setText(htmlBody, true)
            logger.info("Enviando correo HTML a $to...")
            mailSender.send(mimeMessage)
            Result.success(true)
        } catch (e: Exception) {
            logger.error("Fallo al enviar correo HTML", e)
            Result.failure(e)
        }
    }
}