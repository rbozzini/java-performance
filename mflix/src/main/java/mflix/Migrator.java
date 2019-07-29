package mflix;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.WriteModel;

public class Migrator {

	/**
	 * Creates and UpdateOneModel object for each Document that contains an
	 * "imdb.rating" field of non-numerical type into a parsable
	 *
	 * @param doc - Document object to be updated
	 * @return UpdateOneModel operation response object
	 */
	private static UpdateOneModel<Document> transformRating(Document doc) {
		try {
			String imdbRating = doc.get("imdb", Document.class).getString("rating");

			if (imdbRating == null) {
				return null;
			}

			int rating = 0;
			if (!"".equals(imdbRating)) {
				rating = Integer.valueOf(imdbRating);
			}
			// TODO> Ticket: Migration - define the UpdateOneModel object for
			// the rating type cleanup.
			//return new UpdateOneModel<Document>(new Document("$eq", new Document("_id", doc.getObjectId("_id"))), new Document());
			if (rating != 0) {
				return new UpdateOneModel<>(Filters.eq("_id", doc.getObjectId("_id")),
						Updates.set("imdb.rating", rating));
			}

		} catch (NumberFormatException e) {
			System.out.println(MessageFormat.format("Could not parse {0} into " + "number: {1}",
					doc.get("imdb.rating", e.getMessage())));
		}
		return null;
	}

	/**
	 * Creates an UpdateOneModel for each Document object field `lastupdated` of
	 * type string into an update $set to Date type. db.movies.update({_id:
	 * doc._id}, {$set: {lastupdated: ISODate(doc.lastupdated)}})
	 *
	 * @param doc - Document object to get the date transformation applied to
	 * @return UpdateOneModel object or null if no change is required.
	 */
	private static UpdateOneModel<Document> transformDates(Document doc, DateFormat dateFormat) {

		String lastUpdated = doc.getString("lastupdated");

		try {
			if (lastUpdated != null) {
				return new UpdateOneModel<>(Filters.eq("_id", doc.getObjectId("_id")),
						Updates.set("lastupdated", dateFormat.parse(lastUpdated)));
			}

		} catch (ParseException e) {
			System.out.println(MessageFormat.format("String date {0} cannot be parsed using {1} " + "format: {2}",
					lastUpdated, dateFormat, e.getMessage()));
		}

		return null;
	}

	/**
	 * Migration script main class. This should be executed within the IDE!
	 *
	 * @param args is a set of system arguments that can be ignored.
	 */
	public static void main(String[] args) {
		
		// db.movies.find({$or: [ {lastupdated : { $type : 'string'}}, {"imdb.rating" : { $type : 'string'}}]}, {lastupdated : 1}).count()
		//https://github.com/carlan/m220j/blob/master/mflix/src/main/java/mflix/Migrator.java

		System.out.println("Dataset cleanup migration");

		// set your MongoDB Cluster connection string
		// TODO> Ticket: Migration - set the cluster connection string.
		String mongoUri = "mongodb+srv://m220student:m220password@mflix-fwx3w.mongodb.net/test";

		// instantiate database and collection objects
		MongoDatabase mflix = MongoClients.create(mongoUri).getDatabase("sample_umflix");
		MongoCollection<Document> movies = mflix.getCollection("movies");

		//Bson dateStringFilter = Filters.type("lastupdated", "string");
		Bson dateStringFilter = Filters.and( Filters.exists("lastupdated"), Filters.type("lastupdated", "string") );
		String datePattern = "yyyy-MM-dd hh:mm:ss";
		// TODO> Ticket: Migration - create a query filter that finds all
		// documents that are required to be updated and the correct date
		// format pattern
		// Document myfilter1 = new Document("$match", 
	    //new Document("lastupdated", new Document("$type", "string")));
		//Document queryFilter = new Document("$match", new Document("lastupdated", new Document("$type", "string")));
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

		// create list of bulkWrites to be applied.
		List<WriteModel<Document>> bulkWrites = new ArrayList<>();

		// iterate over the documents and apply the transformations.
		//FindIterable<Document> docs = movies.find(dateStringFilter);

		for (Document doc : movies.find(dateStringFilter)) {

			// Apply lastupdate string to date conversion
			WriteModel<Document> updateDate = transformDates(doc, dateFormat);
			if (updateDate != null) {
				bulkWrites.add(updateDate);
			}
		}

		// TODO> Ticket: Migration - create a query filter that finds
		// documents where `imdb.rating` is of type string
		//Bson ratingStringFilter = Filters.type("imdb.rating", "string");
		Bson ratingStringFilter = Filters.and( Filters.exists("imdb.rating"), Filters.type("imdb.rating", "string") );
		for (Document doc : movies.find(ratingStringFilter)) {
			// Apply "imdb.rating" string to number conversion
			WriteModel<Document> updateRating = transformRating(doc);
			if (updateRating != null) {
				bulkWrites.add(updateRating);
			}
		}

		// execute the bulk update
		// TODO> Ticket: Migration - set the bulkWrite options
		BulkWriteOptions bulkWriteOptions = new BulkWriteOptions().ordered(false);
		if (bulkWrites.isEmpty()) {
			System.out.println("Nothing to update!");
			System.exit(0);
		}

		BulkWriteResult bulkResult = movies.bulkWrite(bulkWrites, bulkWriteOptions);
		// output the number of updated documents
		System.out.println(MessageFormat.format("Updated {0} documents", bulkResult.getModifiedCount()));
	}
}
