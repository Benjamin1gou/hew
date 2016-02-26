/**
 * クラス名:DBクラス
 * 制作日:2015/12/15
 * 制作者:t.ueshima
 * 内容:DBにコネクトからクローズまでの処理があるよ
 */

package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class DbControl {


//-----------------------フィールド-------------------------------
	/**
	 * DBドライバーのフィールド
	 */
	private String strDriver;

	/**
	 * DBパスのフィールド
	 */
	private String strPath;

	/**
	 * DB名のフィールド
	 */
	private String strDbName;

	/**
	 * コネクションのフィールド
	 */
	private Connection cn;

	/**
	 * ステートメントのフィールド
	 */
	private Statement st;

	/**
	 * リザルトセットのフィールド
	 */
	private ResultSet rs;

	/**
	 * カラム名のフィールド
	 */
	private ArrayList<String> lineName = new ArrayList<String>();

	/**
	 * where部分のフィールド
	 */
	private ArrayList<String> condition = new ArrayList<String>();

	/**
	 * SQLのフィールド
	 */
	private StringBuffer stbSql = new StringBuffer();

	/**
	 * データのテーブルのフィールド
	 */
	private ArrayList<ArrayList> table = new ArrayList<ArrayList>();

	/**
	 * データのレコードのフィールド
	 */
	private ArrayList<String> record = new ArrayList<String>();

	/**
	 * テーブル名のフィールド
	 */
	private String strTableName ;
//-----------------------セッター----------------------------------
	/**
	 * コンストラクタ
	 * @param driver
	 * @param path
	 * @param dbName
	 */
	public DbControl(String driver, String path, String dbName ){
		this.strDriver = driver;
		this.strPath = path;
		this.strDbName = dbName;

	}


	/**
	 * カラム名のセッター
	 * @param lineName
	 */
	public void setLineName(String lineName){
		this.lineName.add(lineName);
	}

	/**
	 * whereのセッター
	 * @param condition
	 */
	public void setCondition(String condition){
		this.condition.add(condition);
	}

	/**
	 * 使用するテーブル名のセッター
	 */
	public void setTableName(String tableName){
		this.strTableName = tableName;
	}
//-----------------------------------------------------------------------

	/**
	 * DBにコネクトするメソッド
	 * @param strDriver ドライバー名
	 *        strPath   DBパス
	 *        strDbName DB名
	 *        cn        コネクション
	 *        st        ステートメント
	 */
	public void DbConnect(){
		try{
			Class.forName(strDriver);
			cn = DriverManager.getConnection(strPath + strDbName,"root","");
			st = cn.createStatement();
		}catch(SQLException e){
			System.out.println("SQLException:"+ e.getMessage());
		}catch(ClassNotFoundException e){
			System.out.println("ClassNotFoundException" + e.getMessage());
		}
	}

	/**
	 * クローズのメソッド
	 */
	public void DbClose(){
		try{
			st.close();
			cn.close();
		}catch(SQLException e){
			System.out.println("SQLException:"+ e.getMessage());
		}
	}

	/**
	 * Selectのメソッド
	 */
	public ArrayList<ArrayList> Select(){
		//SQLの結合
		stbSql = stbSql.append("select ");
		for(String strColmun:lineName){
			stbSql = stbSql.append(strColmun + " ");
		}
		stbSql = stbSql.append("From " + strTableName + " ");
		stbSql = stbSql.append("where ");
		stbSql = stbSql.append("1 = 1 ");
		for(String strCondition:condition){
			stbSql = stbSql.append("And ");
			stbSql = stbSql.append(strCondition + " ");
		}
		stbSql = stbSql.append(";");
		String strSql = String.valueOf(stbSql);

		ArrayList<ArrayList> selectTable = new ArrayList<ArrayList>();
		try{
			//SQLの実行
			rs = st.executeQuery(strSql);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				ArrayList<String> selectRec = new ArrayList<String>();

				for(int i = 1 ; i <= rsmd.getColumnCount(); i++){
					selectRec.add(rs.getString(i));
				}
				selectTable.add(selectRec);
			}
			rs.close();
			return selectTable;

		}catch(SQLException e){
			System.out.println("ミスがあります");
			System.out.println(e);
			return selectTable;
		}

	}
	/**
	 * select 簡潔版
	 */
	public ArrayList<ArrayList> EasySelect(String sql){
		ArrayList<ArrayList> selectTable = new ArrayList<ArrayList>();

		try{
			//SQLの実行
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()){
				ArrayList<String> selectRec = new ArrayList<String>();

				for(int i = 1 ; i <= rsmd.getColumnCount(); i++){
					selectRec.add(rs.getString(i));
				}
				selectTable.add(selectRec);
			}
			rs.close();
			System.out.println(selectTable);
			return selectTable;

		}catch(SQLException e){
			System.out.println("ミスがあります");
			return selectTable;
		}
	}

	/**
	 * select 簡潔版 1件抽出
	 */
	public ArrayList<String> EasyOneSelect(String sql){
		ArrayList<String> selectTable = new ArrayList<String>();

		try{
			//SQLの実行
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();

			if(rs.next()){
				for(int i = 1 ; i <= rsmd.getColumnCount(); i++){
					selectTable.add(rs.getString(i));
				}

			}
			rs.close();
			System.out.println(selectTable);
			return selectTable;

		}catch(SQLException e){
			System.out.println("ミスがあります");
			return selectTable;
		}
	}

	/**
	 * insert　簡潔版
	 */
	public String easyInsert(String sql){


		System.out.println(sql);
		//SQLの実行
		try{
			st.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("ミスがある");
			return "1";
		}
		return "0";

	}

	/**
	 * update 簡潔版
	 *
	 */
	public String easyUpdate(String sql){
		System.out.println(sql);
		//SQLの実行
		try{
			st.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("ミスがある");
			return "1";
		}
		return "0";

	}
}

