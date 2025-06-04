package com.seidor.comerzzia.commons.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public interface AwsS3ClientConfig {
	
	/**
	 * Configuração do client AmazonS3 para Bucket
	 * 
	 * @param region Região da conta AWS. Exemplo: sa-east-1
	 * @param accessKeyId Chave de acesso da conta AWS
	 * @param secretAccessKey Senha de Chave de acesso da conta AWS 
	 * @return AmazonS3
	 */
    default AmazonS3 s3ClientAws(String region, String accessKeyId, String secretAccessKey) {

        AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

}
