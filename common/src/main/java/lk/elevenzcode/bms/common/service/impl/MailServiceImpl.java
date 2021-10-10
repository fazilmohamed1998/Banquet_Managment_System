package lk.elevenzcode.bms.common.service.impl;

import lk.elevenzcode.bms.common.service.MailService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.CharEncoding;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {
  private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);
  @Autowired
  JavaMailSender mailSender;
  @Inject
  private ApplicationContext context;
  @Autowired
  private VelocityEngine velocityEngine;
  @Value("${mail.default.from.address}")
  private String fromAdr;
  @Value("${mail.default.from.name}")
  private String fromName;
  @Value("${mail.default.reply.to.address}")
  private String replyTo;

  @Override
  public void send(final String recipientEmail, final String subject, final String templatePath,
                   final Map model) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("send ---> recipientEmail - {}, subject - {}, templatePath - {}, model - {}",
        recipientEmail, subject, templatePath, model);
    }
    final MimeMessagePreparator preparator = mimeMessage -> {
      MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
      message.setFrom(fromAdr, fromName);
      message.setReplyTo(replyTo);
      for (String email : recipientEmail.split("\\;")) {
        message.addTo(email);
      }
      message.setSubject(subject);
      message.setSentDate(new Date());

      String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templatePath,
        "UTF-8", model);

      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("text-->{}", text);
      }

      message.setText(text, true);
    };

    Thread thread = new Thread(() -> mailSender.send(preparator));
    thread.start();
  }

  @Override
  public void send(final String recipientEmail, final String subject, final String templatePath,
                   final Map model, final Map<String, File> attachments) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("send ---> recipientEmail - {}, subject - {}, templatePath - {}, model - {}, " +
        "attachments - {}", recipientEmail, subject, templatePath, model, attachments);
    }
    final MimeMessagePreparator preparator = new MimeMessagePreparator() {
      @SuppressWarnings({"rawtypes", "unchecked"})
      public void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, CharEncoding.UTF_8);

        message.setFrom(fromAdr, fromName);
        message.setReplyTo(replyTo);
        for (String email : recipientEmail.split("\\;")) {
          message.addTo(email);
        }
        message.setSubject(subject);
        message.setSentDate(new Date());
        for (Map.Entry<String, File> entry : attachments.entrySet()) {
          message.addAttachment(entry.getKey(), entry.getValue());
        }
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templatePath,
          "UTF-8", model);

        if (LOGGER.isTraceEnabled()) {
          LOGGER.trace("text-->{}", text);
        }

        message.setText(text, true);

      }
    };

    Thread thread = new Thread(() -> mailSender.send(preparator));
    thread.start();
  }

  @Override
  public void sendIS(String recipientEmail, String subject, String templatePath, Map model, Map<String,
    InputStreamSource> attachments, Map<String, Resource> resources) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("send ---> recipientEmail - {}, subject - {}, templatePath - {}, model - {}, " +
        "attachments - {}", recipientEmail, subject, templatePath, model, attachments);
    }
    final MimeMessagePreparator preparator = new MimeMessagePreparator() {
      @SuppressWarnings({"rawtypes", "unchecked"})
      public void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, CharEncoding.UTF_8);

        message.setFrom(fromAdr, fromName);
        message.setReplyTo(replyTo);
        for (String email : recipientEmail.split("\\;")) {
          message.addTo(email);
        }
        message.setSubject(subject);
        message.setSentDate(new Date());
        for (Map.Entry<String, InputStreamSource> entry : attachments.entrySet()) {
          message.addAttachment(entry.getKey(), entry.getValue());
        }
        String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templatePath,
          "UTF-8", model);
        if (LOGGER.isTraceEnabled()) {
          LOGGER.trace("content-->{}", content);
        }
        message.setText(content, true);

        //add resources
        if (MapUtils.isNotEmpty(resources)) {
          for (String id : resources.keySet()) {
            message.addInline(id, resources.get(id));
          }
        }
      }
    };
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          mailSender.send(preparator);
        } catch (MailException e) {
          LOGGER.error(e.getMessage(), e);
        }
      }
    }).start();
  }
}
