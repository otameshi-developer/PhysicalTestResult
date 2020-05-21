package study.json;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 体力テストの結果を保持するクラス
 *　TODO: otsuka: このクラスの説明は嫌です。
 * インターフェースって1クラスに何個でも指定できるけど、全部書くの？
 * Aインターフェースと、Bインターフェースと、Cインターフェース・・・・・・を実装したクラスです。って感じ？涙
 * ※たしか数に制限はないはず。。。
 * kaneko: インターフェースを実装した旨のコメントは消しました。
 *
 */
public class PhysicalTestResult implements Comparable<PhysicalTestResult>{

	// TODO: kaneko: JSONのキーはPhysicalTestResultに持たせました。また、KEY_NAMEのNAMEを消しました。
	public static final String KEY_USER_NAME = "user_name";

	public static final String KEY_SIT_UPS = "sit_ups";

	public static final String KEY_50M_RUN = "run_50m";// TODO: kaneko: 文字から始まるように50m_runから変更しました。

	public static final String KEY_GRIP = "grip";

	private final String KEY_NAME_STUDENT_NUMBER = "student_number";

	private JSONObject testResult; // TODO: otsuka: publicはダメです。外から触り放題。仮にpublicだとしたら、後述のgetterは不要です。 kaneko: privateに変更しました。

	/**
	 * コンストラクタ
	 *
	 * @param testResult 体力テストの結果のJSONObject
	 */
	public PhysicalTestResult(JSONObject testResult) { // TODO: otsuka: joって略す人みたことないな。。。testResultじゃダメなの？ kaneko: testResultの方が分かり易いので変更しました。

		this.testResult = testResult;

	}

	/**
	 * 体力テストの結果を保持するJSONObjectを取得する。
	 * 
	 * @return　体力テストの結果を保持するJSONObject
	 */
//	public JSONObject getJSONObject() { // TODO: これっているのかなぁ。。。いちいちjson取得しないといけない？ kaneko: JSONObjectを取得することを止め、getStringとgetIntを用意しました。
//		return this.testResult;
//	}
	
	/**
	 * JSONObjectのキーに関連付けられた文字列を取得する。
	 *
	 * @param key　キーとなる文字列
	 * @return　値となる文字列
	 * @throws JSONException キーに関連付けられた文字列がない
	 */
	public String getString(String key) throws JSONException {

		return this.testResult.getString(key);

	}

	/**
	 * JSONObjectのキーに関連付けられた数値を取得する。
	 * 
	 * @param key　キーとなる文字列
	 * @return　値となる数値
	 * @throws JSONException キーに関連付けられた数値がない
	 */
	public int getInt(String key) throws JSONException {

		return this.testResult.getInt(key);

	}

	/**
	 * 体力テストの結果を出席番号の昇順に並び替える。
	 * TODO: otsuka: returnのところにどんな値を返すかは書いてあるけど、、、
	 * これ結局どんな順番になるの？ってのがわからなくない？ kaneko: 出席番号の昇順に並び替えるという旨を追加しました。
	 *
	 * @param compareObject 比較対象のオブジェクト TODO: otsuka: other??コメントと変数名の意味が合っていないね。 kaneko: compareObjectに変更しました。
	 * @return 比較対象の出席番号より小さい場合は負の整数、等しい場合は0、大きい場合は正の整数
	 */
	@Override
	public int compareTo(PhysicalTestResult compareObject) {
		/*
		 * TODO: otsuka: compareNumberとcomparedNumber
		 * 比較する値と比較された値？ってことかな？意味あってる？ kaneko: 比較する値と比較される値という意味で付けていました。比較元の値と比較する値という意味に変更します。
		 */
		int comparisonSourceNumber = this.testResult.getInt(KEY_NAME_STUDENT_NUMBER);
		int compareNumber = compareObject.testResult.getInt(KEY_NAME_STUDENT_NUMBER);
		return Integer.compare(comparisonSourceNumber, compareNumber); // TODO: commaの後ろは半角スペース空けてね

	}

}
