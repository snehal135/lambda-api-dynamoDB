package demo.com.lambda_api_dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	
	static final Logger log=LoggerFactory.getLogger(App.class);
    public static Object handleRequest(Request request,Context context) throws ResourceNotFoundException
    {
    	
    	AmazonDynamoDB client=AmazonDynamoDBClientBuilder.defaultClient();
    	DynamoDBMapper mapper=new DynamoDBMapper(client);
    	
    	Student student=null;
    	switch(request.getHttpMethod()) {
    	
    	case "GET":
    		student=mapper.load(Student.class,request.getId());
    		//student=mapper.lo
    	if(student==null) {
    		throw new ResourceNotFoundException("Resource not found" );
    	}
    	return student;   	
    	
    case "POST":
		student=request.getStudent();
		mapper.save(student);
//	return student;
	return student;
	
	default: 
		break;
	}
    	
        System.out.println( "Hello World!" );
		return null;
    }
}
