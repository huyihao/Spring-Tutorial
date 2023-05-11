package tacos.integration;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "textInChannel")	// 声明消息网关
public interface FileWriterGateway {

	// 写入文件
	void writeToFile(@Header(FileHeaders.FILENAME) String filename, String data);
	
}
