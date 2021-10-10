package lk.elevenzcode.bms.common.util;

import lk.elevenzcode.bms.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileUtil {
  @Value("${app.data.upload.dir}")
  private String appDataDir;

  public void write(String entityName, Integer entityId, CommonsMultipartFile file) throws ServiceException {
    try {
      final File dest = new File(appDataDir + File.separator + entityName + File.separator
              + entityId + File.separator + file.getOriginalFilename());
      if (!dest.exists()) {
        dest.getParentFile().mkdirs();
        file.transferTo(dest);
      }
    } catch (IOException e) {
      throw new ServiceException("Error while saving file", e);
    }
  }

  public File read(String entityName, Integer entityId, String fileName) throws ServiceException {
    try {
      final File file = new File(appDataDir + File.separator + entityName + File.separator
              + entityId + File.separator + fileName);
      if (!file.exists()) {
        throw new ServiceException(String.format("File(%s) doesn't exist",
                file.getCanonicalPath()));
      }
      return file;
    } catch (IOException e) {
      throw new ServiceException(String.format("File(%s) doesn't exist", fileName));
    }
  }
}
