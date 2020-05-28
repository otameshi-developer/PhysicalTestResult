package study.json;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 体力テストの結果を保持するクラス
 *
 */
public class PhysicalTestResult extends JSONObject implements Comparable<PhysicalTestResult>{

	public static final String KEY_USER_NAME = "user_name";

	public static final String KEY_SIT_UPS = "sit_ups";

	public static final String KEY_50M_RUN = "run_50m";

	public static final String KEY_GRIP = "grip";

	private final String KEY_NAME_STUDENT_NUMBER = "student_number";

	/**
	 * コンストラクタ
	 *
	 * @param testResult 体力テストの結果のJSONObject
	 */
	public PhysicalTestResult(JSONObject testResult) {

		super(testResult.toString());

	}

	/**
	 * JSONObjectのキーに関連付けられた文字列を取得する。
	 *
	 * @param key　キーとなる文字列
	 * @return　値となる文字列(エラーが発生した場合はerror)
	 */
	@Override
	public String getString(String key) {

		try {
			return super.getString(key);

		} catch (JSONException e) {
			e.printStackTrace();
			return "error";
		}

	}

	/**
	 * JSONObjectのキーに関連付けられた数値を取得する。
	 * 
	 * @param key　キーとなる文字列
	 * @return　値となる数値(エラーが発生した場合は-1)
	 */
	@Override
	public int getInt(String key) {

		try {
			return super.getInt(key);

		} catch (JSONException e) {
			e.printStackTrace();
			return -1;
		}

	}

	/**
	 * 体力テストの結果を出席番号の昇順に並び替える。
	 *
	 * @param compareObject 比較対象のオブジェクト
	 * @return 比較対象の出席番号より小さい場合は負の整数、等しい場合は0、大きい場合は正の整数
	 */
	@Override
	public int compareTo(PhysicalTestResult compareObject) {

		int base = this.getInt(KEY_NAME_STUDENT_NUMBER);
		int comparison = compareObject.getInt(KEY_NAME_STUDENT_NUMBER);
		return Integer.compare(base, comparison);

	}

}
