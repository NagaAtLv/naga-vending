package com.naga.kcomtest.service.util;

import java.io.IOException;
import java.util.Properties;

/**
 * The FileProcessor interface
 * @author Naga
 *
 */
public interface FileProcessor {

	/**
	 * Read existing file props.
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public Properties readFile(final String fileName) throws IOException;

	/**
	 * Write/override properties
	 * @param fileName
	 * @param properties
	 */
	public void writeFile(final String fileName, Properties properties);

}
