package study.html;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * HTMLファイルを生成する。
 *
 */
public class HtmlPrinter {

	/**
	 * HTMLファイルを生成する。
	 *
	 * @param printString ファイルに書き込む文字列 TODO: otsuka: 引数名とコメントあってないよね。 kaneko: 引数名をhtmlから変更しました。
	 * @throws IOException 出力に失敗
	 */
	public void printHtml(String printString) throws IOException {

		try (PrintWriter pw = new PrintWriter("res/physicalTestResult.html")) {
			pw.print(printString);
			pw.flush();// TODO: otsuka: Exception発生時もちゃんとcloseされる？ kaneko: try-with-resources文を使ってリソースを自動でcloseするようにしました。
		}// TODO: otsuka: このキャッチいる？ kaneko: 上に投げるだけだったので意味なかったです。catch句は消しました。

	}

}