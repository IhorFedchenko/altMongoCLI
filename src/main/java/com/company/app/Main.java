package com.company.app;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fedchenko on 22.06.17.
 */
public class Main {

    static String host = "localhost";
    static int port = 27017;
    static String database = "test";

    public static void setDatabase(String database) {
        Main.database = database;
    }

    public static void setHost(String host) {
        Main.host = host;
    }

    public static void setPort(int port) {
        Main.port = port;
    }

    public static void main(String[] args) {

        if (args.length >= 2 && args.length % 2 == 0) {
            insertKeyValue(args);
        }


        MongoClient mongoClient = null;
        mongoClient = new MongoClient(host, port);

        // Get database
        MongoDatabase database = mongoClient.getDatabase(Main.database);

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
        collection.find().projection(Projections.include("brand", "model")).forEach((Block<Document>) example10 -> {
            System.out.println(example10.toJson());
        });

        //SELECT field.* FROM table
        System.out.println();
        System.out.println("***example11***");
        collection.find().projection(Projections.include("engineSpec")).forEach((Block<Document>) example11 -> {
            System.out.println(example11.toJson());
        });

        //SELECT field.subfield FROM table
        System.out.println();
        System.out.println("***example12***");
        collection.find().projection((Projections.include("engineSpec.transmission"))).forEach((Block<Document>) example12 -> {
            System.out.println(example12.toJson());
        });

        //SELECT * FROM table SKIP value LIMIT value
        System.out.println();
        System.out.println("***example13***");
        collection.find().skip(0).limit(0).forEach((Block<Document>) example13 -> {
            System.out.println(example13.toJson());
        });
        // SELECT * FROM table ORDER fields ASC
        System.out.println();
        System.out.println("***example14***");
        collection.find().sort(new BasicDBObject("rank", 1)).forEach((Block<Document>) example14 -> {
            System.out.println(example14.toJson());
        });

        // SELECT * FROM table ORDER fields DESC
        System.out.println();
        System.out.println("***example15***");
        collection.find().sort(new BasicDBObject("rank", -1)).forEach((Block<Document>) example15 -> {
            System.out.println(example15.toJson());
        });
    }

    public static void insertKeyValue(String[] input){
        Map setup = new HashMap<String, String >();

        for(int i = 0; i < input.length; i+=2){
            if(input[i].substring(0,2).equals("--")){
                setup.put(input[i], input[i+1]);
            }
        }

        if(setup.containsKey("--db")){
            setDatabase((String) setup.get("--db"));
        }
        if(setup.containsKey("--host")){
            setHost((String) setup.get("--host"));
        }
        if(setup.containsKey("--port")){
            setPort((Integer) setup.get("--port"));
        }
    }
}
