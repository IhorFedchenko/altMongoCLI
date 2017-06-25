package com.company.app;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;

/**
 * Created by fedchenko on 22.06.17.
 */
public class Main {



    static String uriDb = "mongodb://127.0.0.1:27017";

    static String dbName = "test";

    public static void setUriDb(String uriDb) {
        Main.uriDb = uriDb;
    }

    public static void setDbName(String dbName) {
        Main.dbName = dbName;
    }

    public static void main(String[] args) {
        if (args.length == 1){
            setUriDb(args[0]);
        }
        if (args.length == 2){
            setUriDb(args[0]);
            setDbName(args[1]);
        }


        MongoClientURI uri = new MongoClientURI(uriDb);


        MongoClient mongoClient = null;

        mongoClient = new MongoClient(uri);

        // Get database
        MongoDatabase database = mongoClient.getDatabase(dbName);

        // Get collection
        MongoCollection<Document> collection = database.getCollection("sport");

        //SELECT * FROM table
        System.out.println("***example01***");
        collection.find().forEach((Block<Document>) example01 -> {
            System.out.println(example01.toJson());
        });

        //SELECT * FROM table WHERE field = value
        System.out.println();
        System.out.println("***example02***");
        collection.find(Filters.eq("brand", "Acura")).forEach((Block<Document>) example02 -> {
            System.out.println(example02.toJson());
        });
        //SELECT * FROM table WHERE field > value
        System.out.println();
        System.out.println("***example03***");
        collection.find(Filters.gt("rank", 5)).forEach((Block<Document>) example03 -> {
            System.out.println(example03.toJson());
        });

        //SELECT * FROM table WHERE field >= value
        System.out.println();
        System.out.println("***example04***");
        collection.find(Filters.gte("rank", 6)).forEach((Block<Document>) example04 -> {
            System.out.println(example04.toJson());
        });

        //SELECT * FROM table WHERE field < value
        System.out.println();
        System.out.println("***example05***");
        collection.find(Filters.lt("rank", 6)).forEach((Block<Document>) example05 -> {
            System.out.println(example05.toJson());
        });

        //SELECT * FROM table WHERE field <= value
        System.out.println();
        System.out.println("***example06***");
        collection.find(Filters.lte("rank", 5)).forEach((Block<Document>) example06 -> {
            System.out.println(example06.toJson());
        });

        //SELECT * FROM table WHERE field <> value
        System.out.println();
        System.out.println("***example07***");
        collection.find(Filters.ne("rank", 5)).forEach((Block<Document>) example07 -> {
            System.out.println(example07.toJson());
        });

        //SElECT * FROM table WHERE <condition> and <condition>
        System.out.println();
        System.out.println("***example08***");
        collection.find(Filters.and(Filters.gte("rank", 3), Filters.lte("rank", 7)))
                .forEach((Block<Document>) example08 -> {
                    System.out.println(example08.toJson());
                });

        //SELECT * FROM table WHERE <condition> or <condition>
        System.out.println();
        System.out.println("***exaple09***");
        collection.find(Filters.or(Filters.eq("rank", 3), Filters.eq("rank", 7)))
                .forEach((Block<Document>) exemple09 -> {
                    System.out.println(exemple09.toJson());
                });

        //SELECT fields FROM table
        System.out.println();
        System.out.println("***example10***");
        collection.find().projection(Projections.include("brand","model")).forEach((Block<Document>) example10 ->{
            System.out.println(example10.toJson());
        });

        //SELECT field.* FROM table
        System.out.println();
        System.out.println("***example11***");
        collection.find().projection(Projections.include("engineSpec")).forEach((Block<Document>) example11 ->{
            System.out.println(example11.toJson());
        });

        //SELECT field.subfield FROM table
        System.out.println();
        System.out.println("***example12***");
        collection.find().projection((Projections.include("engineSpec.transmission"))).forEach((Block<Document>) example12 ->{
            System.out.println(example12.toJson());
        });

        //SELECT * FROM table SKIP value LIMIT value
        System.out.println();
        System.out.println("***example13***");
        collection.find().skip(0).limit(0).forEach((Block<Document>) example01 -> {
            System.out.println(example01.toJson());
        });
    }
}
