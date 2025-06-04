package com.seidor.comerzzia.commons.domain.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface BucketService {

    /**
     * Envia um arquivo para uma pasta específica no bucket.
     *
     * @param file O arquivo a ser enviado (MultipartFile do Spring).
     * @return A URL pública do arquivo.
     * @throws IOException Se houver um erro ao ler o arquivo.
     */    
	String uploadFile(MultipartFile file) throws IOException;
	
    /**
     * Envia um arquivo para uma pasta específica no bucket.
     *
     * @param folderName O nome da pasta dentro do bucket (ex: "documentos", "imagens").
     * @param file O arquivo a ser enviado (MultipartFile do Spring).
     * @return A URL pública do arquivo.
     * @throws IOException Se houver um erro ao ler o arquivo.
     */
	String uploadFile(String folderName, MultipartFile file) throws IOException;
	
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
