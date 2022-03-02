package bestreads.readingtip;

import java.sql.Connection;
import java.util.*;

import bestreads.databasehandlers.ConnectionManager;
import bestreads.databasehandlers.DatabaseManager;

/** The ReadingTips object contains a collection of
 *  reading tips of Tip class objects.
 *
 * @see Tip
 */
public class ReadingTips {
    /** Container for the tips */
    private ArrayList<Tip> tips;
    private static Connection conn = null;
    private DatabaseManager dbManager = new DatabaseManager();

    /** The constructor of the ReadingTips class
     */
    public ReadingTips() {
	    this.tips = new ArrayList<Tip>();
    }

    /** Creates new Tip object and adds it to the cllection
     *
     * @param url The network adress of the tip
     * @param title The title text for the tip
     */
    public void addTip(String url, String title) {
	    Tip newTip = new Tip(url, title);

	    this.tips.add(newTip);

        try {
            conn = ConnectionManager.getConnection();
            dbManager.inserIntoDatabase(conn, newTip);
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

    /** Produces string representation of all the tips.
     * Each line contains one tip
     *
     * @return Tips as a multiline string
     * @see Tip#toString()
     */
    // @override
    public String toString() {
	    String allTips = "";


        try {
            conn = ConnectionManager.getConnection();
            this.tips = dbManager.getAllTipsFromDatabase(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
	
	    int i = 0;
	
	    while (this.tips.size() > i) {
	        allTips += this.tips.get(i) + "\n";
	        i++;
	    }

	    return allTips;
    }
}
