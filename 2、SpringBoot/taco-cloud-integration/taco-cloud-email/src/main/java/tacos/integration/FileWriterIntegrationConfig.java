package tacos.integration;

import java.io.File;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.transformer.GenericTransformer;

@Configuration
public class FileWriterIntegrationConfig {

	@Bean
	@Transformer(inputChannel = "textInChannel",
				 outputChannel = "fileWriterChannel")
	public GenericTransformer<String, String> upperCaseTransformer() {
		return text -> text.toUpperCase();
	}
	
	@Bean
	@ServiceActivator(inputChannel = "fileWriterChannel")
	public FileWritingMessageHandler fileWriter() {
		FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("/tmp/sia5/files"));
		handler.setExpectReply(false);
		handler.setFileExistsMode(FileExistsMode.APPEND);
		handler.setAppendNewLine(true);
		return handler;
	}
	
	// 使用DSL
	@Bean
	public IntegrationFlow fileWriterFlow() {
		return IntegrationFlows.from(MessageChannels.direct("textInChannel"))               // 入站通道
							   .<String, String>transform(t -> t.toUpperCase())				// 声明转换器
							   .channel(MessageChannels.direct("fileWriterChannel"))		// 出站通道
							   .handle(Files.outboundAdapter(new File("/tmp/sia5/files"))	// 处理文件写入
									   	    .fileExistsMode(FileExistsMode.APPEND)
									   	    .appendNewLine(true))
							   .get();
	}
	
}
