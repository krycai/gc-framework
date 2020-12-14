package com.allen.jdbc;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

public class MongodbDemo2 {

	public static void main(String[] args) {

        try {
            List<ServerAddress> addresses = new ArrayList<ServerAddress>();
            ServerAddress address1 = new ServerAddress("192.168.2.237" , 10005);
            ServerAddress address2 = new ServerAddress("192.168.2.238" , 10005);
            ServerAddress address3 = new ServerAddress("192.168.2.239" , 10005);
            addresses.add(address1);
            addresses.add(address2);
            addresses.add(address3);
            int i=0;

            MongoClient client = new MongoClient(addresses);
            //定义数据库
            DB db = client.getDB( "videoimage");
            //定义表名
            DBCollection coll = db.getCollection("Face");
//            DBCollection coll = db.getCollection("ImageInfo");Person Face MotorVehicle
//             // 插入
//            BasicDBObject object = null;
//            for (i=0;i<100000;i++)
//            {
//            object = new BasicDBObject();
//            object.append( "age", i );
//            object.append( "name", "user"+String.valueOf(i) );
//            coll.insert(object);
//            if(i%500==0){
//            	System.out.println(i); 
//            }
//            }

            
            	//查询数据
            DBCursor dbCursor = coll.find();

             while (dbCursor.hasNext()) {
                  DBObject dbObject = dbCursor.next();
                  System. out.println(dbObject.toString());
//                  System. out.println(dbObject.get("_id"));
//                  dbObject.get("ID").toString().substring(0,dbObject.get("ID").toString().lastIndexOf("."));
//                  String id=dbObject.get("ID").toString();
//                   System. out.println("==="+Integer.parseInt(id.substring(0,id.lastIndexOf("."))));
//                  System. out.println(dbObject.get("MotorVehicleID"));
//                  System. out.println(dbObject.get("NearImageID"));
//                  System. out.println(dbObject.get("NearImageURL"));
//                  System. out.println(dbObject.get("FarImageID"));
                 
            }

     } catch (Exception e) {
            e.printStackTrace();
     }    	    	 	   

	}

}
