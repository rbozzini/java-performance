package mflix.api.daos;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

import mflix.api.models.Comment;
import mflix.api.models.Critic;

@Component
public class CommentDao extends AbstractMFlixDao {

	public static String COMMENT_COLLECTION = "comments";

	private MongoCollection<Comment> commentCollection;

	private CodecRegistry pojoCodecRegistry;

	private final Logger log;

	@Autowired
	public CommentDao(MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
		super(mongoClient, databaseName);
		log = LoggerFactory.getLogger(this.getClass());
		this.db = this.mongoClient.getDatabase(MFLIX_DATABASE);
		this.pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		this.commentCollection = db.getCollection(COMMENT_COLLECTION, Comment.class)
				.withCodecRegistry(pojoCodecRegistry);
	}

	/**
	 * Returns a Comment object that matches the provided id string.
	 *
	 * @param id - comment identifier
	 * @return Comment object corresponding to the identifier value
	 */
	public Comment getComment(String id) {
		return commentCollection.find(new Document("_id", new ObjectId(id))).first();
	}

	/**
	 * Adds a new Comment to the collection. The equivalent instruction in the mongo
	 * shell would be:
	 *
	 * <p>
	 * db.comments.insertOne({comment})
	 *
	 * <p>
	 *
	 * @param comment - Comment object.
	 * @throw IncorrectDaoOperation if the insert fails, otherwise returns the
	 *        resulting Comment object.
	 */
	public Comment addComment(Comment comment) {

		// Ticket - Update User reviews: implement the functionality that enables
		// adding a new
		// comment.
		if (comment.getId() == null) {
			throw new IncorrectDaoOperation("Comment must have an id.");
		}
		
		try {
			commentCollection.insertOne(comment);
		} catch (Exception e) {
			// TODO
			return null;
		}

		// TODO> Ticket - Handling Errors: Implement a try catch block to
		// handle a potential write exception when given a wrong commentId.
		return comment;
	}

	/**
	 * Updates the comment text matching commentId and user email. This method would
	 * be equivalent to running the following mongo shell command:
	 *
	 * <p>
	 * db.comments.update({_id: commentId}, {$set: { "text": text, date: ISODate()
	 * }})
	 *
	 * <p>
	 *
	 * @param commentId - comment id string value.
	 * @param text      - comment text to be updated.
	 * @param email     - user email.
	 * @return true if successfully updates the comment text.
	 */
	public boolean updateComment(String commentId, String text, String email) {

		// Ticket - Update User reviews: implement the functionality that enables
		// updating an
		// user own comments
		Bson updateObj = Updates.combine(Updates.set("text", text), Updates.set("date", new Date()));
		Bson query = new Document("_id", new ObjectId(commentId)).append("email", email);
		
		// TODO> Ticket - Handling Errors: Implement a try catch block to
		// handle a potential write exception when given a wrong commentId.
		try {
			commentCollection.updateOne(query, updateObj);
		} catch (Exception e) {
			// TODO 
			return false;
		}

		Comment updatedComment = commentCollection.find(new Document("_id", new ObjectId(commentId))).first();

		boolean updated = false;
		if (updatedComment != null && updatedComment.getText().equals(text)) {
			updated = true;
		}

		return updated;
	}

	/**
	 * Deletes comment that matches user email and commentId.
	 *
	 * @param commentId - commentId string value.
	 * @param email     - user email value.
	 * @return true if successful deletes the comment.
	 */
	public boolean deleteComment(String commentId, String email) {
		// Ticket Delete Comments - Implement the method that enables the deletion
		// of a user
		// comment

		Bson filter = new Document("_id", new ObjectId(commentId)).append("email", email);
		DeleteResult dResult = null;
		try {
			dResult = commentCollection.deleteOne(filter);
		} catch (Exception e) {
			return false;
		}

		// TIP: make sure to match only users that own the given commentId
		// TODO> Ticket Handling Errors - Implement a try catch block to
		// handle a potential write exception when given a wrong commentId.
		return dResult.getDeletedCount() == 1;
	}

	/**
	 * Ticket: User Report - produce a list of users that comment the most in the
	 * website. Query the `comments` collection and group the users by number of
	 * comments. The list is limited to up most 20 commenter.
	 *
	 * @return List {@link Critic} objects.
	 */
	public List<Critic> mostActiveCommenters() {
		List<Critic> mostActive = new ArrayList<>();
		// // Ticket: User Report - execute a command that returns the
		// // list of 20 users, group by number of comments. Don't forget,
		// // this report is expected to be produced with an high durability
		// // guarantee for the returned documents. Once a commenter is in the
		// // top 20 of users, they become a Critic, so mostActive is composed of
		// // Critic objects.

		commentCollection
				.aggregate(Arrays.asList(Aggregates.group("$email", Accumulators.sum("count", 1L)),
						Aggregates.sort(Sorts.descending("count")), Aggregates.limit(20)), Critic.class)
				.into(mostActive);

		return mostActive;
	}
}
