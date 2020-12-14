package com.allen.jdbc;

import com.mongodb.*;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MongoDBDemo {
	
	 private final static Logger logger = Logger.getLogger(MongoDBDemo.class);
	 
	 private static final String HOST="192.168.163.130";
	 private static final int PORT=27017;
	 private static final String DATABASE="blog";
	 private static final String TABLE="blog";
	 
	 //连接mongodb
	 private static DBCollection getTemplateMongodb() {
		 try {
			ServerAddress address = new ServerAddress(HOST, PORT);
			MongoClient client = new MongoClient(address);
			logger.info("连接mongodb成功");
			DB db = client.getDB(DATABASE);
			DBCollection coll = db.getCollection(TABLE);
			logger.info("获取表信息");
			return coll;
		} catch (Exception e) {
			logger.debug("连接失败："+e.getMessage());
			return null;
	   }
		 
	 }

	 //保存数据
	 private static void saveData(DBCollection coll) {
		 DBObject object=new BasicDBObject();
		 object.put("title", "oracle");
		 object.put("context","数据库中的一种");
		 object.put("type","IT界");
		 
		 DBObject info=new BasicDBObject();
		 info.put("age", "15");
		 info.put("foreiger","老外");
		 object.put("info",info);
		 coll.save(object);
		 logger.info("saveData插入成功");
	 }
	 
	 private static void batchInsert(DBCollection coll) {
		 for(int i=0;i<10000000;i++) {
			 DBObject object =new BasicDBObject();
			 object.put("title", "Java课程"+i);
			 object.put("context","中国官方海洋大学课程"+i);
			 object.put("name", "学生"+i);
			 coll.insert(object);
			
		 }
		 logger.info("batchInsert批量插入成功");
	 }

	 //插入数据
	 private static void insertData(DBCollection coll) {
		 DBObject object =new BasicDBObject();
		 object.put("title", "语文1");
		 object.put("context","中国官方倡导学习课程1");
		 object.put("grade", 90);
		 coll.insert(object);
		 logger.info("insertData单个插入成功");
		 
		 List<DBObject> list= new ArrayList<DBObject>();
		 DBObject info=new BasicDBObject();
		 info.put("title", "数学1");
		 info.put("context", "数学在我国有悠久的发展史1");
		 info.put("grade", 89);
		 info.put("url", "中国");
		 info.put("age", 99);
		 
		 DBObject info1=new BasicDBObject();
		 info1.put("title", "英语1");
		 info1.put("context", "老外low2");
		 info1.put("grade", 33);
		 info1.put("age", 0);
		 
		 list.add(info);
		 list.add(info1);
		 coll.insert(list);
		 logger.info("insertData插入list成功");
	 }
	 
	 private static void update(DBCollection coll) {
		 
		 //根据username来更新，修改age如果没有追加，更新所有满足条件的文档  
//		 coll.update(new BasicDBObject("title", "mysql"),  
//		         new BasicDBObject("$set", new BasicDBObject("context", "数据库sql")), false, true);  
		 
		 //更新 条件
		 DBObject updateinfo=new BasicDBObject();
		 updateinfo.put("title", "mysql");
		 
		 //更新某值
		 DBObject updatevalue=new BasicDBObject();
		 updatevalue.put("context", "mysql数据库");
		 
		 //添加更新标志符 set
		 DBObject updatSetevalue=new BasicDBObject("$set",updatevalue);
		 
	    /** 
         * update blog set context='数据库sql' where title='mysql' 
         * updateinfo:更新条件 
         * updatevalue:设置的新值 
         */  
		 coll.update(updateinfo, updatSetevalue,false, true);
		 logger.info("update更新成功一");
		 
		 DBObject queryColl=new BasicDBObject();  
         
         //where name='sam',此条件在更新前不是成立的  
         queryColl.put("title", "mongodb");  
          
         DBObject setValue=new BasicDBObject();  
         setValue.put("context", "数据库sql");  
          
         DBObject upsertValue=new BasicDBObject("$set",setValue);  
         /** 
          * 后面两个参数含义分别是： 
          * 若所更新的数据没有，则插入 
          * ，同时更新多个符合条件的文档(collection) 
          */  
         coll.update(queryColl, upsertValue, true, true);  
         logger.info("update更新成功二");
    }
	 
	 //查询全部
	 private static void findAll(DBCollection coll) {
		 DBCursor dbCursor = coll.find();
		 while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            System. out.println(dbObject.toString());
        }
	 }
	 
	 //查询单个
	 private static void findOne(DBCollection coll) {
		 DBCursor dbCursor = coll.find(new BasicDBObject("age", 0));
		 while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            System. out.println(dbObject.toString());
        }
	 }
	 
	 //分页查询
	 private static void findByPages(DBCollection coll) {
		 int pageSize=15,pageNo=5,startPage=(pageNo-1)*pageSize;
		 ObjectId lastId=null;
		 int j=0;
		 
		 DBCursor dbCursor = coll.find();
		 int dataCount=dbCursor.count();
		 logger.info("查找全部数据共有="+dataCount);
		 logger.info("================查找全部数据=========================");
//		 while (dbCursor.hasNext()) {
//			DBObject dbObject = dbCursor.next();
//			logger.info(dbObject.toString());
//		}

		 logger.info("================分页  skip(跳动分页)=========================");
		DBCursor data = coll.find().skip(startPage).sort(new BasicDBObject()).limit(pageSize);
		
		List<Object> list = new ArrayList<Object>();
		while (data.hasNext()) {
			DBObject dbObject = data.next();
			j++;
			if(j==5) {
				lastId=(ObjectId) dbObject.get("_id");
				logger.info("Object="+dbObject.get("_id"));
			}
//			logger.info("Object="+dbObject.get("title"));
			list.add(dbObject.toString());
		}
		
		Integer pages = dataCount % pageSize > 0 ? dataCount / pageSize + 1 : dataCount / pageSize;
		logger.info("skip分页的页数="+pages);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}	
		
		logger.info("=================上下分页:根据ObjectId比较========================");
		DBCursor dbCur;
		if (pageNo == 1) {  
			dbCur = coll.find().sort(new BasicDBObject("id", 1)).limit(pageSize);  
	    } else { 
	    	logger.info("lastId="+lastId);
//	    	coll.find(new BasicDBObject("_id", new BasicDBObject(QueryOperators.GT, lastId)))
//	    	.sort(new BasicDBObject("_id", 1)).limit(pageSize);
	    	dbCur = coll.find(new BasicDBObject("_id", new BasicDBObject(  
	                        QueryOperators.LT, lastId)))  
	                .sort(new BasicDBObject("_id", 1)).limit(pageSize);
	    } 
		
		 while (dbCur.hasNext()) {
				DBObject dbObject = dbCur.next();
				logger.info(dbObject.toString());
		   }
	 }
	 
	//删除
	 private static void delete(DBCollection coll) {
		 BasicDBObject delete = new BasicDBObject("id","5a5ffba784029121d0c04d12");  
		  
		 coll.remove(delete); 
		 logger.info("delete删除成功");
	 }
	 
	public static void main(String[] args) {

		DBCollection coll=getTemplateMongodb();
			
//		saveData(coll);
//		insertData(coll);
//		batchInsert(coll);
//	    findAll(coll);
//	    System.out.println();
//	    findOne(coll);
//	    update(coll);
	    findByPages(coll);
//	    delete(coll);

	}

}
