package com.seidor.comerzzia.commons.domain.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public interface BucketServiceAwsCloud {

    /**
     * Envia um arquivo para uma pasta específica no bucket.
     *
     * @param file O arquivo a ser enviado (MultipartFile do Spring).
     * @return A URL pública do arquivo.
     * @throws IOException Se houver um erro ao ler o arquivo.
     */    
	String sendFile(MultipartFile file) throws IOException;
	
    /**
     * Envia um arquivo para uma pasta específica no bucket.
     *
     * @param folderName O nome da pasta dentro do bucket (ex: "documentos", "imagens").
     * @param file O arquivo a ser enviado (MultipartFile do Spring).
     * @return A URL pública do arquivo.
     * @throws IOException Se houver um erro ao ler o arquivo.
     */
	String sendFile(String folderName, MultipartFile file) throws IOException;
	
    /**
     * Envia um arquivo para uma pasta específica no bucket.
     *
     * @param storage Instancia do AWS
     * @param bucketName Nome do Bucket
     * @param folderName O nome da pasta dentro do bucket (ex: "documentos", "imagens").
     * @param file O arquivo a ser enviado (MultipartFile do Spring).
     * @return A URL pública do arquivo.
     * @throws IOException Se houver um erro ao ler o arquivo.
     */
    default String uploadFile(AmazonS3 s3Client, String bucketName, String folderName, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String key = folderName != null ? folderName + "/" + fileName : fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, metadata);
            s3Client.putObject(putObjectRequest);
        }
        return s3Client.getUrl(bucketName, key).toString();
    }
	
    /**
     * Baixa um arquivo.
     *
     * @param folderName O nome da pasta.
     * @param fileName O nome do arquivo.
     * @return O InputStream do arquivo.
     */
	InputStream downloadFile(String folderName, String fileName);
	
    /**
     * Remove um arquivo.
     *
     * @param fileName O nome do arquivo.
     */	
	void deleteFile(String fileName);
	
}
