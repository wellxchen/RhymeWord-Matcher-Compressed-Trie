import java.util.ArrayList;

/**
 * Result class that stores additional information about pool of words for testing
 * @author chengkang xu
 *
 */
class Result {
	ArrayList <String> subtrie;
	String selected;
	Result (ArrayList <String> subtrie, String selected) {
		this.selected = selected;
		this.subtrie = subtrie;
	}
}