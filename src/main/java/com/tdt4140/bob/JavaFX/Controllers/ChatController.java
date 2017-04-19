package com.tdt4140.bob.JavaFX.Controllers;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.tdt4140.bob.Application.DatabaseHandler;
import com.tdt4140.bob.Application.Chat.ChatHandler;
import com.tdt4140.bob.JavaFX.Controllers.Login.User;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

public class ChatController extends Controller {

	@FXML
	private TextFlow area;
	@FXML
	private TextField input, text;
	@FXML
	private Button button, btnAdmin, btnLogout, commands, questions;
	@FXML
	private TextArea chat1, chat2, chat;
	@FXML
	private ImageView btnSettings;
	@FXML
	private ComboBox<String> choiceSubject = new ComboBox<>();
	@FXML
	private Text labelText;
	@FXML
	private ScrollPane scroll;

	private DatabaseHandler dbh;
	private ArrayList<String> subjects = new ArrayList<String>();
	private String pick;
	private ResultSet rs = null;
	private ResultSet rs1 = null;
	private String username;
	private TextFlow reply;

	/**
	 * Function activated when launching the class ChatController
	 * <p>
	 * Retrieves the subjects that the user attends and adds them to the list
	 *
	 * @author jorgburg
	 */
	public void onLoad() {
		username = User.getUsername();
		pick = "";
		dbh = app.getDatabaseHandler();

		ResultSet data = null;
		try {
			data = ChatHandler.getSubjects(dbh, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			while (data.next()) {
				subjects.add(data.getString("coursename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < subjects.size(); i++) {
			choiceSubject.getItems().add(subjects.get(i));
		}
	}

	/**
	 * Function activated by chaning drop down list
	 * <p>
	 * Change the subject which you communicate with
	 *
	 * @author jorgburg
	 */
	public void subjectPick() {
		pick = choiceSubject.getValue();
	}

	/**
	 * Function activated by button clicked
	 * <p>
	 * The bot writes back the possible commands in the chat
	 *
	 * @author jorgburg
	 */
	public void showCommands() {
		Text text = new Text("BOB: Possible commands are: \n" + "1: /subjects \n" + "2: /lecturer \n" + "3: /clear \n"
				+ "4: Ask me something \n\n");
		area.getChildren().add(text);
	}

	/**
	 * Function activated by button clicked
	 * <p>
	 * The bot writes back the users last questions in the chat
	 *
	 * @author jorgburg
	 */
	public void showLastQuestions() throws SQLException {
		int i = 1;
		ResultSet output = ChatHandler.getQuestion(dbh, username);
		ArrayList<String> questions = new ArrayList<String>();
		while (output.next()) {
			questions.add(i + ": " + output.getString("question"));
			i++;
		}
		Text text = new Text("BOB: Your last questions: \n" + questions + "\n\n");
		area.getChildren().add(text);
	}

	/**
	 * Function activated by button clicked
	 * <p>
	 * The bot gathers the information from the user and analyzes the
	 * input/command. Based on the input, the bot writes back an answer.
	 *
	 * @author jorgburg
	 */
	public void chatClicked() throws IOException, SQLException {
		String uText = input.getText();
		TextFlow reply = toTextFlow("ERROR!");

		String TEXT = uText.toLowerCase();
		String part[] = TEXT.split(" ");
		int n = part.length;

		if (!(pick.equals(""))) {
			if ((TEXT.contains("hello")) || (TEXT.contains("hi"))) {
				int reply_decider = (int) (Math.random() * 3 + 1);
				if (reply_decider == 1) {
					reply = toTextFlow("Hello there!");
				} else if (reply_decider == 2) {
					reply = toTextFlow("How you doin? ^^");
				} else if (reply_decider == 3) {
					reply = toTextFlow("Hello mate!");
				}
			}

			else if ((part[0].equals("what") || part[0].equals("who") || part[0].equals("where"))
					&& part[1].contains("is")) {
				ArrayList<String> list = new ArrayList<String>();
				String subject = "";
				for (int i = 2; i < n; i++) {
					list.add(part[i]);
				}
				for (String s : list) {
					subject += s + " ";
				}
				String input1 = subject.substring(0, subject.length() - 1);
				String input2 = WordUtils.capitalizeFully(subject);
				String code = null;
				String page = null;

				rs = ChatHandler.getSubjectCode(dbh, pick);
				if (rs.next()) {
					code = rs.getString("code");
				}

				rs1 = ChatHandler.getCurriculum(dbh, subject, code);
				if (rs1.next()) {
					page = rs1.getString("pages");
					ChatHandler.writeKeywords(dbh, subject, code);
					ChatHandler.writeQuestion(dbh, TEXT, username);
					if (wikipedia(input1).equals("0")) {
						reply = toTextFlow("You can read about '" + input2 + "' in the curriculum on the pages: " + page);
					} else if (hasReferTo(wikipedia(input1))) {
						reply = new TextFlow(
								new Text("You can read about '" + input2 + "' in the curriculum on the page(s): " + page
										+ "\nI also found something on Wikipedia,\nbut it may refer to multiple subjects."),
								showWikipedia(input1));
					} else {
						reply = toTextFlow("You can read about '" + input2 + "' in the curriculum on the page(s): " + page
								+ "\nI also found this on wikipedia: " + "\n" + wikipedia(input1));
					}
				} else {
					if (wikipedia(input1).equals("0")) {
						reply = toTextFlow("Please rephrase! I couldnt find what you where looking for :(");
					} else if (wikipedia(input1).equals("1")) {
						reply = new TextFlow(new Text("This may refer to multiple subjects."),
								showWikipedia(input1));
					} else {
						ChatHandler.writeKeywords(dbh, subject, code);
						ChatHandler.writeQuestion(dbh, TEXT, username);
						if (!hasReferTo(wikipedia(input1))) {
							reply = toTextFlow("I found this on Wikipedia:" + "\n" + wikipedia(input1) + "\n");
						} else {
							reply = new TextFlow(new Text("This may refer to multiple subjects."),
									showWikipedia(input1));
						}
					}
				}

			}

			else if (TEXT.equals("/subjects")) {
				int reply_decider = (int) (Math.random() * 2 + 1);
				if (reply_decider == 1) {
					reply = toTextFlow("You have the subjects: \n" + subjects);
				} else if (reply_decider == 2) {
					reply = toTextFlow("Currently, you are attending these classes: \n" + subjects);
				}
			}

			else if (part[0].equals("/lecturer")) {
				ResultSet output = ChatHandler.getLecturer(dbh, pick);
				ArrayList<String> lecturer = new ArrayList<String>();
				while (output.next()) {
					lecturer.add(output.getString("fornavn") + " " + output.getString("etternavn"));
				}
				if (lecturer.isEmpty()) {
					reply = toTextFlow("I dont think there are registered any lecturers in that course..");
				} else {
					reply = toTextFlow("In " + pick + " teaches the lecturer: \n" + lecturer);
				}
			}

			else if ((part[0].equals("/clear"))) {
				int reply_decider = (int) (Math.random() * 2 + 1);
				if (reply_decider == 1) {
					reply = toTextFlow("Cleared!");
				} else if (reply_decider == 2) {
					reply = toTextFlow("Bye bye text =)");
				}
			}

			else if ((part[0].equals("/help")) || part[0].equals("/commands")) {
				reply = toTextFlow("Possible commands are: \n \n" + "1: /subjects \n" + "2: /lecturer \n" + "3: /clear \n"
						+ "4: Ask me something");
			}

			else if (part[0].contains("h") && part[0].contains("e") && part[0].contains("l") && part[0].contains("p")
					&& part[0] != "help") {
				reply = toTextFlow("Did you mean '/help'?");
			}

			else if (part[0].contains("c") && part[0].contains("o") && part[0].contains("m") && part[0].contains("n")
					&& part[0] != "commands") {
				reply = toTextFlow("Did you mean '/commands'?");
			}

			else if (part[0].contains("c") && part[0].contains("l") && part[0].contains("e") && part[0].contains("a")
					&& part[0].contains("r") && part[0] != "clear") {
				reply = toTextFlow("Did you mean '/clear'?");
			}

			else if (part[0].contains("w") && part[0].contains("h") && part[0].contains("a") && part[0] != "what") {
				reply = toTextFlow("Did you mean to ask me a question?");
			}

			else if (TEXT.contains("i am") || (part[0].contains("i") && part[0].contains("m"))) {
				int reply_decider = (int) (Math.random() * 2 + 1);
				if (reply_decider == 1) {
					reply = toTextFlow("Good for you!!");
				} else if (reply_decider == 2) {
					reply = toTextFlow("Nice to hear that!");
				}
			}

			else if (TEXT.contains("how are you")
					|| ((part[0].contains("how")) && (part[1].contains("are")) && (part[2].contains("you")))) {
				int reply_decider = (int) (Math.random() * 3 + 1);
				if (reply_decider == 1) {
					reply = toTextFlow("I'm doing well thanks! And you?");
				} else if (reply_decider == 2) {
					reply = toTextFlow("Not too bad, and for you?");
				} else if (reply_decider == 3) {
					reply = toTextFlow("Very well thank you! How is it going for you?");
				}
			}

			else {
				int reply_decider = (int) (Math.random() * 4 + 1);
				if (reply_decider == 1) {
					reply = toTextFlow("I didn't get that, try '/help' to show commands");
				} else if (reply_decider == 2) {
					reply = toTextFlow("Please rephrase that or type '/commands' to show commands");
				} else if (reply_decider == 3) {
					reply = toTextFlow("Type '/help' to show commands");
				} else if (reply_decider == 4) {
					reply = toTextFlow("'/help' will show you some useful comands =D");
				}
			}
			
			this.reply = reply;

			if (TEXT.contains("/clear")) {
				input.setText("");
				area.getChildren().clear();
			} else {
				input.setText("");
				chatArea(uText, this.reply);
			}
		} else {
			Text text = new Text("BOB: Please choose a subject! \n\n");
			area.getChildren().add(text);
		}
	}

	/**
	 * Function activated by function chatClicked()
	 * <p>
	 * This function adds text to the chat area based on the strings that it
	 * gets from the function chatClicked()
	 *
	 * @author jorgburg
	 */
	public void chatArea(String user, TextFlow bot) {
		this.reply = new TextFlow(new Text("You: " + user + "\nBOB: "), bot, new Text("\n\n"));
		area.getChildren().add(this.reply);
		Animation animation = new Timeline(new KeyFrame(Duration.seconds(2), new KeyValue(scroll.vvalueProperty(), 1)));
		animation.play();
	}

	/**
	 * Function activated by function chatClicked()
	 * <p>
	 * This function gathers information from wikipedia on a given topic.
	 * 
	 * @param object
	 *            The object that the user searched for
	 * @return Returns the first sentence from the wikipedia article of the
	 *         given object
	 * @author jorgburg
	 */
	public String wikipedia(String object) throws IOException {
		System.out.println("Ble skrevet inn: " + object);
		URL url = new URL(
				"https://en.wikipedia.org/w/api.php?action=query&prop=extracts&format=json&exsentences=1&exintro=&explaintext=&exsectionformat=plain&titles="
						+ object.replace(" ", "%20"));
		String text = "";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()))) {
			String line = null;
			while (null != (line = br.readLine())) {
				line = line.trim();
				if (true) {
					text += line;
				}
			}
		}

		String extract = null;
		String check = null;

		try {
			System.out.println("text = " + text);
			JSONObject json = new JSONObject(text);
			JSONObject query = json.getJSONObject("query");
			JSONObject pages = query.getJSONObject("pages");
			for (String key : pages.keySet()) {
				// System.out.println("key = " + key);
				check = key;
				JSONObject page = pages.getJSONObject(key);
				extract = page.getString("extract");
				// System.out.println("extract = " + extract);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return ("0");
		}

		// System.out.println("Check: " + check);

		if (text.contains("missing")) {
			return ("0");
		} else if (extract.contains("redirect") || check.equals("-1")) {
			return ("0");
		} else {
			System.out.println(extract);
			return (extract);
		}

	}

	/**
	 * Function used for test code by function chatClicked()
	 * <p>
	 * Checks if the given command (uText) is valid
	 * 
	 * @param uText
	 *            Written user input
	 * @return Returns true if the command is correct
	 * @author jorgburg
	 */
	public boolean isValidCommand(String uText) {
		if (uText.equals("/lecturer") || uText.equals("/clear") || uText.equals("/help") || uText.equals("/subjects")) {
			return true;
		}
		return false;
	}

	/**
	 * Function activated by button clicked
	 * <p>
	 * This function sends the user to the settings page
	 * 
	 * @author jorgburg
	 */
	public void showSettings() {
		app.makeSettings();
	}

	/**
	 * Function activated by button clicked
	 * <p>
	 * This function sends the user to the admin page
	 * 
	 * @author jorgburg
	 */
	public void showAdmin() {
		app.makeAdmin();
	}

	/**
	 * Function activated by button clicked
	 * <p>
	 * This function sends the user to the log in page and the user is now
	 * logged out.
	 * 
	 * @author jorgburg
	 */
	public void logOut() {
		User.resetUser();
		app.makeLogin();
	}

	/**
	 * Function used for test code for wikipedia()
	 * <p>
	 * This function checks if there is any information to gather from wikipedia
	 * on the given topic(object). *
	 * 
	 * @param object
	 *            Object that is searched for in wikipedia
	 * @return True if the wikipedia page exist, else false
	 * @author jorgburg
	 */
	public boolean isValidWiki(String object) throws IOException {
		String input1 = object;
		String input2 = WordUtils.capitalizeFully(object);

		if (!(wikipedia(input1).equals("0")) || !(wikipedia(input2).equals("0") || hasReferTo(input1))) {
			return true;
		}
		return false;
	}

	public boolean hasReferTo(String extract) {
		if (extract.contains("may refer to")) {
			return true;
		}
		return false;
	}

	private TextFlow showWikipedia(String object) {
		Hyperlink link = new Hyperlink("Click here to see all disambiguations.");
		TextFlow text = new TextFlow(link);
		link.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(new URI("https://en.wikipedia.org/wiki/"
								+ URLEncoder.encode(object.substring(0, object.length()), "UTF-8")));
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		return text;
	}
	
	private TextFlow toTextFlow(String text) {
		return new TextFlow(new Text(text));
	}

}
