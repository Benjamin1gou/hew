/********************************************************
 * クラス名：緊急搬送要請クラス
 * 作成者：t.ueshima
 * 作成日：2016/02/15
 * 内容：緊急搬送するためのメソッドが存在している
 ********************************************************
 *
 *
 *
 *
 ********************************************************/

package beans;


public class Emergency {

	/**
	 * 診療科のフィールド
	 */
	private String _medical;

	/**
	 * トリアージのフィールド
	 */
	private String _triage;

	/**
	 * 緯度のフィールド
	 */
	private Double _ido ;

	/**
	 * 経度のフィールド
	 */
	private Double _keido;

	/**
	 * 住所のフィールド
	 */
	private String _address;

	/**
	 *DB内のデータ
	 */
	private Hospital hos = new Hospital();

	/**
	 * デフォルトコンストラクタ
	 */
	public Emergency(String medical, String triage, String ido, String keido, String address ){
		this._medical = medical;
		this._triage = triage;
		this._ido = Double.parseDouble(ido);
		this._keido = Double.parseDouble(keido);
		this._address = address;
		System.out.println("クラス内診療科："+_medical);
		System.out.println("クラス内トリアージ："+_triage);
		System.out.println("クラス内緯度："+_ido);
		System.out.println("クラス内経度："+_keido);
		System.out.println("クラス内住所："+_address);


	}

	/**
	 * 要請情報を登録するメソッド
	 * @param _medical 診療科
	 * 		  _triage トリアージ
	 * 		  _ido 緯度
	 * 		  _keido 経度
	 * 		  _address 住所
	 */
	public void petientConditionInsert(){
		DbControl db =new DbControl("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/","hew_pro");

		String strSql = "insert into petient_condition(medical_id,emergency,date,latitude,longitude,occ_address) ";
			   strSql +="values("+_medical+",'"+_triage+"',current_date,"+_ido+","+_keido+",'"+_address+"');";

		db.DbConnect();
		String strF = db.easyInsert(strSql);

		db.DbClose();
	}

	/**
	 * 要請可能な病院を検索するメソッド
	 * @param _medical 診療科
	 * 		  _address 住所
	 *
	 * @return 特定の診療科の受け入れ可能人数が1人以上かつ、県・市が一致した病院の
	 * 　　　　病院ID、病院の緯度・経度
	 *
	 *今回使用しない
	 */
	public void hospitalSelect(){


		DbControl db =new DbControl("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/","hew_pro");

		String strSql = "select hos.Hospital_Id, hos.latitude, hos.longitude from hospital as hos ";
			   strSql +="inner join acceptance_content as ac on hos.Hospital_Id = ac.Hospital_Id ";
			   strSql +="where ac.Medical_Id = "+_medical+" and ";
			   strSql +="ac.acceptable_number > 0 and ";
			   strSql +="hos.address like '"+_address+"%';";

	}




}
