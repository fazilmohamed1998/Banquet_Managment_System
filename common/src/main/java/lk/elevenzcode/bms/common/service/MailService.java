package lk.elevenzcode.bms.common.service;

import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.Map;

public interface MailService {
  void send(final String recipientEmail, final String subject, final String templatePath,
            final Map model);

  void send(String recipientEmail, String subject, String templatePath, Map model,
            Map<String, File> attachments);

  void sendIS(String recipientEmail, String subject, String templatePath, Map model,
              Map<String, InputStreamSource> attachments, Map<String, Resource> resources);
}
