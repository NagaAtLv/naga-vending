package com.naga.kcomtest.service.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * The class FileProcessorUtils properties file implementation of FileProcessor
 * 
 * @author Naga
 *
 */
public class FileProcessorUtils implements FileProcessor {

	@Override
	public Properties readFile(final String fileName) throws IOException {

		if (null == fileName || "".equalsIgnoreCase(fileName)) {

			System.err.println("readFile-Error whilst reading File properties and fileName is  "
							+ fileName);
			return null;
		} else {
			final Path configPath = Paths.get(fileName);
			try (InputStream inStream = Files.newInputStream(configPath)) {
				final Properties properties = new Properties();
				properties.load(inStream);
				if (null != inStream)
					inStream.close();
				return properties;
			}
		}
	}

	@Override
	public void writeFile(final String fileName, final Properties properties) {
		OutputStream outStream = null;
		final File file;
		try {
			file = new File(fileName);
			outStream = new FileOutputStream(file);
			// no comments needed while storing
			properties.store(outStream, null);
		} catch (final IOException e) {
			System.err.println("writeFile - Error while saving File properties into File: "
							+ fileName);
			e.printStackTrace();
		} finally {
			if (null != outStream) {
				try {
					outStream.close();

				} catch (IOException e) {
					System.err.println("writeFile - Error while closing outStream Stream and outStream "
									+ outStream);
					e.printStackTrace();
				}
			}

		}

	}

}
