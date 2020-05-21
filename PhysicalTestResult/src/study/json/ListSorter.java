package study.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * リストをソートする。
 *
 */
public class ListSorter {

	/**
	 * リストの中身をソートする。
	 * 
	 * @param physicalTestResult 体力テストの結果リスト // TODO: otsuka: 引数名違う。あと、ここは「体力テストの結果」なのに、returnで使っている文言は「体力テストの結果リスト」と、統一性がない。 kaneko: 引数名を訂正し、returnの文言と統一しました。
	 * @return ソート後の体力テストの結果リスト
	 */
	public List<PhysicalTestResult> sortList(JSONObject physicalTestResult) throws JSONException { // TODO: otsuka: Mainクラスが持っているのが気持ち悪い。他の機能はちゃんと他のクラスに分けているのに。。。
		/*
		 * TODO: otsuka: resultsって名前は良くないかな。
		 * 特にこのアプリは「体力テストの結果」って意味でresultをすでに使用しているから
		 * 紛らわしい。
		 * ここで使うべきは、「ソート済み」ってことを伝えるキーワードじゃない？　kaneko:　「ソート済みのリスト」という意味でsortedListにしました。 
		 */
		List<PhysicalTestResult> sortedList = new ArrayList<>();
		String[] userID = JSONObject.getNames(physicalTestResult);
		for (int i = 0; i < userID.length; i++) {
			sortedList.add(new PhysicalTestResult(physicalTestResult.getJSONObject(userID[i]))); // TODO: otsuka: getJSONObject()ってthrows宣言あるけど、特にエラーハンドリングしていないけど、これって発生したらどうなるの？ kaneko: 考えていなかったです。動作としてはJSONExceptionが発生してスタックトレースを吐いてプログラムは終了します。ちゃんとMainでcatchするようにしました。
		}
		Collections.sort(sortedList);
		return sortedList;

	}

}
