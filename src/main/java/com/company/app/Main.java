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
            System.out.println("=======");
            System.out.println("find all entities from collection");
            System.out.println("=======");
            collection.find().forEach((Block<Document>) exemple01 -> {
                System.out.println(exemple01.toJson());
            });

            //SELECT * FROM table Where field = value
            System.out.println("=======");
            System.out.println("find field");
            System.out.println("=======");
            collection.find(Filters.eq("brand", "Acura")).forEach((Block<Document>) exemple02 ->{
            System.out.println(exemple02.toJson());
        });



        }
    }

