package study.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import study.json.PhysicalTestResult;

/**
 * 体力テスト結果のHTMLを生成する。
 *
 */
public class HtmlBuilder {

	private static final String CRLF = "\r\n";

	private static final String REPLACE_MARK = "$$";

	// TODO: otsuka: なんでHTML担当の人がJSONのキー知ってるの？？役割が違うんじゃない？あと、KEY_NAMEのNAMEっているかな？もしくはKEY kaneko: JSONのキーはPhysicalTestResultに持たせました。
//	private static final String KEY_NAME_USER_NAME = "user_name";

//	private static final String KEY_NAME_BLOOD_TYPE = "blood_type";

//	private static final String KEY_NAME_50M_RUN = "50m_run"; // TODO: otsuka: 数字始まりは微妙。一応使えるけど、、、JSONってJavaScriptをベースにしてるからなぁ（JSとJSONは別規格となっているので考えなくても良いかもだけど。）。。。JSは数字始まりはngのはず。参考程度だけど、Google内ではこんな感じでルールがあるみたいね[https://google.github.io/styleguide/jsoncstyleguide.xml?showone=Property_Name_Format#Property_Name_Format]。

//	private static final String KEY_NAME_GRIP = "grip";

	/**
	 * 体力テスト結果のHTMLを生成する。
	 *
	 * @param results 体力テスト結果のリスト
	 * @return 体力テスト結果のHTML
	 * @throws IOException 読み込みに失敗
	 */
	public String createHtml (List<PhysicalTestResult> results) throws IOException, JSONException { // TODO: kaneko: buildHtmlからcreateHtmlに変更しました。

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
	private String createResultData(List<PhysicalTestResult> results) throws JSONException { // TODO: kaneko: buildResultDataからcreateResultDataに変更しました。

		StringBuilder resultList = new StringBuilder();
		Iterator<PhysicalTestResult> ite = results.iterator(); // TODO: kaneko: for文を止め、Iteratorを使ってループ処理を行うようにしました。
		while (ite.hasNext()) {
			resultList.append(createTr(ite.next()));
		}
		return resultList.toString();

	}
	
	/**
	 * tr要素を生成する。
	 *
	 * @param onePersonRecord １人分の記録 // TODO: kaneko: physicalTestResultから変更しました。
	 * @return tr要素
	 */
	private String createTr(PhysicalTestResult onePersonRecord) throws JSONException { // TODO: kaneko: buildTrからcreateTrに変更しました。また、JSONObjectではなく自作クラスのPhysicalTestResultを受け取るようにしました。

		StringBuilder tr = new StringBuilder();
		tr.append("<tr>").append(CRLF); // TODO: otsuka: あんまりこれっと言って意味を持たないCRLFを1行で書かなくて良いかな（<tr>のappendの後ろに付けた方がいいんじゃない？）。例えばHTTPのパケットのヘッダーとボディの区切りとか、特別な意味を持っているなら1行で書かないと埋もれちゃうけど、これってHTMLのソース上の見た目だけのものだよね（ブラウザ上では見えないもの）？であればそんなに重要ではないと思う。 kaneko: CRLFを1行で書かずに繋げました。
		tr.append(createTd(onePersonRecord.getString(PhysicalTestResult.KEY_USER_NAME)));
		tr.append(createTd(String.valueOf(onePersonRecord.getInt(PhysicalTestResult.KEY_SIT_UPS))));//
		tr.append(createTd(String.valueOf(onePersonRecord.getInt(PhysicalTestResult.KEY_50M_RUN))));//
		tr.append(createTd(String.valueOf(onePersonRecord.getInt(PhysicalTestResult.KEY_GRIP))));
		tr.append("</tr>").append(CRLF);
		return tr.toString();

	}

	/**
	 * td要素を生成する。
	 *
	 * @param categoryValue 値
	 * @return td要素
	 */
	private String createTd(String categoryValue) { // TODO: otsuka: なんか至る所で「physicalTestResult」って変数名出てくるけど、これって何者？JSONObjectだったり、Stringだったり、すごい混乱します。しかも結果って名前がついているのに、「user_name」「blood_type」とかも渡ってくるっぽい。。。 kaneko: physicalTestResultから「項目の値」という意味でcategoryValueに変更しました。

		return new StringBuilder().append("<td>")
									.append(categoryValue)
									.append("</td>")
									.append(CRLF)
									.toString();

	}

	/**
	 * 行の中に置換文字列があった場合、マップの値を置換する。 // TODO: otsuka: これって、1行まるまる置換するメソッドだよね？行中に置換文字列が紛れ込んでたら、全体差し替えちゃう？ kaneko: 全体差し替えてしまう実装だったので、行中の置換文字列のみを差し替える実装に変更しました。
	 *
	 * @param line HTMLの一行
	 * @param mapReplacement 置換マップ
	 * @return 置換した一行
	 */
	private String replaceLine(String line, Map<String, String> mapReplacement) {

		String newLine = line; // TODO: kaneko: replacedLineからnewLineに変更しました。
		int idxStart = line.indexOf(REPLACE_MARK);
		if (idxStart != -1) {
			String replacementString = line.substring(idxStart + REPLACE_MARK.length(), line.lastIndexOf(REPLACE_MARK)); // TODO: kaneko: cutStringを置換文字列という意味でreplacementStringに変更しました。
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
