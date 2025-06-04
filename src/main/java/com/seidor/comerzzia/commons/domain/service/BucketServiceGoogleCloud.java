package com.seidor.comerzzia.commons.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

public interface BucketServiceGoogleCloud {

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
     * @param storage Instancia do Storage
     * @param bucketName Nome do Bucket
     * @param folderName O nome da pasta dentro do bucket (ex: "documentos", "imagens").
     * @param file O arquivo a ser enviado (MultipartFile do Spring).
     * @return A URL pública do arquivo.
     * @throws IOException Se houver um erro ao ler o arquivo.
     */
    default String uploadFile(Storage storage, String bucketName, String folderName, MultipartFile file) throws IOException {
        
    	String fileName = file.getOriginalFilename();
        fileName = folderName != null ? folderName + "/" + fileName : fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        
        String objectName = Paths.get(fileName).getFileName().toString();

        if (!file.isEmpty()) {
            BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, objectName)
                    .setContentType(file.getContentType())
                    .build();

            try (InputStream inputStream = file.getInputStream()) {
            	storage.createFrom(blobInfo, inputStream);
            } catch (IOException e) {
            	e.printStackTrace();
            	System.err.print("[BucketServiceGoogleCloud] - Falha ao enviar o arquivo " + fileName + " para o bucket " + bucketName + ". Erro: {}" + e.getMessage());
            }
        }
        
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, objectName);

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
