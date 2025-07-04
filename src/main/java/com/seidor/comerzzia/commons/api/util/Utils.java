package com.seidor.comerzzia.commons.api.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public abstract class Utils {
	
    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if(pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("Page size inválido: " + pageSize);
        }
        
        int fromIndex = (page - 1) * pageSize;
        if(sourceList == null || sourceList.size() <= fromIndex){
            return Collections.emptyList();
        }
        
        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }
    
	public static <T> Integer calcPages(List<T> sourceList, Integer pageSize) {
		
		Long pages = ((long) sourceList.size() / pageSize);
		Integer resto = (sourceList.size() % pageSize);
		pages = resto > 0 ? Math.round(pages) + 1 : pages;
		
		if(pages == 0)
			pages = Long.parseLong("1");
		
		Integer totalPages = Math.round(pages);
		
		return totalPages;
	}
	
	public static String cleanString(String item) {
		
		if (item == null || item == "")
			return null;
			
		String item2 = item.replace(".", "")
			.replace("-", "")
			.replace("/", "")
			.toUpperCase();
		
		return item2;
		
	}
	
	public static String formatNumber(BigDecimal value) {
		
		if (value == null)
			return null;
		
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(value).replace(",", ".");
		
	}
	
	public static String formatarCPF(String cpf) {
		
		if (cpf == null)
			return null;
		
	    cpf = cpf.replaceAll("[^0-9]",""); // Remover caracteres não numéricos
	    return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
	}
	
	public static String formatarCNPJ(String cnpj) {
		
		if (cnpj == null)
			return null;
		
	    cnpj = cnpj.replaceAll("[^0-9]",""); // Remover caracteres não numéricos
	    return cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
	}
	
    public static MultipartFile convertFileToMultipartFile(String filePathString, String paramName) throws IOException {
        Path path = Paths.get(filePathString);
        String originalFilename = path.getFileName().toString();
        String contentType = Files.probeContentType(path); // Tenta detectar o tipo MIME

        // Lê os bytes do arquivo
        byte[] content = Files.readAllBytes(path);

        // Cria o MockMultipartFile
        return new MockMultipartFile(paramName, originalFilename, contentType, content);
    }
    
    public static BigDecimal getBigDecimalValue(String value) {
    	
    	try {
    		
    		return new BigDecimal(value);
    		
    	} catch(Exception e) {
    		return null;
    	}
    	
    }

}
