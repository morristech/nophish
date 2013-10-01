package de.tudarmstadt.informatik.secuso.phishedu.backend;

/**
 * This is the interface that the backend presents to the frontend.
 * @author Clemens Bergmann <cbergmann@schuhklassert.de>
 *
 */
public interface BackendControllerInterface {
	/**
	 * This function must be called directly before the first start of the app.
	 * It will register the caller with the backend for callbacks.
	 * @param frontend
	 */
	void init(FrontendControllerInterface frontend);
	
	/**
	 * This function sends a Mail to a custom Mail Adress-
	 * @param from The Sender mail address
	 * @param to The receiver mail address
	 * @param usermessage Each send out mail contains a usermessage to maximize the awareness.
	 */
	void sendMail(String from, String to, String usermessage);
	/**
	 * This starts level1.
	 */
	void StartLevel1();
	
	/**
	 * Get the next url and save the URL Information internally.
	 * @return A set of strings that (concardinated) make up the URL
	 */
	String[] getNextUrl();
	/**
	 * Get the current level of the user.
	 * This is the level the user is currently in.
	 * If we later think about allowing the user to select level we have to implement a getUnlockedLevel() Function to get the maximum available level.
	 * @return Current user level
	 */
	int getLevel();
	/**
	 * Get how many points in the current level the user has.
	 * 
	 * It is worth to mention that these are not persistently saved.
	 * The user has to restart the given level from 0 points each time the app starts.
	 * @return points in the current level.
	 */
	int getPoints();
	/**
	 * What type of phish is this currently
	 * @return type of the current phish
	 */
	PhishType getType();
	/**
	 * This function is called when the user chooses weather this URL is a phish or not 
	 * @param accptance true of the user thinks this is a phish. false otherwise.
	 * @return a {@link PhishResult} enum representing the state of the phish
	 */
	PhishResult userClicked(boolean accptance);
	/**
	 * This function must be called when the user selects a part of the URL as phishy.
	 * @param part the part that the user suspects to be an attack.
	 * @return true of the user clicked the correct part. False otherwise
	 */
	boolean partClicked(int part);
	
}