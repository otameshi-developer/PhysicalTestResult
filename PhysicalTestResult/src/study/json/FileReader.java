package study.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * ファイルを読み込み、文字列を生成する。
 *
 */
public class FileReader {

	/**
	 * ファイルを読み込み、文字列を返却する。
	 *
	 * @param filePath ファイルのパス
	 * @return ファイルから読み込んだ文字列 
	 * @throws IOException 読み込みに失敗
	 */
	public String read(String filePath) throws IOException {

		StringBuilder fileData = new StringBuilder();

		Path path = Paths.get(filePath);
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			Stream<String> stream = reader.lines();
			stream.forEach(line -> {
				fileData.append(line);
			});
		}
		return fileData.toString();

	}

}
