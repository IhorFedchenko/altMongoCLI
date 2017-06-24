package com.company.app;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

/**
 * Created by fedchenko on 22.06.17.
 */
public class Main {
    public static void main(String[] args) {

        MongoClientURI uri = new MongoClientURI("mongodb://127.0.0.1:27017");

        MongoClient mongoClient = null;

        mongoClient = new MongoClient(uri);

        // Get database
        MongoDatabase database = mongoClient.getDatabase("cars");

        // Get collection
        MongoCollection<Document> collection = database.getCollection("sport");

        //(SELECT * FROM table)
        System.out.println("***exemple01***");
        collection.find().forEach((Block<Document>) exemple01 -> {
            System.out.println(exemple01.toJson());
        });

        //SELECT * FROM table WHERE field = value
        System.out.println();
        System.out.println("***exemple02***");
        collection.find(Filters.eq("brand", "Acura")).forEach((Block<Document>) exemple02 -> {
            System.out.println(exemple02.toJson());
        });
        //SELECT * FROM table WHERE field > value
        System.out.println();
        System.out.println("***exemple03***");
        collection.find(Filters.gt("rank", 5)).forEach((Block<Document>) exemple03 -> {
            System.out.println(exemple03.toJson());
        });

        //SELECT * FROM table WHERE field >= value
        System.out.println();
        System.out.println("***exemple04***");
        collection.find(Filters.gte("rank", 6)).forEach((Block<Document>) exemple04 -> {
            System.out.println(exemple04.toJson());
        });

        //SELECT * FROM table WHERE field < value
        System.out.println();
        System.out.println("***exemple05***");
        collection.find(Filters.lt("rank", 6)).forEach((Block<Document>) exemple05 -> {
            System.out.println(exemple05.toJson());
        });

        //SELECT * FROM table WHERE field <= value
        System.out.println();
        System.out.println("***exemple06***");
        collection.find(Filters.lte("rank", 5)).forEach((Block<Document>) exemple06 -> {
            System.out.println(exemple06.toJson());
        });

        //SELECT * FROM table WHERE field <> value
        System.out.println();
        System.out.println("***exemple07***");
        collection.find(Filters.ne("rank", 5)).forEach((Block<Document>) exemple07 -> {
            System.out.println(exemple07.toJson());
        });
    }
}
