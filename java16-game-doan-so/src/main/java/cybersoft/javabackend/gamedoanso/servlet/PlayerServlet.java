package cybersoft.javabackend.gamedoanso.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import cybersoft.javabackend.gamedoanso.model.Player;
import cybersoft.javabackend.gamedoanso.service.StoreService;
import cybersoft.javabackend.gamedoanso.util.JspConst;
import cybersoft.javabackend.gamedoanso.util.UrlConst;

@WebServlet(name = "playerServlet", urlPatterns = { UrlConst.PLAYER_LOGIN, UrlConst.PLAYER_REGISTER, UrlConst.ROOT })
public class PlayerServlet extends HttpServlet {
	private List<Player> players;

	@Override
	public void init() throws ServletException {
		super.init();
		players = StoreService.players;
		players.add(new Player("admin", "123"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.PLAYER_REGISTER:
			req.setAttribute("existeduser", 0);
			req.setAttribute("notmatchedpass", 0);
			req.setAttribute("blankusername", 0);
			req.setAttribute("blankplayername", 0);
			req.setAttribute("blankpassword", 0);
			resp.addCookie(new Cookie("lastplayername", ""));
			resp.addCookie(new Cookie("lastusername", ""));
			req.getRequestDispatcher(JspConst.PLAYER_REGISTER).forward(req, resp);
			break;

		case UrlConst.PLAYER_LOGIN:
			Cookie[] cookies = req.getCookies();
			String username = null;

			if (cookies != null) {
				Optional<Cookie> usernameCookieOpt = Arrays.asList(cookies).stream()
						.filter(t -> t.getName().equals("username")).findFirst();
				if (usernameCookieOpt.isPresent()) {
					username = usernameCookieOpt.get().getValue();
				}
			}

			if (username != null) {
				req.setAttribute("lastUsername", username);
			}

			req.getRequestDispatcher(JspConst.PLAYER_LOGIN).forward(req, resp);
			break;
		default:
			resp.getWriter().append("??i sai ???????ng r???i anh ??y!!!");
			break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		String playername, username, password, message;

		switch (path) {
		case UrlConst.PLAYER_LOGIN:
			username = req.getParameter("username");
			password = req.getParameter("password");

			Optional<Player> curPlayerOpt = players.stream().filter(t -> t.getUsername().equals(username))
					.filter(t -> t.getPassword().equals(password)).findFirst();

			if (curPlayerOpt.isPresent()) {
				HttpSession session = req.getSession();
				session.setAttribute("player", curPlayerOpt.get());
				resp.sendRedirect(req.getContextPath() + UrlConst.GAME_ROOT);
			} else {
				resp.sendRedirect(req.getContextPath() + UrlConst.PLAYER_LOGIN);
			}

			break;

		case UrlConst.PLAYER_REGISTER:
			playername = req.getParameter("playerName");
			username = req.getParameter("username");
			password = req.getParameter("password");
			String rPassword = req.getParameter("rPassword");

			Cookie[] cookies = req.getCookies();

			String lastUsername = "";
			String lastPlayername = "";

			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("lastusername"))
					lastUsername = cookies[i].getValue();
				else if (cookies[i].getName().equals("lastplayername"))
					lastPlayername = cookies[i].getValue();
			}

			if (!lastUsername.equals("") || !username.equals(""))
				req.setAttribute("lastusername", lastUsername);
			if (!lastPlayername.equals("") || !playername.equals(""))
				req.setAttribute("lastplayername", lastPlayername);

			boolean isExistedUsername = players.stream().anyMatch(t -> t.getUsername().equalsIgnoreCase(username));

			if (isExistedUsername) {
				req.setAttribute("existeduser", 1);
			} else
				req.setAttribute("existeduser", 0);

			if (!password.equals(rPassword))
				req.setAttribute("notmatchedpass", 1);
			else
				req.setAttribute("notmatchedpass", 0);

			if (username.equals(""))
				req.setAttribute("blankusername", 1);
			else
				req.setAttribute("blankusername", 0);

			if (playername.equals(""))
				req.setAttribute("blankplayername", 1);
			else
				req.setAttribute("blankplayername", 0);

			if (password.equals(""))
				req.setAttribute("blankpassword", 1);
			else
				req.setAttribute("blankpassword", 0);

			resp.addCookie(new Cookie("lastplayername", playername));
			resp.addCookie(new Cookie("lastusername", username));

			if (!password.equals(rPassword) || isExistedUsername || username.equals("") || playername.equals("")
					|| password.equals(""))
				req.getRequestDispatcher(JspConst.PLAYER_REGISTER).forward(req, resp);
			else {
				players.add(new Player(playername, username, password));
				resp.sendRedirect(req.getContextPath() + UrlConst.PLAYER_LOGIN);
			}
			break;

		default:
			resp.getWriter().append("??i sai ???????ng r???i anh ??y!!!");
			break;
		}
	}
}
