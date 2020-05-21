package study.main;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import study.html.HtmlBuilder;
import study.html.HtmlPrinter;
import study.json.FileReader;
import study.json.ListSorter;
import study.json.PhysicalTestResult;

/**
 * TODO: otsuka: Project全体的な指摘はここに記載します。
 * ◆空行の先頭にタブがある箇所がいくつかあります。
 * 　一般的なサーバーとかだったらパワーあるからあんまり気にしなくていいところかもしれないけど、
 * 　MEAPとかだと出来るだけリソースの消費を抑えたいので、削除する癖をつけてください。
 * 　この時、eclipseで、自動で末尾のスペースやタブをトリムする機能があるけど
 * 　これは使用しないでください。
 * 　特に既存アプリのソースに対して、勝手に空白削除とかすると差分がえげつない量でてしまうので。
 *
 * ◆空行の開け方がバラバラ。
 * 　個人的には「こーゆーところで改行しろ！」みたいな強制はしないけど、
 * 　自身の中では統一性を持っていてほしい。
 * 　※※大前提として、既存ソースは既存のルールに合わせること。自分色を主張するとソースが読みにくくなる。※※
 * 　メソッドの開始終了は1行空ける。とか、何かしら自分のルールを持って、どう書いたら読みやすいかな？ってのを
 * 　常に考えてください。
 *
 * ◆base.htmlとphysicalTestData.jsonの置き場所
 * 　なんでsrcフォルダ内？resじゃない？ kaneko: MEAPの取り方でリソースを取得していたのですが、その場合srcフォルダ内でないと取得できなかったので、srcフォルダ内に置いていました。Fileインスタンスを生成する方法だとresから取得できたので変更します。
 *
 *
 */

/**
 * 体力テストの結果を使ってHTMLファイルを生成する。
 *
 */
public class Main { // TODO: otsuka:Mainってjsonパッケージの中がいいの？ kaneko:Mainのパッケージは考えていなかったです。新たにmainパッケージを作って、その中にしました。

	public static void main(String[] args) {

		try {
			String physicalTestResultJSON = new FileReader().read("res/physicalTestData.json");
			JSONObject physicalTestResult = new JSONObject(physicalTestResultJSON); // TODO: otsuka: physicalTestResultJSONが空文字だったらどうなるの？ kaneko: 考えていなかったです。JSONExceptionが発生するのでcatchしてスタックトレースを吐くようにします。
			List<PhysicalTestResult> results = new ListSorter().sortList(physicalTestResult);
			String html = new HtmlBuilder().createHtml(results);
			new HtmlPrinter().printHtml(html);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
