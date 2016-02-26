/**********************************************************************
 * プログラム名：緊急搬送サーブレット
 * 作成者：t.ueshima
 * 作成日：2016/02/15
 * 内容：緊急搬送HTMLより値を受け取りその値を使用し、病院に対して
 * 　　　要請を行う
 **********************************************************************
 *
 *
 *
 *
 **********************************************************************/
package beans;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmergencyServlet
 */
@WebServlet("/EmergencyServlet")
public class EmergencyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmergencyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");


		//値受け取り
		String strMedical = request.getParameter("condition");
		String strTriage = request.getParameter("triage");
		String strIdo = request.getParameter("t_ido");
		String strKeido = request.getParameter("t_keido");
		String strAddress = request.getParameter("t_address");

		System.out.println("診療科番号："+strMedical);
		System.out.println("トリアージ："+strTriage);
		System.out.println("緯度："+strIdo);
		System.out.println("経度："+strKeido);
		System.out.println("アドレス："+strAddress);

		Emergency em = new Emergency(strMedical,strTriage,strIdo,strKeido,strAddress);


		//要請情報登録
		em.petientConditionInsert();

		//forwardする宛先の格納
		request.setAttribute("latitude", strIdo);
		request.setAttribute("longitude", strKeido);

		RequestDispatcher rd = request.getRequestDispatcher("./");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
