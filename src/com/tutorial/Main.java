package com.tutorial;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zulkarnaen
 */

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ini adalah function untuk membuat split
	public static String removeDuplicate(String txt, String splitterRegex) {
		List<String> values = new ArrayList<String>();
		String[] splitted = txt.split(splitterRegex);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < splitted.length; ++i) {
			if (!values.contains(splitted[i])) {
				values.add(splitted[i]);
				sb.append(' ');
				sb.append(splitted[i]);
			}
		}
		return sb.substring(1);
	}

	// ini untuk mengambil duplicate kata yang sama
	public static Set<String> duplicateWords(String input) {
		if (input == null || input.isEmpty()) {
			return Collections.emptySet();
		}

		// set string duplicate dengan function
		// membuat string words input yang di split(mengambil 1 kata dari 2 kata
		// yang sama)
		// kemudian di set dan diambil disimpan di word
		// dan duplicate mengambil alih input dari word
		Set<String> duplicates = new HashSet<>();
		String[] words = input.split("\\s+");
		Set<String> set = new HashSet<>();
		for (String word : words) {
			if (!set.add(word)) {
				duplicates.add(word);
			}
		}
		return duplicates;
	}

	/**
	 * @author zulkarnaen
	 */

	public Main() {
		super();
	}

	/**
	 * @author zulkarnaen
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath())

		// membuat String name untuk mendapat parameter input pada jsp index
		String stringInput = request.getParameter("input");

		// membuat tanggal
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("dd MMMMMMMMMM yyyy");
		String dates = date.format(today);

		/// buat atribute untuk hasil ouput name dengan nama nama
		// nanti dinama akan dikirim ke index.jsp dan akan sebagai outputnya!
		request.setAttribute("nama", stringInput);

		// membuat print servlet dengan nama pw
		PrintWriter pw = response.getWriter();

		// Manipulasi
		String singkat = "saktm";
		String tempe = "t";
		String mendoan = "m";
		String asin = "a";

		// ini untuk mencari nilai string yang beda pada bagian kata berulang
		// nanti!
		Set<String> duplicateWords = duplicateWords(stringInput);

		// membuat split dengan nama output dan name sebagai input di split
		// sehingga 2 kata yang sama akan jadi 1
		// contoh 'zulkarnaen zulkarnaen' akan hanya muncul di output sebagai
		// 'zulkarnaen' saja
		String stringInputSplitted[] = stringInput.split("\\s+");

		// jika diperlukan
		// System.out.println(word);

		// hasil inputan output dimasukan kedalam ArrayList
		// System.out.println(Arrays.asList(st));

		// input name akan masuk ke main dan di print
		stringInput = removeDuplicate(stringInput, " ");
		// System.out.println(name);

		// untuk mencari index keberapa kata tersebut! (dengan nama mp)
		Map<String, List<Integer>> mappedWord = new HashMap<String, List<Integer>>();
		for (int i = 0; i < stringInputSplitted.length; i++) {

			List<Integer> count = mappedWord.get(stringInputSplitted[i]);
			if (count == null) {
				count = new ArrayList<>();
			}
			count.add(i);
			mappedWord.put(stringInputSplitted[i], count);
		}

		// here we go!
		pw.print("<body>");
		pw.print("<h1><font size='7' face='Georgia, Arial' color='maroon'>T</font>ABLE</h1>");
		pw.print("<hr>");
		pw.print("<form action='Main' method='post'>");
		pw.print("<table border='1' cellpadding='24' cellspacing='3'>");
		pw.print("<tbody><tr bgcolor='silver'>");
		pw.print("<td><center>NO</center></td>");
		pw.print("<td><center>Input</center></td>");
		pw.print("<td><center>Output</center></td>");
		pw.print("<td><center>Kata Berulang</center><td>");
		pw.print("<center>kata yang diulang</center></td>");
		pw.print("<td><center>Tanggal</center></td></tr>");
		pw.print("<tr><td>1</td>");

		// memanggil input ArrayList untuk dijadikan input
		pw.print("<td>" + Arrays.asList(stringInputSplitted) + "</td>");

		// memanggil output dari split name untuk dijadikan output
		pw.print("<td>" + stringInput + "</td>");

		pw.print("<td>");

		// memanggil kata yang sama dengan mp.get secara manipulasi
		for (Map.Entry<String, List<Integer>> entry : mappedWord.entrySet()) {
			List<Integer> value = entry.getValue();
			if (value.size() > 1) {
				pw.println(" kata = " + entry.getKey() + "<br>" + " dengan perulangan = " + value.size() + " index: " + stringInput.indexOf(entry.getKey()) +"<br>");
			}

		}

		pw.print("</td>");

		// memanggil duplicate yang sudah didapatkan dari duplicateword(name)
		pw.print("<td>" + duplicateWords + "</td>");

		// memanggil tanggal
		pw.print("<td>" + dates + "</td></tr>");
		pw.print("</tr></tbody></table>");
		pw.print("</form></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
