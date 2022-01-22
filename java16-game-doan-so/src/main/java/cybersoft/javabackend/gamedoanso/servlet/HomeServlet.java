package cybersoft.javabackend.gamedoanso.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.gamedoanso.util.UrlConst;

@WebServlet(name = "homeServlet", urlPatterns = UrlConst.HOME)
public class HomeServlet extends HttpServlet {
	/*
	 * TODO: Game Ä‘oÃ¡n sá»‘
	 * 
	 * Khi truy cáº­p vÃ o game, má»™t sá»‘ ngáº«u nhiÃªn trong khoáº£n
	 * [1 - 1000] sáº½ Ä‘Æ°á»£c sinh ra.
	 * NgÆ°á»�i dÃ¹ng cÃ³ nhiá»‡m vá»¥ Ä‘oÃ¡n chÃ­nh xÃ¡c con sá»‘ ngáº«u nhiÃªn nÃ y.
	 * 
	 * Má»—i lÆ°á»£t Ä‘oÃ¡n náº¿u Ä‘oÃ¡n sai, ngÆ°á»�i dÃ¹ng sáº½ Ä‘Æ°á»£c gá»£i Ã½:
	 *  - Sá»‘ vá»«a Ä‘oÃ¡n nhá»� hÆ¡n Ä‘Ã¡p Ã¡n!
	 *  - Sá»‘ vá»«a Ä‘oÃ¡n lá»›n hÆ¡n Ä‘Ã¡p Ã¡n!
	 *  
	 * ThÃ nh tÃ­ch ngÆ°á»�i dÃ¹ng sáº½ Ä‘Æ°á»£c sáº¯p xáº¿p dá»±a trÃªn sá»‘ láº§n Ä‘oÃ¡n.
	 * Ai cÃ³ sá»‘ láº§n Ä‘oÃ¡n Ã­t nháº¥t Ä‘áº¿n khi Ä‘oÃ¡n Ä‘Ãºng sá»‘ sáº½ xáº¿p háº¡ng nháº¥t.
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/doan-so");
	}
}
