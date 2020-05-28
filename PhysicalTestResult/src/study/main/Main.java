package study.main;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import study.html.HtmlCreator;
import study.html.HtmlPrinter;
import study.json.FileReader;
import study.json.ListSorter;
import study.json.PhysicalTestResult;

/**
 * 体力テストの結果を使ってHTMLファイルを生成する。
 *
 */
public class Main {

	public static void main(String[] args) {

		try {
			String physicalTestResultJSON = new FileReader().read("res/physicalTestData.json");
			JSONObject physicalTestResult = new JSONObject(physicalTestResultJSON);
			List<PhysicalTestResult> results = new ListSorter().sortList(physicalTestResult);
			String html = new HtmlCreator().createHtml(results);
			new HtmlPrinter().printHtml(html);

		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}

	}

}
