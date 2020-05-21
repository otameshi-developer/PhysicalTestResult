package study.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * ファイルを読み込み、文字列を生成する。
 *
 */
public class FileReader { // TODO: otsuka: あんまりこういうクラス名ってみないよね。pure javaのjavadocとか参考にしてほしいんだけど、この場合はFileReaderじゃない？（読む人はreadじゃなくてreaderじゃない？） kaneko: 確かにFileReaderです。FileReadから変更しました。

	/**
	 * ファイルを読み込み、文字列を生成する。
	 *
	 * @param filePath ファイルのパス
	 * @return ファイルから生成した文字列 // TODO: otsuka: なにか変換したの？　kaneko: 変換はしていないです。「ファイルから生成した文字列」に変更します。
	 * @throws IOException 読み込みに失敗
	 */
	public String read(String filePath) throws IOException {

		StringBuilder fileData = new StringBuilder();
		// TODO: otsuka: main()で実行したときって、リソースこうやって取らないといけないんだっけ？なんか普通にFileインスタンス生成すればよいような気もするんだけど。。。（MEAPはこの取り方が正解！） kaneko: Fileインスタンス生成で出来たので修正しました。
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), StandardCharsets.UTF_8))) {
			String line = br.readLine();
			while (line != null) {
				fileData.append(line);
				line = br.readLine();
			}
		}// TODO: otsuka: これってなんでキャッチしているの？意味ある？ kaneko: 上に投げるだけだったので意味なかったです。catch句は消しました。
		return fileData.toString();

	}

}
