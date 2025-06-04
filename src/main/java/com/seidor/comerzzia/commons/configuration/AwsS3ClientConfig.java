package com.seidor.comerzzia.commons.configuration;

import com.amazonaws.services.s3.AmazonS3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public interface AwsS3ClientConfig {
	
    default AmazonS3 s3ClientAws(String region, String accessKeyId, String secretAccessKey) {

        AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

}
