1. Install spark in your desktop : https://spark.apache.org/downloads.html
2. Run spark with next command :

./bin/spark-shell --master local --packages com.databricks:spark-csv_2.11:1.5.0 


3. Run project with next command:
mvn spring-boot:run