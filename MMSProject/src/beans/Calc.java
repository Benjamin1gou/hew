/**********************************************************
 * プログラム名：計算クラス
 * 作成日：2016/02/16
 * 作成者：t.ueshima
 * 内容：計算メソッドがたくさん
 * ********************************************************
 *
 *
 *
 */
package beans;

public class Calc {
	/**
	 * 現在地の緯度
	 */
	private Double _nowIdo;

	/**
	 * 現在地の経度
	 */
	private Double _nowKeido;

	/**
	 * 取得した緯度
	 */
	private Double _getIdo;
	/**
	 * 取得した経度
	 */
	private Double _getKeido;

	/**
	 *デフォルトコンストラクタ
	 */
	public Calc (Double ido, Double keido){
		this._nowIdo = ido;
		this._nowKeido = keido;
	}
}
