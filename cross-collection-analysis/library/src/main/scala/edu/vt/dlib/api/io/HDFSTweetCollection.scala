package edu.vt.dlib.api.io

class HDFSTweetCollection(sc: org.apache.spark.SparkContext, sqlContext: org.apache.spark.sql.SQLContext, var collectionId: String) extends TweetCollection(sc, sqlContext) {
    
    import org.apache.avro.mapred.AvroInputFormat
    import org.apache.avro.mapred.AvroWrapper
    import org.apache.avro.generic.GenericRecord
    import org.apache.hadoop.io.NullWritable
	
	val path = "/collections/tweets/z_" + collectionId + "/part-m-00000.avro"
    val records = sc.hadoopFile[AvroWrapper[GenericRecord], NullWritable, AvroInputFormat[GenericRecord]](path)
    var collection = records.map(lambda => (new String(lambda._1.datum.get("id").toString), new String(lambda._1.datum.get("text").toString).split(" ")))

}