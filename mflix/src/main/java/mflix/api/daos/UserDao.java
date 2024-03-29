package mflix.api.daos;

import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import mflix.api.models.Session;
import mflix.api.models.User;

@Configuration
public class UserDao extends AbstractMFlixDao {

	private final MongoCollection<User> usersCollection;
	// Ticket: User Management - do the necessary changes so that the sessions
	// collection
	// returns a Session object
	private final MongoCollection<Session> sessionsCollection;

	private final Logger log;

	@Autowired
	public UserDao(MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
		super(mongoClient, databaseName);
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		usersCollection = db.getCollection("users", User.class).withCodecRegistry(pojoCodecRegistry);
		log = LoggerFactory.getLogger(this.getClass());
		// Ticket: User Management - implement the necessary changes so that the
		// sessions
		// collection returns a Session objects instead of Document objects.
		sessionsCollection = db.getCollection("sessions", Session.class).withCodecRegistry(pojoCodecRegistry);
	}

	/**
	 * Inserts the `user` object in the `users` collection.
	 *
	 * @param user - User object to be added
	 * @return True if successful, throw IncorrectDaoOperation otherwise
	 */
	public boolean addUser(User user) {
		// Ticket: Durable Writes - you might want to use a more durable write
		// concern here!
		
		try {
			User existingUser = getUser(user.getEmail());
			if (existingUser != null) {
				return false;
			}
		} catch (Exception e) {
		}
		usersCollection.insertOne(user);
		return true;
		
//	    UpdateOptions options = new UpdateOptions();
//	    options.upsert(true);
//
//	    Bson filter = Filters.eq("email", user.getEmail());
//	    // and adding those options to the update method.
//	    usersCollection.updateOne(filter, new Document("$set", user), options);

		// Ticket: Handling Errors - make sure to only add new users
		// and not users that already exist.

	}

	/**
	 * Creates session using userId and jwt token.
	 *
	 * @param userId - user string identifier
	 * @param jwt    - jwt string token
	 * @return true if successful
	 */
	public boolean createUserSession(String userId, String jwt) {
		// Ticket: User Management - implement the method that allows session
		// information to be
		// stored in it's designated collection.
		
		try {
			Session existingUserSession = getUserSession(userId);
			if (existingUserSession != null) {
				return true;
			}
		} catch (Exception e) {
			
		}
		
		Session userSession = new Session();
		userSession.setUserId(userId);
		userSession.setJwt(jwt);
		sessionsCollection.insertOne(userSession);
		return true;
		// TODO > Ticket: Handling Errors - implement a safeguard against
		// creating a session with the same jwt token.
	}

	/**
	 * Returns the User object matching the an email string value.
	 *
	 * @param email - email string to be matched.
	 * @return User object or null.
	 */
	public User getUser(String email) {
		User user = null;
		// TODO> Ticket: User Management - implement the query that returns the first
		// User object.
		Document queryFilter = new Document("email", email);
		user = usersCollection.find(queryFilter).limit(1).iterator().next();
		return user;
	}

	/**
	 * Given the userId, returns a Session object.
	 *
	 * @param userId - user string identifier.
	 * @return Session object or null.
	 */
	public Session getUserSession(String userId) {
		// TODO> Ticket: User Management - implement the method that returns Sessions
		// for a given
		// userId
		Document queryFilter = new Document("user_id", userId);
		Session userSession = sessionsCollection.find(queryFilter).limit(1).iterator().next();
		return userSession;
	}

	public boolean deleteUserSessions(String userId) {
		// TODO> Ticket: User Management - implement the delete user sessions method
		Document queryFilter = new Document("user_id", userId);
		DeleteResult result = sessionsCollection.deleteOne(queryFilter);
		return result.getDeletedCount() == 1;
	}

	/**
	 * Removes the user document that match the provided email.
	 *
	 * @param email - of the user to be deleted.
	 * @return true if user successfully removed
	 */
	public boolean deleteUser(String email) {
		// remove user sessions
		// TODO> Ticket: User Management - implement the delete user method
		Document queryFilter = new Document("email", email);
		DeleteResult result = usersCollection.deleteOne(queryFilter);
		deleteUserSessions(email);
		// TODO > Ticket: Handling Errors - make this method more robust by
		// handling potential exceptions.
		return result.getDeletedCount() == 1;
	}

	/**
	 * Updates the preferences of an user identified by `email` parameter.
	 *
	 * @param email           - user to be updated email
	 * @param userPreferences - set of preferences that should be stored and replace
	 *                        the existing ones. Cannot be set to null value
	 * @return User object that just been updated.
	 */
	public boolean updateUserPreferences(String email, Map<String, ?> userPreferences) {
		// TODO> Ticket: User Preferences - implement the method that allows for user
		// preferences to
		// be updated.
		if (userPreferences == null) {
			throw new IncorrectDaoOperation("userPreferences cannot be null");
		}
		Set<String> keys = userPreferences.keySet();
		Document updates = new Document();
		for (String key : keys) {
			updates.append(key, userPreferences.get(key));
		}
		Bson queryFilter = new Document("email", email);
		UpdateOptions options = new UpdateOptions();
		options.upsert(true); // set upsert to true
		usersCollection.updateOne(queryFilter, set("preferences", updates), options);

//		usersCollection.updateOne(queryFilter, set("preferences", userPreferences));

		// TODO > Ticket: Handling Errors - make this method more robust by
		// handling potential exceptions when updating an entry.
		return true;
	}
}
