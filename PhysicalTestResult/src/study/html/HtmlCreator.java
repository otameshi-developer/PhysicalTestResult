package study.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import study.json.PhysicalTestResult;

/**
 * 体力テスト結果のHTMLを生成する。
 *
 */
public class HtmlCreator {

	private static final String CRLF = "\r\n";

	private static final String REPLACE_MARK = "$$";

	/**
	 * 体力テスト結果のHTMLを生成する。
	 *
	 * @param results 体力テスト結果のリスト
	 * @return 体力テスト結果のHTML
	 */
	public String createHtml (List<PhysicalTestResult> results) throws IOException {

		StringBuilder html = new StringBuilder();
		String htmlPhysicalTestResult = createResultData(results);
		Map<String, String> mapReplacement = new HashMap<>();
		mapReplacement.put("RESULT", htmlPhysicalTestResult);

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("res/base.html")), StandardCharsets.UTF_8))) {
			String line = br.readLine();
			while (line != null) {
				html.append(replaceLine(line, mapReplacement)).append(CRLF);
				line = br.readLine();
			}
		}
		return html.toString();

	}

	/**
	 * 体力テストの結果をHTMLの形式に形成する。
	 * 
	 * @param results 体力テストの結果のリスト
	 * @return HTML形式の体力テストの結果
	 */
	private String createResultData(List<PhysicalTestResult> results) {

		StringBuilder resultData = new StringBuilder();
		results.forEach(onePersonData -> {
			resultData.append(createTr(onePersonData));
		});
		return resultData.toString();

	}
	
	/**
	 * tr要素を生成する。
	 *
	 * @param onePersonData １人分の記録 
	 * @return tr要素
	 */
	private String createTr(PhysicalTestResult onePersonData) {

		StringBuilder tr = new StringBuilder();
		tr.append("<tr>").append(CRLF); 
		tr.append(createTd(onePersonData.getString(PhysicalTestResult.KEY_USER_NAME)));
		tr.append(createTd(String.valueOf(onePersonData.getInt(PhysicalTestResult.KEY_SIT_UPS))));
		tr.append(createTd(String.valueOf(onePersonData.getInt(PhysicalTestResult.KEY_50M_RUN))));
		tr.append(createTd(String.valueOf(onePersonData.getInt(PhysicalTestResult.KEY_GRIP))));
		tr.append("</tr>").append(CRLF);
		return tr.toString();

	}

	/**
	 * td要素を生成する。
	 *
	 * @param value 値
	 * @return td要素
	 */
	private String createTd(String value) {

		return new StringBuilder().append("<td>")
									.append(value)
									.append("</td>")
									.append(CRLF)
									.toString();

	}

	/**
	 * 行の中に置換文字列があった場合、マップの値を置換する。 
	 *
	 * @param line HTMLの一行
	 * @param mapReplacement 置換マップ
	 * @return 置換した一行
	 */
	private String replaceLine(String line, Map<String, String> mapReplacement) {

		String newLine = line;
		int idxStart = line.indexOf(REPLACE_MARK);
		if (idxStart != -1) {
			String replacementString = line.substring(idxStart + REPLACE_MARK.length(), line.lastIndexOf(REPLACE_MARK));
			String frontLine = line.substring(0, idxStart);
			String backLine = line.substring(line.lastIndexOf(REPLACE_MARK) + REPLACE_MARK.length());
			StringBuilder replacedLine = new StringBuilder();
			replacedLine.append(frontLine);
			replacedLine.append(mapReplacement.get(replacementString));
			replacedLine.append(backLine);
			newLine = replacedLine.toString();
		}
		return newLine;

	}

}
